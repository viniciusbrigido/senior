package com.brigido.senior.controller;

import com.brigido.senior.dto.query.VotesPerScheduleDTO;
import com.brigido.senior.dto.query.VotesPerScheduleFilterDTO;
import com.brigido.senior.dto.response.ResponseVoteDTO;
import com.brigido.senior.dto.save.SaveVoteDTO;
import com.brigido.senior.dto.update.UpdateVoteDTO;
import com.brigido.senior.service.VoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/vote")
@Tag(name = "Vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("save")
    public ResponseEntity<ResponseVoteDTO> save(@RequestBody SaveVoteDTO saveVoteDTO) {
        return ResponseEntity.ok(voteService.save(saveVoteDTO));
    }

    @PostMapping("save-all")
    public ResponseEntity<List<ResponseVoteDTO>> saveAll(@RequestBody List<SaveVoteDTO> saveVoteDTOList) {
        return ResponseEntity.ok(voteService.saveAll(saveVoteDTOList));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseVoteDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(voteService.findByIdDTO(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        voteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update")
    public ResponseEntity<ResponseVoteDTO> update(@RequestBody UpdateVoteDTO updateVoteDTO) {
        return ResponseEntity.ok(voteService.update(updateVoteDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseVoteDTO>> findAll() {
        return ResponseEntity.ok(voteService.findAll());
    }

    @GetMapping("find-votes-per-schedule")
    public ResponseEntity<VotesPerScheduleDTO> findVotesPerSchedule(@RequestBody VotesPerScheduleFilterDTO votesPerScheduleFilterDTO) {
        return ResponseEntity.ok(voteService.findVotesPerSchedule(votesPerScheduleFilterDTO));
    }
}
