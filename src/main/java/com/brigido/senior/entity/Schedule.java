package com.brigido.senior.entity;

import com.brigido.senior.dto.ScheduleRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false, name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    public void update(ScheduleRequestDTO scheduleRequestDTO) {
        this.title = scheduleRequestDTO.getTitle();
        this.description = scheduleRequestDTO.getDescription();
    }
}

