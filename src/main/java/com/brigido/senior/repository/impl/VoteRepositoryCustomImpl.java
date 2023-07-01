package com.brigido.senior.repository.impl;

import com.brigido.senior.dto.query.*;
import com.brigido.senior.entity.*;
import com.brigido.senior.repository.VoteRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import static java.util.Objects.*;

public class VoteRepositoryCustomImpl implements VoteRepositoryCustom {

    private final QVote vote = QVote.vote;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ResponseAssociateVoteDTO> findVotesPerSchedule(VotesPerScheduleFilterDTO votesPerScheduleFilterDTO) {
        JPAQuery<ResponseAssociateVoteDTO> query =  new JPAQueryFactory(em)
                .select(Projections.constructor(ResponseAssociateVoteDTO.class,
                            vote.associate.id,
                            vote.associate.name,
                            vote.voteEnum
                        )
                )
                .from(vote)
                .where(vote.schedule.id.eq(votesPerScheduleFilterDTO.getScheduleId()));

        if (nonNull(votesPerScheduleFilterDTO.getVoteEnum())) {
            query.where(vote.voteEnum.eq(votesPerScheduleFilterDTO.getVoteEnum()));
        }

        return query.fetch();
    }
}
