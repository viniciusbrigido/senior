package com.brigido.senior.service;

import com.brigido.senior.dto.ScheduleRequestDTO;
import com.brigido.senior.dto.ScheduleResponseDTO;
import com.brigido.senior.entity.Schedule;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    ScheduleResponseDTO findByIdDTO(UUID id);
    Schedule findById(UUID id);
    ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO);
    List<ScheduleResponseDTO> findAll();
    ScheduleResponseDTO update(UUID id, ScheduleRequestDTO scheduleRequestDTO);
    void delete(UUID id);
}
