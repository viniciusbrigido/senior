package com.brigido.senior.service;

import com.brigido.senior.dto.VoteRequestDTO;
import com.brigido.senior.dto.VoteResponseDTO;
import com.brigido.senior.entity.Vote;
import java.util.List;
import java.util.UUID;

public interface VoteService {

    VoteResponseDTO findByIdDTO(UUID id);
    Vote findById(UUID id);
    VoteResponseDTO save(VoteRequestDTO voteRequestDTO);
    List<VoteResponseDTO> findAll();
    VoteResponseDTO update(UUID id, VoteRequestDTO voteRequestDTO);
    void delete(UUID id);
}
