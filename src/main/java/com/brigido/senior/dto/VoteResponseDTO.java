package com.brigido.senior.dto;

import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter
public class VoteResponseDTO {
    private UUID id;
    private String vote;
    private Date voteAt;
    private ScheduleResponseDTO schedule;
    private AssociateResponseDTO associate;
}
