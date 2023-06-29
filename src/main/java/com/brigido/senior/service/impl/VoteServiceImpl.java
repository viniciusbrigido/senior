package com.brigido.senior.service.impl;

import com.brigido.senior.dto.VoteRequestDTO;
import com.brigido.senior.dto.VoteResponseDTO;
import com.brigido.senior.entity.Vote;
import com.brigido.senior.repository.VoteRepository;
import com.brigido.senior.service.VoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VoteResponseDTO findByIdDTO(UUID id) {
        return modelMapper.map(findById(id), VoteResponseDTO.class);
    }

    @Override
    public Vote findById(UUID id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voto não encontrada."));
    }

    @Override
    public VoteResponseDTO save(VoteRequestDTO voteRequestDTO) {
        Vote vote = modelMapper.map(voteRequestDTO, Vote.class);
        return modelMapper.map(voteRepository.save(vote), VoteResponseDTO.class);
    }

    @Override
    public List<VoteResponseDTO> findAll() {
        return voteRepository.findAll()
                .stream()
                .map(vote -> modelMapper.map(vote, VoteResponseDTO.class))
                .collect(toList());
    }

    @Override
    public VoteResponseDTO update(UUID id, VoteRequestDTO voteRequestDTO) {
        Vote vote = findById(id);
        vote.update(voteRequestDTO);
        return modelMapper.map(vote, VoteResponseDTO.class);
    }

    @Override
    public void delete(UUID id) {
        voteRepository.deleteById(id);
    }
}
