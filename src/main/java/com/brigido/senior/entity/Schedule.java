package com.brigido.senior.entity;

import com.brigido.senior.dto.update.UpdateScheduleDTO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import static java.util.Objects.*;
import static java.util.Optional.*;

@Getter @Setter
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

    @Column(nullable = false, name = "init_date")
    private LocalDateTime initDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Transient
    private Integer minutes;

    @PrePersist
    protected void onCreate() {
        ofNullable(initDate).orElseGet(() -> initDate = LocalDateTime.now());
        ofNullable(endDate).orElseGet(() -> endDate = initDate.plusMinutes(ofNullable(minutes).orElse(1)));
    }

    public void update(UpdateScheduleDTO updateScheduleDTO) {
        title = ofNullable(updateScheduleDTO.getTitle()).orElse(title);
        description = ofNullable(updateScheduleDTO.getDescription()).orElse(description);
        if (nonNull(updateScheduleDTO.getInitDate())) {
            initDate = updateScheduleDTO.getInitDate();
            endDate = initDate.plusMinutes(ofNullable(minutes).orElse(1));
        }
    }
}

