package com.brigido.senior.service.impl;

import com.brigido.senior.dto.ScheduleRequestDTO;
import com.brigido.senior.dto.ScheduleResponseDTO;
import com.brigido.senior.entity.Schedule;
import com.brigido.senior.repository.ScheduleRepository;
import com.brigido.senior.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ScheduleResponseDTO findByIdDTO(UUID id) {
        return modelMapper.map(findById(id), ScheduleResponseDTO.class);
    }

    @Override
    public Schedule findById(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada."));
    }

    @Override
    public ScheduleResponseDTO save(ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = modelMapper.map(scheduleRequestDTO, Schedule.class);
        return modelMapper.map(scheduleRepository.save(schedule), ScheduleResponseDTO.class);
    }

    @Override
    public List<ScheduleResponseDTO> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleResponseDTO.class))
                .collect(toList());
    }

    @Override
    public ScheduleResponseDTO update(UUID id, ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = findById(id);
        schedule.update(scheduleRequestDTO);
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
    }

    @Override
    public void delete(UUID id) {
        scheduleRepository.deleteById(id);
    }
}
