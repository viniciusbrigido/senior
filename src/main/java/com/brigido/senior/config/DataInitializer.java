package com.brigido.senior.config;

import com.brigido.senior.dto.response.ResponseAssociateDTO;
import com.brigido.senior.dto.response.ResponseScheduleDTO;
import com.brigido.senior.dto.save.*;
import com.brigido.senior.enumeration.VoteEnum;
import com.brigido.senior.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private AssociateService associateService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private VoteService voteService;

    @PostConstruct
    public void initData() {
        ResponseAssociateDTO associate1 = associateService.save(
                SaveAssociateDTO
                    .builder()
                    .name("Vinicius")
                    .cpf("56602368800")
                    .build());

        ResponseAssociateDTO associate2 = associateService.save(
                SaveAssociateDTO
                    .builder()
                    .name("Joao")
                    .cpf("73514040117")
                    .build());

        ResponseAssociateDTO associate3 = associateService.save(
                SaveAssociateDTO
                    .builder()
                    .name("Maria")
                    .cpf("11407646672")
                    .build());

        ResponseScheduleDTO scheduleRequestDTO = scheduleService.save(SaveScheduleDTO.builder()
                .title("Schedule")
                .description("Teste")
                .minutes(25)
                .build());


        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO.getId())
                .associateId(associate1.getId())
                .voteEnum(VoteEnum.SIM)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO.getId())
                .associateId(associate2.getId())
                .voteEnum(VoteEnum.SIM)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO.getId())
                .associateId(associate3.getId())
                .voteEnum(VoteEnum.NAO)
                .build());

        ResponseScheduleDTO scheduleRequestDTO2 = scheduleService.save(SaveScheduleDTO.builder()
                .title("Schedule Não")
                .description("Teste")
                .minutes(25)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO2.getId())
                .associateId(associate1.getId())
                .voteEnum(VoteEnum.SIM)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO2.getId())
                .associateId(associate2.getId())
                .voteEnum(VoteEnum.NAO)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO2.getId())
                .associateId(associate3.getId())
                .voteEnum(VoteEnum.NAO)
                .build());

        ResponseScheduleDTO scheduleRequestDTO3 = scheduleService.save(SaveScheduleDTO.builder()
                .title("Schedule TIE")
                .description("Teste")
                .minutes(25)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO3.getId())
                .associateId(associate1.getId())
                .voteEnum(VoteEnum.SIM)
                .build());

        voteService.save(SaveVoteDTO.builder()
                .scheduleId(scheduleRequestDTO3.getId())
                .associateId(associate2.getId())
                .voteEnum(VoteEnum.NAO)
                .build());
    }
}
