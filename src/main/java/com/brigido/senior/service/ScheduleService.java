package com.brigido.senior.service;

import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import com.brigido.senior.dto.save.SaveScheduleDTO;
import com.brigido.senior.dto.response.ResponseScheduleDTO;
import com.brigido.senior.dto.update.UpdateScheduleDTO;
import com.brigido.senior.entity.Schedule;
import java.util.List;
import java.util.UUID;

public interface ScheduleService {

    ResponseScheduleDTO findByIdDTO(UUID id);
    Schedule findById(UUID id);
    ResponseScheduleDTO save(SaveScheduleDTO saveScheduleDTO);
    List<ResponseScheduleDTO> findAll();
    ResponseScheduleDTO update(UpdateScheduleDTO updateScheduleDTO);
    void delete(UUID id);
    List<ScheduleResultDTO> findScheduleResults(ScheduleResultFilterDTO scheduleResultFilterDTO);
}
