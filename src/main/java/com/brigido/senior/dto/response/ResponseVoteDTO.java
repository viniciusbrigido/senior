package com.brigido.senior.dto.response;

import com.brigido.senior.enumeration.VoteEnum;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseVoteDTO {
    private UUID id;
    private VoteEnum voteEnum;
    private String voteAt;
    private ResponseScheduleDTO schedule;
    private ResponseAssociateDTO associate;
}
