package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.entities;

import com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator.EWinner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public String getProducersInline() {
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

    public final List<String> getProducers() {

        if (!hasMoreThanProducer()) {
            return List.of(producers.trim());
        }

        if (!hasMoreThanTwoProducers()) {
            return parseTwoProducers();
        }

        return parseMoreThanTwoProducers();
    }

    public boolean hasMoreThanProducer() {
        return producers.contains("and");
    }

    private List<String> parseTwoProducers() {
        return Arrays.stream(producers.split("and")).map(String::trim).toList();
    }

    public boolean hasMoreThanTwoProducers() {
        if(!hasMoreThanProducer()){
            return false;
        }
        return producers.contains(",");
    }

    private List<String> parseMoreThanTwoProducers() {
        String[] splits = producers.split("and");
        List<String> producersList = new ArrayList<>(Arrays.stream(splits[0].split(",")).map(String::trim).toList());
        producersList.add(splits[1].trim());
        return producersList;
    }

}