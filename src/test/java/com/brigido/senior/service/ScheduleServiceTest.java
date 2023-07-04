package com.brigido.senior.service;

import com.brigido.senior.BaseTests;
import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import com.brigido.senior.dto.response.ResponseScheduleDTO;
import com.brigido.senior.dto.save.SaveScheduleDTO;
import com.brigido.senior.dto.update.UpdateScheduleDTO;
import com.brigido.senior.exception.NotFoundEntityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BaseTests.TestConfig.class)
@RunWith(SpringRunner.class)
public class ScheduleServiceTest extends BaseTests {

    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void saveScheduleTest() {
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());
        assertThat(schedule).isNotNull();
        assertThat(schedule.getId()).isNotNull();
    }

    @Test
    public void saveScheduleListTest() {
        List<ResponseScheduleDTO> responseScheduleDTOList = scheduleService.saveAll(List.of(getSaveScheduleDTO(), getSaveSchedule2DTO()));

        responseScheduleDTOList.forEach(schedule -> {
            assertThat(schedule).isNotNull();
            assertThat(schedule.getId()).isNotNull();
        });
    }

    @Test
    public void deleteScheduleTest() {
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());
        assertThatCode(() -> scheduleService.delete(schedule.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findScheduleByIdTest() {
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());
        assertThatCode(() -> scheduleService.findById(schedule.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findScheduleByIdNotFoundTest() {
        assertThatThrownBy(() -> scheduleService.findById(UUID.randomUUID())).isInstanceOf(NotFoundEntityException.class);
    }

    @Test
    public void findAllTest() {
        assertThatCode(() -> scheduleService.findAll()).doesNotThrowAnyException();
    }

    @Test
    public void findScheduleResultsTest() {
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());
        ScheduleResultFilterDTO scheduleResultFilterDTO = ScheduleResultFilterDTO.builder().scheduleId(schedule.getId()).build();
        List<ScheduleResultDTO> scheduleResultDTOList = scheduleService.findScheduleResults(scheduleResultFilterDTO);

        assertThat(scheduleResultDTOList).isNotNull();
        assertThat(scheduleResultDTOList.get(0)).isNotNull();
        assertThat(scheduleResultDTOList.get(0).getResult()).isEqualTo("TIE");
    }

    @Test
    public void updateScheduleTest() {
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());
        String newTitle = "New Title";
        UpdateScheduleDTO updateScheduleDTO = UpdateScheduleDTO.builder().id(schedule.getId()).title(newTitle).build();
        scheduleService.update(updateScheduleDTO);

        ResponseScheduleDTO scheduleUpdate = scheduleService.findByIdDTO(schedule.getId());
        assertThat(scheduleUpdate).isNotNull();
        assertThat(scheduleUpdate.getId()).isNotNull();
        assertThat(scheduleUpdate.getTitle()).isEqualTo(newTitle);
    }

    private SaveScheduleDTO getSaveScheduleDTO() {
        return SaveScheduleDTO.builder()
                .title("Title")
                .description("Desc")
                .initDate(LocalDateTime.now())
                .minutes(5)
                .build();
    }

    private SaveScheduleDTO getSaveSchedule2DTO() {
        return SaveScheduleDTO.builder()
                .title("Title 2")
                .description("Desc 2")
                .initDate(LocalDateTime.now())
                .minutes(5)
                .build();
    }
}
