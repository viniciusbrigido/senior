package com.brigido.senior.service.impl;

import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import com.brigido.senior.dto.save.SaveScheduleDTO;
import com.brigido.senior.dto.response.ResponseScheduleDTO;
import com.brigido.senior.dto.update.UpdateScheduleDTO;
import com.brigido.senior.entity.Schedule;
import com.brigido.senior.exception.NotFoundEntityException;
import com.brigido.senior.repository.ScheduleRepository;
import com.brigido.senior.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseScheduleDTO findByIdDTO(UUID id) {
        return toResponseDto(findById(id));
    }

    @Override
    public Schedule findById(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Schedule not found."));
    }

    @Override
    public ResponseScheduleDTO save(SaveScheduleDTO saveScheduleDTO) {
        Schedule schedule = modelMapper.map(saveScheduleDTO, Schedule.class);
        return toResponseDto(scheduleRepository.save(schedule));
    }

    @Override
    public List<ResponseScheduleDTO> saveAll(List<SaveScheduleDTO> saveScheduleDTOList) {
        List<ResponseScheduleDTO> responseScheduleDTOList = new ArrayList<>();
        for (SaveScheduleDTO saveScheduleDTO : saveScheduleDTOList) {
            try {
                responseScheduleDTOList.add(save(saveScheduleDTO));
            } catch (Exception e) {
                log.error("Schedule %s Invalid. Reason: %s.".formatted(saveScheduleDTO.getTitle(), e.getMessage()));
            }
        }
        return responseScheduleDTOList;
    }

    @Override
    public List<ResponseScheduleDTO> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(toList());
    }

    @Override
    public ResponseScheduleDTO update(UpdateScheduleDTO updateScheduleDTO) {
        Schedule schedule = findById(updateScheduleDTO.getId());
        schedule.update(updateScheduleDTO);
        return toResponseDto(scheduleRepository.save(schedule));
    }

    @Override
    public void delete(UUID id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<ScheduleResultDTO> findScheduleResults(ScheduleResultFilterDTO scheduleResultFilterDTO) {
        return scheduleRepository.findScheduleResults(scheduleResultFilterDTO);
    }

    private ResponseScheduleDTO toResponseDto(Schedule schedule) {
        return modelMapper.map(schedule, ResponseScheduleDTO.class);
    }
}
