package com.brigido.senior.entity;

import com.brigido.senior.dto.update.UpdateVoteDTO;
import com.brigido.senior.enumeration.VoteEnum;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private VoteEnum voteEnum;

    private LocalDateTime voteAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private Schedule schedule;

    @ManyToOne
    private Associate associate;

    @PrePersist
    protected void onCreate() {
        LocalDateTime currentDate = LocalDateTime.now();
        voteAt = currentDate;
        updatedAt = currentDate;
    }

    public void update(UpdateVoteDTO updateVoteDTO) {
        voteEnum = updateVoteDTO.getVoteEnum();
        updatedAt = LocalDateTime.now();
    }
}

