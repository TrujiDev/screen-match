package com.trujidev.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SeasonData(
    @JsonAlias("Season") Integer season,
    @JsonAlias("Episodes") List<EpisodeData> episodes
) {
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Season Data: {\n");
    sb.append("  \"season\": ").append(season).append(",\n");
    sb.append("  \"episodes\": [");

    for (int i = 0; i < episodes.size(); i++) {
      sb.append(episodes.get(i).toString());
      if (i < episodes.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    sb.append("\n}");
    return sb.toString();
  }
}
