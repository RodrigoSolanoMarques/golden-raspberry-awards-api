package com.rodrigosolanomarques.goldenraspberryawardsapi.external.api;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.services.FilmService;
import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos.WinnersIntervalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @GetMapping("/min-max-winners-interval")
    @ResponseStatus(HttpStatus.OK)
    public WinnersIntervalDTO minMaxWinnersIntervalList() {
        return filmService.minMaxWinnersIntervalList();
    }
}