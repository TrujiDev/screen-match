package com.trujidev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeData(
    @JsonAlias("Episode") Integer episode,
    @JsonAlias("Title") String title,
    @JsonAlias("Released") String released,
    @JsonAlias("imdbRating") String rating
) {
  @Override
  public String toString() {
    return "EpisodeData{" +
        "episode: " + episode +
        ", title: '" + title + '\'' +
        ", released: '" + released + '\'' +
        ", rating: '" + rating + '\'' +
        '}';
  }
}
