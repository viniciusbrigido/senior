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
    private String initDate;
    private String endDate;
    private List<ResponseAssociateVoteDTO> associates;

    public VotesPerScheduleDTO(UUID id, String title, String description, LocalDateTime initDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.initDate = initDate.toString();
        this.endDate = endDate.toString();
    }
}
