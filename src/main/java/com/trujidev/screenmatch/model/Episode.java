package com.trujidev.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodes")
public class Episode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Integer season;
  private String title;
  private Integer episode;
  private Double rating;
  private LocalDate released;
  @ManyToOne
  private Series series;

  public Episode() {}

  public Episode(Integer season, EpisodeData episodeData) {
    this.season = season;
    this.title = episodeData.title();
    this.episode = episodeData.episode();
    try {
      this.rating = Double.valueOf(episodeData.rating());
    } catch (NumberFormatException e) {
      this.rating = 0.0;
    }
    try {
      this.released = LocalDate.parse(episodeData.released());
    } catch (DateTimeParseException e) {
      this.released = null;
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Integer getSeason() {
    return season;
  }

  public void setSeason(Integer season) {
    this.season = season;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getEpisode() {
    return episode;
  }

  public void setEpisode(Integer episode) {
    this.episode = episode;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public LocalDate getReleased() {
    return released;
  }

  public void setReleased(LocalDate released) {
    this.released = released;
  }

  public Series getSeries() {
    return series;
  }

  public void setSeries(Series series) {
    this.series = series;
  }

  @Override
  public String toString() {
    return "Episode{" +
        "season: " + season +
        ", title: '" + title + '\'' +
        ", episode: " + episode +
        ", rating: " + rating +
        ", released: " + released +
        '}';
  }
}
