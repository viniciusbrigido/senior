package com.brigido.senior.service;

import com.brigido.senior.dto.query.*;
import com.brigido.senior.dto.save.SaveVoteDTO;
import com.brigido.senior.dto.response.ResponseVoteDTO;
import com.brigido.senior.dto.update.UpdateVoteDTO;
import com.brigido.senior.entity.Vote;
import java.util.List;
import java.util.UUID;

public interface VoteService {

    ResponseVoteDTO findByIdDTO(UUID id);
    Vote findById(UUID id);
    ResponseVoteDTO save(SaveVoteDTO saveVoteDTO);
    List<ResponseVoteDTO> findAll();
    ResponseVoteDTO update(UpdateVoteDTO updateVoteDTO);
    void delete(UUID id);
    VotesPerScheduleDTO findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO);
}
