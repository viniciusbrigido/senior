package com.brigido.senior.dto.save;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class SaveScheduleDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;

    private LocalDateTime initDate;
    private Integer minutes;
}
