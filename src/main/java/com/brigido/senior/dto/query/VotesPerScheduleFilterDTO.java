package com.brigido.senior.dto.query;

import com.brigido.senior.enumeration.VoteEnum;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class VotesPerScheduleFilterDTO {
    private UUID scheduleId;
    private VoteEnum voteEnum;
}
