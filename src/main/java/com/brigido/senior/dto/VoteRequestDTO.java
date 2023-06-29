package com.brigido.senior.dto;

import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteRequestDTO {

    private String vote;
    private Date voteAt;
    private UUID scheduleId;
    private UUID associateId;
}
