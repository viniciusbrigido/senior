package com.brigido.senior.repository;

import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import java.util.List;

public interface ScheduleRepositoryCustom {
    List<ScheduleResultDTO> findScheduleResults(ScheduleResultFilterDTO scheduleResultFilterDTO);
}
