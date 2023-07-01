package com.brigido.senior.repository;

import com.brigido.senior.dto.query.*;
import java.util.List;

public interface VoteRepositoryCustom {
    List<ResponseAssociateVoteDTO> findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO);
}
