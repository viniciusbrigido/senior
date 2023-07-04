package com.brigido.senior.service;

import com.brigido.senior.dto.save.SaveAssociateDTO;
import com.brigido.senior.dto.response.ResponseAssociateDTO;
import com.brigido.senior.dto.update.UpdateAssociateDTO;
import com.brigido.senior.entity.Associate;
import java.util.List;
import java.util.UUID;

public interface AssociateService {

    ResponseAssociateDTO findByIdDTO(UUID id);
    Associate findById(UUID id);
    ResponseAssociateDTO save(SaveAssociateDTO saveAssociateDTO);
    List<ResponseAssociateDTO> saveAll(List<SaveAssociateDTO> saveAssociateDTOList);
    List<ResponseAssociateDTO> findAll();
    ResponseAssociateDTO update(UpdateAssociateDTO updateAssociateDTO);
    void delete(UUID id);
}
