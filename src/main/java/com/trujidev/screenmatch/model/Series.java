package com.trujidev.screenmatch.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
public class Series {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String title;
  private Integer totalSeasons;
  private Double rating;
  @Enumerated(EnumType.STRING)
  private Category genre;
  private String plot;
  private String poster;
  private String actors;
  @OneToMany(mappedBy = "series", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Episode> episodes;

  public Series() {}

  public Series(SeriesData seriesData) {
    this.title = seriesData.title();
    this.totalSeasons = seriesData.totalSeasons();
    this.rating = OptionalDouble.of(Double.parseDouble(seriesData.rating())).orElse(0.0);
    this.genre = Category.fromString(seriesData.genre().split(",")[0].trim());
    this.plot = seriesData.plot();
    this.poster = seriesData.poster();
    this.actors = seriesData.actors();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getTotalSeasons() {
    return totalSeasons;
  }

  public void setTotalSeasons(Integer totalSeasons) {
    this.totalSeasons = totalSeasons;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Category getGenre() {
    return genre;
  }

  public void setGenre(Category genre) {
    this.genre = genre;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(String poster) {
    this.poster = poster;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(String actors) {
    this.actors = actors;
  }

  public List<Episode> getEpisodes() {
    return episodes;
  }

  public void setEpisodes(List<Episode> episodes) {
    episodes.forEach(e -> e.setSeries(this));
    this.episodes = episodes;
  }

  @Override
  public String toString() {
    return String.format("{\n" +
        "  \"title\": \"%s\",\n" +
        "  \"totalSeasons\": %d,\n" +
        "  \"rating\": \"%s\",\n" +
        "  \"genre\": \"%s\",\n" +
        "  \"plot\": \"%s\",\n" +
        "  \"poster\": \"%s\",\n" +
        "  \"actors\": \"%s\"\n" +
        "  \"episodes\": \"%s\"\n" +
        "}", title, totalSeasons, rating, genre, plot, poster, actors, episodes);
  }
}
