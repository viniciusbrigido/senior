package com.brigido.senior.dto.query;

import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ScheduleResultFilterDTO {
    private UUID scheduleId;
}
