package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinnersIntervalDTO;

import java.util.List;

public interface FilmService {
    List<FilmDTO> read(String path);

    WinnersIntervalDTO minMaxWinnersIntervalList();
}
