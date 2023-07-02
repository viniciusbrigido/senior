package com.brigido.senior.repository;

import com.brigido.senior.dto.query.*;

public interface VoteRepositoryCustom {
    VotesPerScheduleDTO findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO);
}
