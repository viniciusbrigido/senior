package com.brigido.senior.entity;

import com.brigido.senior.dto.VoteRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    private UUID id;

    //VALIDAR TIPAGEM
    private String vote;
    private Date voteAt;

    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Associate associate;

    public void update(VoteRequestDTO voteRequestDTO) {
        this.vote = voteRequestDTO.getVote();
    }
}

