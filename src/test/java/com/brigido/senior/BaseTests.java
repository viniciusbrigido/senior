package com.brigido.senior;

import com.brigido.senior.service.*;
import com.brigido.senior.service.impl.*;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@DataJpaTest(showSql = false)
public class BaseTests {

    @TestConfiguration
    public static class TestConfig {

        @Bean
        ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            return modelMapper;
        }

        @Bean
        Gson gson() {
            return new Gson();
        }

        @Bean
        VoteService voteService() {
            return new VoteServiceImpl();
        }

        @Bean
        ScheduleService scheduleService() {
            return new ScheduleServiceImpl();
        }

        @Bean
        AssociateService associateService() {
            return new AssociateServiceImpl();
        }

    }
}