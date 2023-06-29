package com.brigido.senior.service.impl;

import com.brigido.senior.dto.AssociateRequestDTO;
import com.brigido.senior.dto.AssociateResponseDTO;
import com.brigido.senior.entity.Associate;
import com.brigido.senior.repository.AssociateRepository;
import com.brigido.senior.service.AssociateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class AssociateServiceImpl implements AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssociateResponseDTO findByIdDTO(UUID id) {
        return modelMapper.map(findById(id), AssociateResponseDTO.class);
    }

    @Override
    public Associate findById(UUID id) {
        return associateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Associado(a) não encontrado(a)."));
    }

    @Override
    public AssociateResponseDTO save(AssociateRequestDTO associateRequestDTO) {
        Associate associate = modelMapper.map(associateRequestDTO, Associate.class);
        return modelMapper.map(associateRepository.save(associate), AssociateResponseDTO.class);
    }

    @Override
    public List<AssociateResponseDTO> findAll() {
        return associateRepository.findAll()
                .stream()
                .map(associate -> modelMapper.map(associate, AssociateResponseDTO.class))
                .collect(toList());
    }

    @Override
    public AssociateResponseDTO update(UUID id, AssociateRequestDTO associateRequestDTO) {
        Associate associate = findById(id);
        associate.update(associateRequestDTO);
        return modelMapper.map(associate, AssociateResponseDTO.class);
    }

    @Override
    public void delete(UUID id) {
        associateRepository.deleteById(id);
    }
}
