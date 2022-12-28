package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job;

import com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step.MigrateCSVFileDataStepConfig;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class MigrateCSVFileDataJobConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private MigrateCSVFileDataStepConfig migrateCSVFileDataStepConfig;

    @Bean
    public Job migrateCSVFileDataJob(Step migrateCSVFileDataStep) {
        return jobBuilderFactory
                .get("migrateCSVFileDataJob")
                .start(migrateCSVFileDataStep)
                .build();
    }
}
