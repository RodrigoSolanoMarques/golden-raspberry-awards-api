package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities.FilmEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CSVFileProcessor implements ItemProcessor<FilmDTO, FilmEntity> {

    @Override
    public FilmEntity process(FilmDTO filmDTO) throws Exception {
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setYear(filmDTO.year());
        filmEntity.setTitle(filmDTO.title());
        filmEntity.setStudios(filmDTO.studios());
        filmEntity.setProducers(filmDTO.producers());
        filmEntity.setWinner(filmDTO.winner());
        return filmEntity;
    }
}
