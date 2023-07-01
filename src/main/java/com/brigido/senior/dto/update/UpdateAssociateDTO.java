package com.brigido.senior.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UpdateAssociateDTO {

    @NotNull
    private UUID id;

    private String name;
}
