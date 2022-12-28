package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.repositories;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {

    @Query("SELECT f "
            + "FROM FilmEntity f "
            + "WHERE f.winner = 'YES' "
            + "ORDER BY f.producers, f.year ASC")
    List<FilmEntity> findAllWinningFilm();
}