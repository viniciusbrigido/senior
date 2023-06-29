package com.brigido.senior.service;

import com.brigido.senior.dto.AssociateRequestDTO;
import com.brigido.senior.dto.AssociateResponseDTO;
import com.brigido.senior.entity.Associate;
import java.util.List;
import java.util.UUID;

public interface AssociateService {

    AssociateResponseDTO findByIdDTO(UUID id);
    Associate findById(UUID id);
    AssociateResponseDTO save(AssociateRequestDTO associateRequestDTO);
    List<AssociateResponseDTO> findAll();
    AssociateResponseDTO update(UUID id, AssociateRequestDTO associateRequestDTO);
    void delete(UUID id);
}
