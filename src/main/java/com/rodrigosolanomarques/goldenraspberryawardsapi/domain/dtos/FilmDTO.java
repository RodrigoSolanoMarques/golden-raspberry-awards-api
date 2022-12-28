package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator.EWinner;

public record FilmDTO(int year, String title, String studios, String producers, EWinner winner) {
}