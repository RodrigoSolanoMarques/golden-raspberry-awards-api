package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator.EWinner;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class FilmFieldSetMapper implements FieldSetMapper<FilmDTO> {

    @Override
    public FilmDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        return new FilmDTO(
                fieldSet.readInt("year"),
                fieldSet.readString("title"),
                fieldSet.readString("studios"),
                fieldSet.readString("producers"),
                EWinner.fromString(fieldSet.readString("winner")));
    }
}
