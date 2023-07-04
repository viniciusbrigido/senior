package com.brigido.senior.service;

import com.brigido.senior.BaseTests;
import com.brigido.senior.dto.query.VotesPerScheduleDTO;
import com.brigido.senior.dto.query.VotesPerScheduleFilterDTO;
import com.brigido.senior.dto.response.*;
import com.brigido.senior.dto.save.*;
import com.brigido.senior.dto.update.UpdateVoteDTO;
import com.brigido.senior.enumeration.VoteEnum;
import com.brigido.senior.exception.NotFoundEntityException;
import com.brigido.senior.exception.ScheduleDateExpiredException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static com.brigido.senior.enumeration.VoteEnum.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(BaseTests.TestConfig.class)
@RunWith(SpringRunner.class)
public class VoteServiceTest extends BaseTests {

    @Autowired
    private VoteService voteService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private AssociateService associateService;

    @Test
    public void saveVoteTest() {
        ResponseVoteDTO vote = voteService.save(getSaveVoteNaoDTO());
        assertThat(vote).isNotNull();
        assertThat(vote.getId()).isNotNull();
    }

    @Test
    public void saveVoteListTest() {
        List<ResponseVoteDTO> responseVoteDTOList = voteService.saveAll(List.of(getSaveVoteNaoDTO(), getSaveVote2DTO()));

        responseVoteDTOList.forEach(vote -> {
            assertThat(vote).isNotNull();
            assertThat(vote.getId()).isNotNull();
        });
    }

    @Test
    public void deleteVoteTest() {
        ResponseVoteDTO vote = voteService.save(getSaveVoteNaoDTO());
        assertThatCode(() -> voteService.delete(vote.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findVoteByIdTest() {
        ResponseVoteDTO vote = voteService.save(getSaveVoteNaoDTO());
        assertThatCode(() -> voteService.findById(vote.getId())).doesNotThrowAnyException();
    }

    @Test
    public void findVoteByIdNotFoundTest() {
        assertThatThrownBy(() -> voteService.findById(UUID.randomUUID())).isInstanceOf(NotFoundEntityException.class);
    }

    @Test
    public void findAllTest() {
        assertThatCode(() -> voteService.findAll()).doesNotThrowAnyException();
    }

    @Test
    public void findVotesPerScheduleTest() {
        ResponseVoteDTO vote = voteService.save(getSaveVoteNaoDTO());

        VotesPerScheduleFilterDTO votesPerScheduleFilterDTO = VotesPerScheduleFilterDTO.builder()
                .scheduleId(vote.getSchedule().getId())
                .voteEnum(vote.getVoteEnum())
                .build();
        VotesPerScheduleDTO votesPerScheduleDTO = voteService.findVotesPerSchedule(votesPerScheduleFilterDTO);

        assertThat(votesPerScheduleDTO).isNotNull();
        assertThat(votesPerScheduleDTO.getAssociates()).isNotNull();
        assertThat(votesPerScheduleDTO.getAssociates().isEmpty()).isFalse();
    }


    @Test
    public void updateVoteTest() {
        ResponseVoteDTO vote = voteService.save(getSaveVoteSimDTO());
        UpdateVoteDTO updateVoteDTO = UpdateVoteDTO.builder().id(vote.getId()).voteEnum(NAO).build();
        voteService.update(updateVoteDTO);

        ResponseVoteDTO voteUpdate = voteService.findByIdDTO(vote.getId());
        assertThat(voteUpdate).isNotNull();
        assertThat(voteUpdate.getId()).isNotNull();
        assertThat(voteUpdate.getVoteEnum()).isEqualTo(NAO);
    }

    @Test
    public void scheduleDateExpiredTest() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleExpiredDTO());

        SaveVoteDTO saveVoteDTO = SaveVoteDTO.builder()
                .voteEnum(SIM)
                .associateId(associate.getId())
                .scheduleId(schedule.getId())
                .build();

        assertThatThrownBy(() -> voteService.save(saveVoteDTO)).isInstanceOf(ScheduleDateExpiredException.class);
    }

    private SaveVoteDTO getSaveVoteNaoDTO() {
        return getSaveVoteDTO(NAO);
    }

    private SaveVoteDTO getSaveVoteSimDTO() {
        return getSaveVoteDTO(SIM);
    }

    private SaveVoteDTO getSaveVoteDTO(VoteEnum voteEnum) {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociateDTO());
        ResponseScheduleDTO schedule = scheduleService.save(getSaveScheduleDTO());

        return SaveVoteDTO.builder()
                .voteEnum(voteEnum)
                .associateId(associate.getId())
                .scheduleId(schedule.getId())
                .build();
    }

    private SaveAssociateDTO getSaveAssociateDTO() {
        return SaveAssociateDTO.builder()
                .name("Associate")
                .cpf("27459443638")
                .build();
    }

    private SaveScheduleDTO getSaveScheduleDTO() {
        return SaveScheduleDTO.builder()
                .title("Title")
                .description("Desc")
                .initDate(LocalDateTime.now().minusMinutes(100))
                .minutes(1000)
                .build();
    }

    private SaveVoteDTO getSaveVote2DTO() {
        ResponseAssociateDTO associate = associateService.save(getSaveAssociate2DTO());
        ResponseScheduleDTO schedule = scheduleService.save(getSaveSchedule2DTO());

        return SaveVoteDTO.builder()
                .voteEnum(SIM)
                .associateId(associate.getId())
                .scheduleId(schedule.getId())
                .build();
    }

    private SaveAssociateDTO getSaveAssociate2DTO() {
        return SaveAssociateDTO.builder()
                .name("Associate 2")
                .cpf("43125843383")
                .build();
    }

    private SaveScheduleDTO getSaveSchedule2DTO() {
        return SaveScheduleDTO.builder()
                .title("Title 2")
                .description("Desc 2")
                .initDate(LocalDateTime.now().minusMinutes(100))
                .minutes(1000)
                .build();
    }

    private SaveScheduleDTO getSaveScheduleExpiredDTO() {
        return SaveScheduleDTO.builder()
                .title("Title")
                .description("Desc")
                .initDate(LocalDateTime.of(2000, 1, 1, 1, 1))
                .minutes(100)
                .build();
    }
}
