package com.trujidev.screenmatch.service;

import com.trujidev.screenmatch.dto.EpisodeDto;
import com.trujidev.screenmatch.dto.SeriesDto;
import com.trujidev.screenmatch.model.Category;
import com.trujidev.screenmatch.model.Series;
import com.trujidev.screenmatch.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

  @Autowired
  private SeriesRepository repository;

  public List<SeriesDto> getAllSeries() {
    return convertToSeriesDto(this.repository.findAll());
  }

  public List<SeriesDto> getTop5Series() {
    return convertToSeriesDto(this.repository.findTop5ByOrderByRatingDesc());
  }

  public List<SeriesDto> getPremieres() {
    return convertToSeriesDto(this.repository.premieres());
  }

  public List<SeriesDto> convertToSeriesDto(List<Series> series) {
    return series.stream()
        .map(s -> new SeriesDto(
            s.getId(),
            s.getTitle(),
            s.getTotalSeasons(),
            s.getRating(),
            s.getGenre(),
            s.getPlot(),
            s.getPoster(),
            s.getActors())
        )
        .collect(Collectors.toList());
  }

  public SeriesDto getSeriesById(Long id) {
    Optional<Series> series = this.repository.findById(id);
    if (series.isPresent()) {
      Series s = series.get();
      return new SeriesDto(
          s.getId(),
          s.getTitle(),
          s.getTotalSeasons(),
          s.getRating(),
          s.getGenre(),
          s.getPlot(),
          s.getPoster(),
          s.getActors()
      );
    } else {
      return null;
    }
  }

  public List<EpisodeDto> getAllSeasons(Long id) {
    Optional<Series> series = this.repository.findById(id);
    if (series.isPresent()) {
      Series s = series.get();
      return s.getEpisodes().stream()
          .map(e -> new EpisodeDto(e.getSeason(), e.getTitle(), e.getEpisode()))
          .collect(Collectors.toList());
    }
    return null;
  }

  public List<SeriesDto> getSeriesByCategory(String category) {
    Category c = Category.fromString(category);
    return convertToSeriesDto(this.repository.findByGenre(c));
  }

  public List<EpisodeDto> getTopEpisodes(Long id) {
    var series = repository.findById(id).get();
    return this.repository.topEpisodesPerSeries(series)
        .stream()
        .map(e -> new EpisodeDto(e.getSeason(),e.getTitle(),
            e.getEpisode()))
        .collect(Collectors.toList());
  }

  public List<EpisodeDto> getSeasonByNumber(Long id, Long seasonNumber) {
    return repository.getSeasonByNumber(id, seasonNumber).stream()
        .map(e -> new EpisodeDto(e.getSeason(),e.getTitle(), e.getEpisode()))
        .collect(Collectors.toList());
  }

}
