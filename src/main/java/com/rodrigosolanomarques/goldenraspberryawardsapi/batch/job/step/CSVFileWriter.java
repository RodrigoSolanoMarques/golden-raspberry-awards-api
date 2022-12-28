package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities.FilmEntity;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.repositories.FilmRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVFileWriter implements ItemWriter<FilmEntity> {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public void write(List<? extends FilmEntity> films) throws Exception {
        filmRepository.saveAll(films);
    }
}
