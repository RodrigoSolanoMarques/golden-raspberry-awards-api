package com.rodrigosolanomarques.goldenraspberryawardsapi.batch.job.step;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class CSVFileReader extends FlatFileItemReader<FilmDTO> {

    private final static String FILE_PATH = "file/movielist.csv";

    public CSVFileReader(FilmFieldSetMapper filmFieldSetMapper) {
        this.setResource(new PathResource(FILE_PATH));
        this.setLinesToSkip(1);

        var defaultLineMapper = new DefaultLineMapper<FilmDTO>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(";");
        delimitedLineTokenizer.setNames("year", "title", "studios", "producers", "winner");
        delimitedLineTokenizer.setStrict(false);

        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(filmFieldSetMapper);

        this.setLineMapper(defaultLineMapper);
    }
}
