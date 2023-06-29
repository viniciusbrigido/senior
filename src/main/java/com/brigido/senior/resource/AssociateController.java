package com.brigido.senior.resource;

import com.brigido.senior.dto.AssociateRequestDTO;
import com.brigido.senior.dto.AssociateResponseDTO;
import com.brigido.senior.service.AssociateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/associate")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @PostMapping("save")
    public ResponseEntity<AssociateResponseDTO> save(@RequestBody AssociateRequestDTO associateRequestDTO) {
        return ResponseEntity.ok(associateService.save(associateRequestDTO));
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<AssociateResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(associateService.findByIdDTO(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        associateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AssociateResponseDTO> update(@PathVariable UUID id, @RequestBody AssociateRequestDTO associateRequestDTO) {
        return ResponseEntity.ok(associateService.update(id, associateRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<AssociateResponseDTO>> findAll() {
        return ResponseEntity.ok(associateService.findAll());
    }
}