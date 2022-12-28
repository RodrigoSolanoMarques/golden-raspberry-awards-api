package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities.FilmEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrateCSVFileDataStepConfig {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CSVFileReader csvFileReader;
    @Autowired
    private CSVFileProcessor csvFileProcessor;
    @Autowired
    private CSVFileWriter csvFileWriter;

    @Bean
    public Step migrateCSVFileDataStep() {
        return stepBuilderFactory
                .get("migrateCSVFileDataStep")
                .<FilmDTO, FilmEntity>chunk(10)
                .reader(csvFileReader)
                .processor(csvFileProcessor)
                .writer(csvFileWriter)
                .build();
    }

}
