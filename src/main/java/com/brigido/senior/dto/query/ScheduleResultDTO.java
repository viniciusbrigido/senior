package com.brigido.senior.dto.query;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter @Setter
public class ScheduleResultDTO {
    private UUID id;
    private String title;
    private String description;
    private String initDate;
    private String endDate;
    private String result;

    public ScheduleResultDTO(UUID id, String title, String description, LocalDateTime initDate,
                             LocalDateTime endDate, String result) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.initDate = initDate.toString();
        this.endDate = endDate.toString();
        this.result = result;
    }
}
