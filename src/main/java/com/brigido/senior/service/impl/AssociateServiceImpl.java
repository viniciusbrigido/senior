package com.brigido.senior.service.impl;

import com.brigido.senior.dto.save.SaveAssociateDTO;
import com.brigido.senior.dto.response.ResponseAssociateDTO;
import com.brigido.senior.dto.update.UpdateAssociateDTO;
import com.brigido.senior.entity.Associate;
import com.brigido.senior.exception.InvalidCpfException;
import com.brigido.senior.exception.NotFoundEntityException;
import com.brigido.senior.repository.AssociateRepository;
import com.brigido.senior.service.AssociateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static com.brigido.senior.util.Util.*;
import static java.util.stream.Collectors.toList;

@Service
public class AssociateServiceImpl implements AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseAssociateDTO findByIdDTO(UUID id) {
        return toResponseDto(findById(id));
    }

    @Override
    public Associate findById(UUID id) {
        return associateRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Associate not found."));
    }

    @Override
    public ResponseAssociateDTO save(SaveAssociateDTO saveAssociateDTO) {
        Associate associate = modelMapper.map(saveAssociateDTO, Associate.class);
        validateCpfAssociate(associate.getCpf());
        return toResponseDto(associateRepository.save(associate));
    }

    private void validateCpfAssociate(String cpf) {
        if (!isCpfValid(cpf)) {
            throw new InvalidCpfException("Invalid CPF.");
        }
        if (associateRepository.findByCpf(cpf).isPresent()) {
            throw new InvalidCpfException("There is already an Associate registered with this CPF.");
        }
    }

    @Override
    public List<ResponseAssociateDTO> findAll() {
        return associateRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(toList());
    }

    @Override
    public ResponseAssociateDTO update(UpdateAssociateDTO updateAssociateDTO) {
        Associate associate = findById(updateAssociateDTO.getId());
        associate.update(updateAssociateDTO);
        return toResponseDto(associateRepository.save(associate));
    }

    @Override
    public void delete(UUID id) {
        associateRepository.deleteById(id);
    }

    private ResponseAssociateDTO toResponseDto(Associate associate) {
        return modelMapper.map(associate, ResponseAssociateDTO.class);
    }
}
