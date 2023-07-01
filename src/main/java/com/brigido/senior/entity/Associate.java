package com.brigido.senior.entity;

import com.brigido.senior.dto.update.UpdateAssociateDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "associate")
public class Associate {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    public void update(UpdateAssociateDTO updateAssociateDTO) {
        this.name = updateAssociateDTO.getName();
    }
}
