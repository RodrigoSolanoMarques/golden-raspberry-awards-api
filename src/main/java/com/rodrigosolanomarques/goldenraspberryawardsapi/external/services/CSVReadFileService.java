package com.rodrigosolanomarques.goldenraspberryawardsapi.external.services;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator.EWinner;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services.ReadFileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReadFileService implements ReadFileService {

    @Override
    public List<FilmDTO> read(String path) {

        final List<FilmDTO> films = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            String line = bufferedReader.readLine();

            if (!existLine(line)) {
                return films;
            }

            if (isHeader(line)) {
                line = bufferedReader.readLine();
            }

            while (existLine(line)) {
                FilmDTO filmDto = mapper(line);
                films.add(filmDto);

                line = bufferedReader.readLine();
            }

            return films;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean existLine(String line) {
        if (line == null) {
            return false;
        }
        return !line.isBlank();
    }

    private boolean isHeader(String header) {
        String[] cvs = header.split(";");
        return !cvs[0].matches("[0-9]*");
    }

    private FilmDTO mapper(String line) {

        String[] csv = parseLine(line);

        if (csv.length == 4) {
            return new FilmDTO(Integer.parseInt(csv[0]), csv[1], csv[2], csv[3], EWinner.NO);
        }

        return new FilmDTO(Integer.parseInt(csv[0]), csv[1], csv[2], csv[3], EWinner.fromString(csv[4]));
    }

    private String[] parseLine(String line) {
        return line.split(";");
    }

}