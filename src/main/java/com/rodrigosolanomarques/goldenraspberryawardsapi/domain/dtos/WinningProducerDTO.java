package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos;

public record WinningProducerDTO(String producer, int previousWin, int followingWin, int interval) {
}
