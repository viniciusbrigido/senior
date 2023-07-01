package com.brigido.senior.dto.query;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class VotesPerScheduleDTO {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime initDate;
    private LocalDateTime endDate;
    private List<ResponseAssociateVoteDTO> associates;
}
