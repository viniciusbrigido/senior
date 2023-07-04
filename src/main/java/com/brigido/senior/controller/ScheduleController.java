package com.brigido.senior.controller;

import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import com.brigido.senior.dto.save.SaveScheduleDTO;
import com.brigido.senior.dto.response.ResponseScheduleDTO;
import com.brigido.senior.dto.update.UpdateScheduleDTO;
import com.brigido.senior.service.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/schedule")
@Tag(name = "Schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("save")
    public ResponseEntity<ResponseScheduleDTO> save(@RequestBody SaveScheduleDTO saveScheduleDTO) {
        return ResponseEntity.ok(scheduleService.save(saveScheduleDTO));
    }

    @PostMapping("save-all")
    public ResponseEntity<List<ResponseScheduleDTO> > saveAll(@RequestBody List<SaveScheduleDTO> saveScheduleDTOList) {
        return ResponseEntity.ok(scheduleService.saveAll(saveScheduleDTOList));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseScheduleDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(scheduleService.findByIdDTO(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        scheduleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<ResponseScheduleDTO> update(@RequestBody UpdateScheduleDTO updateScheduleDTO) {
        return ResponseEntity.ok(scheduleService.update(updateScheduleDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseScheduleDTO>> findAll() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("find-schedule-results")
    public ResponseEntity<List<ScheduleResultDTO>> findScheduleResults(@RequestBody ScheduleResultFilterDTO scheduleResultFilterDTO) {
        return ResponseEntity.ok(scheduleService.findScheduleResults(scheduleResultFilterDTO));
    }
}
