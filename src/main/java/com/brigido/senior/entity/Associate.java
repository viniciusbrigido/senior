package com.brigido.senior.entity;

import com.brigido.senior.dto.AssociateRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
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

    @Column(updatable = false, nullable = false, unique = true)
    private String cpf;

    public void update(AssociateRequestDTO associateRequestDTO) {
        this.name = associateRequestDTO.getName();
    }
}
