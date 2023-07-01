package com.brigido.senior.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UpdateScheduleDTO {

    @NotNull
    private UUID id;

    private String title;
    private String description;
    private LocalDateTime initDate;
    private Integer minutes;
}
