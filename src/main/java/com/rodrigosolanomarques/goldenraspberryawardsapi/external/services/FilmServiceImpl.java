
package com.rodrigosolanomarques.goldenraspberryawardsapi.external.services;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinnersIntervalDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinningProducerDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities.FilmEntity;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.repositories.FilmRepository;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services.FilmService;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmServiceImpl implements FilmService {

    private static final int INITIAL_INTERVAL = 0;
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<FilmDTO> read(String path) {
        return readFileService.read(path);
    }

    @Override
    public WinnersIntervalDTO minMaxWinnersIntervalList() {

        List<FilmEntity> allWinningFilmes = filmRepository.findAllWinningFilm();
        List<WinningProducerDTO> winnersProducerList = createWinnersProducerList(allWinningFilmes);

        int minInterval = getMinInterval(winnersProducerList);
        int maxInterval = getMaxInterval(winnersProducerList);

        List<WinningProducerDTO> min = winnersProducerList.stream().filter(item -> item.interval() == minInterval).toList();
        List<WinningProducerDTO> max = winnersProducerList.stream().filter(item -> item.interval() == maxInterval).toList();

        return new WinnersIntervalDTO(min, max);
    }

    private List<WinningProducerDTO> createWinnersProducerList(List<FilmEntity> allWinners) {
        Map<String, WinningProducerDTO> mapAllWinners = new HashMap<>();
        List<WinningProducerDTO> winningIntervalList = new ArrayList<>();

        for (FilmEntity filmEntity : allWinners) {

            for (String producer : filmEntity.getProducers()) {

                WinningProducerDTO winningProducerDTO = mapAllWinners.get(producer);

                if (!existWinningProducer(winningProducerDTO)) {
                    winningProducerDTO = new WinningProducerDTO(
                            producer,
                            filmEntity.getYear(),
                            filmEntity.getYear(),
                            INITIAL_INTERVAL
                    );
                    mapAllWinners.put(producer, winningProducerDTO);
                    continue;
                }

                WinningProducerDTO nextYearWinningProducerDTO = new WinningProducerDTO(
                        producer,
                        winningProducerDTO.followingWin(),
                        filmEntity.getYear(),
                        calculateInterval(filmEntity.getYear(), winningProducerDTO.followingWin())
                );

                winningIntervalList.add(nextYearWinningProducerDTO);
                mapAllWinners.put(producer, nextYearWinningProducerDTO);
            }
        }

        return winningIntervalList;
    }

    private boolean existWinningProducer(WinningProducerDTO winningProducerDTO) {
        return winningProducerDTO != null;
    }

    private int calculateInterval(int year, int previousYear) {
        return year - previousYear;
    }

    private int getMaxInterval(List<WinningProducerDTO> winnersProducerList) {
        Comparator<WinningProducerDTO> comparing = Comparator.comparing(WinningProducerDTO::interval);
        return winnersProducerList.stream().max(comparing).get().interval();
    }

    private int getMinInterval(List<WinningProducerDTO> winnersProducerList) {
        Comparator<WinningProducerDTO> comparing = Comparator.comparing(WinningProducerDTO::interval);
        return winnersProducerList.stream().min(comparing).get().interval();
    }

}