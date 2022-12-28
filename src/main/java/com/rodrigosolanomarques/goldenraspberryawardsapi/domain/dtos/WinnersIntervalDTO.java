package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.dtos;

import java.util.List;

public record WinnersIntervalDTO(List<WinningProducerDTO> min, List<WinningProducerDTO> max) {
}
