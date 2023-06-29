package com.brigido.senior.resource;

import com.brigido.senior.dto.ScheduleRequestDTO;
import com.brigido.senior.dto.ScheduleResponseDTO;
import com.brigido.senior.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("save")
    public ResponseEntity<ScheduleResponseDTO> save(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(scheduleService.save(scheduleRequestDTO));
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<ScheduleResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(scheduleService.findByIdDTO(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ScheduleResponseDTO> update(@PathVariable UUID id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        return ResponseEntity.ok(scheduleService.update(id, scheduleRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }
}
