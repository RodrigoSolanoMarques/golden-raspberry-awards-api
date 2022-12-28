package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator.EWinner;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String studios;
    private String producers;
    private Integer year;
    @Enumerated(EnumType.STRING)
    private EWinner winner;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setWinner(EWinner winner) {
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStudios() {
        return studios;
    }

    public String getProducers() {
        return producers;
    }

    public Integer getYear() {
        return year;
    }

    public EWinner getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}