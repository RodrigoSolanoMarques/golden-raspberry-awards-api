package com.rodrigosolanomarques.goldenraspberryawardsapi;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinnersIntervalDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinningProducerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoldenRaspberryAwardsApiApplicationTests {

    private final static String FILE_PATH = "file/movielist.csv";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldGetminMaxWinnersIntervalList() throws FileNotFoundException {
        ResponseEntity<WinnersIntervalDTO> response = this.testRestTemplate
                .exchange("/film//min-max-winners-interval", HttpMethod.GET, null, WinnersIntervalDTO.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        WinnersIntervalDTO winnersIntervalDTO = response.getBody();
        assertNotNull(winnersIntervalDTO);

        List<WinningProducerDTO> maxList = winnersIntervalDTO.max();
        List<WinningProducerDTO> minList = winnersIntervalDTO.min();

        assertNotNull(maxList);
        assertNotNull(minList);

        String csv = csvFileRead();
        assertNotNull(csv);

        for (WinningProducerDTO producer : maxList) {
            assertTrue(csv.contains(producer.producer()));
        }

        for (WinningProducerDTO producer : minList) {
            assertTrue(csv.contains(producer.producer()));
        }
    }

    private String csvFileRead() throws FileNotFoundException {
        FileReader fileReader = new FileReader(FILE_PATH);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines().map(String::toString).collect(Collectors.joining());
    }

}