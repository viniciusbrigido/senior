package com.brigido.senior.repository.impl;

import com.brigido.senior.dto.query.*;
import com.brigido.senior.entity.*;
import com.brigido.senior.repository.VoteRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import static java.util.Objects.*;

public class VoteRepositoryCustomImpl implements VoteRepositoryCustom {

    private final QVote vote = QVote.vote;
    private final QSchedule schedule = QSchedule.schedule;

    @PersistenceContext
    private EntityManager em;

    @Override
    public VotesPerScheduleDTO findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO) {
        VotesPerScheduleDTO votesPerScheduleDTO = new JPAQueryFactory(em)
                .select(Projections.constructor(VotesPerScheduleDTO.class,
                                schedule.id,
                                schedule.title,
                                schedule.description,
                                schedule.initDate,
                                schedule.endDate
                        )
                )
                .from(schedule)
                .where(schedule.id.eq(votesPerScheduleFilterDTO.getScheduleId()))
                .fetchOne();

        JPAQuery<ResponseAssociateVoteDTO> queryVotes =  new JPAQueryFactory(em)
                .select(Projections.constructor(ResponseAssociateVoteDTO.class,
                            vote.associate.id,
                            vote.associate.name,
                            vote.voteEnum
                        )
                )
                .from(vote)
                .where(vote.schedule.id.eq(votesPerScheduleFilterDTO.getScheduleId()));

        if (nonNull(votesPerScheduleFilterDTO.getVoteEnum())) {
            queryVotes.where(vote.voteEnum.eq(votesPerScheduleFilterDTO.getVoteEnum()));
        }

        votesPerScheduleDTO.setAssociates(queryVotes.fetch());

        return votesPerScheduleDTO;
    }
}
