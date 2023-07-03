package com.brigido.senior.repository.impl;

import com.brigido.senior.dto.query.ScheduleResultDTO;
import com.brigido.senior.dto.query.ScheduleResultFilterDTO;
import com.brigido.senior.entity.QSchedule;
import com.brigido.senior.entity.QVote;
import com.brigido.senior.enumeration.VoteEnum;
import com.brigido.senior.repository.ScheduleRepositoryCustom;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import static java.util.Objects.*;

public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private final QVote vote = QVote.vote;
    private final QSchedule schedule = QSchedule.schedule;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<ScheduleResultDTO> findScheduleResults(ScheduleResultFilterDTO scheduleResultFilterDTO) {
        JPQLQuery<Long> simVoteCount = JPAExpressions
                .select(vote.id.count())
                .from(vote)
                .where(vote.schedule.id.eq(schedule.id)
                        .and(vote.voteEnum.eq(VoteEnum.SIM)));

        JPQLQuery<Long> naoVoteCount = JPAExpressions
                .select(vote.id.count())
                .from(vote)
                .where(vote.schedule.id.eq(schedule.id)
                        .and(vote.voteEnum.eq(VoteEnum.NAO)));

        StringExpression result = new CaseBuilder()
                .when(simVoteCount.gt(naoVoteCount)).then("SIM")
                .when(naoVoteCount.gt(simVoteCount)).then("NAO")
                .otherwise("TIE");


        JPAQuery<ScheduleResultDTO> query = new JPAQueryFactory(em)
                .select(Projections.constructor(ScheduleResultDTO.class,
                        schedule.id,
                        schedule.title,
                        schedule.description,
                        schedule.initDate,
                        schedule.endDate,
                        result))
                .from(schedule)
                .leftJoin(vote).on(vote.schedule.id.eq(schedule.id))
                .groupBy(schedule.id, schedule.title, schedule.description, schedule.initDate, schedule.endDate);

        if (nonNull(scheduleResultFilterDTO.getScheduleId())) {
            query.where(schedule.id.eq(scheduleResultFilterDTO.getScheduleId()));
        }

        return query.fetch();
    }
}
