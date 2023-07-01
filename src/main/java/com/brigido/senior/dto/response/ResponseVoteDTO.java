package com.brigido.senior.dto.response;

import com.brigido.senior.enumeration.VoteEnum;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseVoteDTO {
    private UUID id;
    private VoteEnum voteEnum;
    private LocalDateTime voteAt;
    private ResponseScheduleDTO schedule;
    private ResponseAssociateDTO associate;
}
