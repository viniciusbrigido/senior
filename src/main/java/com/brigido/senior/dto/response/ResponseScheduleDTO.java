package com.brigido.senior.dto.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseScheduleDTO {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime initDate;
    private LocalDateTime endDate;
}
