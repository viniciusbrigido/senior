package com.brigido.senior.dto.response;

import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseAssociateDTO {
    private UUID id;
    private String name;
    private String cpf;
}