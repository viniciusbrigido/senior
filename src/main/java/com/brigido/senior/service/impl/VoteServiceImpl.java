package com.brigido.senior.service.impl;

import com.brigido.senior.dto.query.*;
import com.brigido.senior.dto.save.SaveVoteDTO;
import com.brigido.senior.dto.response.ResponseVoteDTO;
import com.brigido.senior.dto.update.UpdateVoteDTO;
import com.brigido.senior.entity.*;
import com.brigido.senior.exception.*;
import com.brigido.senior.repository.VoteRepository;
import com.brigido.senior.rest.CpfRest;
import com.brigido.senior.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import static java.util.stream.Collectors.toList;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AssociateService associateService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CpfRest cpfRest;

    @Override
    public ResponseVoteDTO findByIdDTO(UUID id) {
        return toResponseDto(findById(id));
    }

    @Override
    public Vote findById(UUID id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("Vote not found."));
    }

    @Override
    public ResponseVoteDTO save(SaveVoteDTO saveVoteDTO) {
        Vote vote = modelMapper.map(saveVoteDTO, Vote.class);
        Associate associate = associateService.findById(saveVoteDTO.getAssociateId());
        validateAssociate(associate);
        vote.setAssociate(associate);

        Schedule schedule = scheduleService.findById(saveVoteDTO.getScheduleId());
        validateSchedule(schedule);
        vote.setSchedule(schedule);

        validateAssociateVote(saveVoteDTO.getAssociateId(), saveVoteDTO.getScheduleId());

        return toResponseDto(voteRepository.save(vote));
    }

    private void validateAssociate(Associate associate) {
        if (!cpfRest.isCpfWithPermissionToVote(associate.getCpf())) {
            throw new InvalidCpfException("CPF without voting permission.");
        }
    }

    private void validateAssociateVote(UUID associateId, UUID scheduleId) {
        if (voteRepository.existsByAssociateIdAndScheduleId(associateId, scheduleId)) {
            throw new AssociateHasAlreadyVotedException("The Associate has already voted for this Schedule.");
        }
    }

    private void validateSchedule(Schedule schedule) {
        LocalDateTime currentDate = LocalDateTime.now();
        if (!(currentDate.isAfter(schedule.getInitDate()) && currentDate.isBefore(schedule.getEndDate()))) {
            throw new ScheduleDateExpiredException("Schedule date expired.");
        }
    }

    @Override
    public List<ResponseVoteDTO> findAll() {
        return voteRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(toList());
    }

    @Override
    public ResponseVoteDTO update(UpdateVoteDTO updateVoteDTO) {
        Vote vote = findById(updateVoteDTO.getId());
        validateSchedule(vote.getSchedule());
        vote.update(updateVoteDTO);
        return toResponseDto(voteRepository.save(vote));
    }

    @Override
    public void delete(UUID id) {
        voteRepository.deleteById(id);
    }

    @Override
    public VotesPerScheduleDTO findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO) {
        return voteRepository.findVotesPerSchedule(votesPerScheduleFilterDTO);
    }

    private ResponseVoteDTO toResponseDto(Vote vote) {
        return modelMapper.map(vote, ResponseVoteDTO.class);
    }
}
