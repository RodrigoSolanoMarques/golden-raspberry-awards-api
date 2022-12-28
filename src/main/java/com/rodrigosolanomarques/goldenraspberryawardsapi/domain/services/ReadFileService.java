package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.FilmDTO;

import java.util.List;

public interface ReadFileService {
    List<FilmDTO> read(String path);
}