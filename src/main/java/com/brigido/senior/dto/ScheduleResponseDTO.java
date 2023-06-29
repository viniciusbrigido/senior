package com.brigido.senior.dto;

import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter
public class ScheduleResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private Date createAt;
}
