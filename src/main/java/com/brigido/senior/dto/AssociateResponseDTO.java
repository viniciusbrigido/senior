package com.brigido.senior.dto;

import lombok.*;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor
@Getter
public class AssociateResponseDTO {
    private UUID id;
    private String name;
    private String cpf;
}