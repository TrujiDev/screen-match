package com.trujidev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeriesData(
    @JsonAlias("Title") String title,
    @JsonAlias("totalSeasons") Integer totalSeasons,
    @JsonAlias("imdbRating") String rating,
    @JsonAlias("Genre") String genre,
    @JsonAlias("Plot") String plot,
    @JsonAlias("Poster") String poster,
    @JsonAlias("Actors") String actors
) {
  @Override
  public String toString() {
    return String.format("Series Data: {\n" +
        "  \"title\": \"%s\",\n" +
        "  \"totalSeasons\": %d,\n" +
        "  \"rating\": \"%s\",\n" +
        "  \"genre\": \"%s\",\n" +
        "  \"plot\": \"%s\",\n" +
        "  \"poster\": \"%s\",\n" +
        "  \"actors\": \"%s\"\n" +
        "}", title, totalSeasons, rating, genre, plot, poster, actors);
  }
}
