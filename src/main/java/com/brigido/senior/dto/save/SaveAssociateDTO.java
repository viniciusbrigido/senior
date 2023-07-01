package com.brigido.senior.dto.save;

import com.brigido.senior.util.Util;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class SaveAssociateDTO {

    @NotNull
    private String name;
    @NotNull
    private String cpf;

    public String getCpf() {
        return Util.normalizeCpf(cpf);
    }
}
