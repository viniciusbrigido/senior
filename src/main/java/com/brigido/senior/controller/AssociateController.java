package com.brigido.senior.controller;

import com.brigido.senior.dto.save.SaveAssociateDTO;
import com.brigido.senior.dto.response.ResponseAssociateDTO;
import com.brigido.senior.dto.update.UpdateAssociateDTO;
import com.brigido.senior.service.AssociateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/associate")
@Tag(name = "Associate")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @PostMapping("save")
    public ResponseEntity<ResponseAssociateDTO> save(@RequestBody SaveAssociateDTO saveAssociateDTO) {
        return ResponseEntity.ok(associateService.save(saveAssociateDTO));
    }

    @PostMapping("save-all")
    public ResponseEntity<List<ResponseAssociateDTO>> saveAll(@RequestBody List<SaveAssociateDTO> saveAssociateDTOList) {
        return ResponseEntity.ok(associateService.saveAll(saveAssociateDTOList));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAssociateDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(associateService.findByIdDTO(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        associateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<ResponseAssociateDTO> update(@RequestBody UpdateAssociateDTO updateAssociateDTO) {
        return ResponseEntity.ok(associateService.update(updateAssociateDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseAssociateDTO>> findAll() {
        return ResponseEntity.ok(associateService.findAll());
    }
}