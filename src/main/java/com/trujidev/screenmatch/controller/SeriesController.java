package com.trujidev.screenmatch.controller;

import com.trujidev.screenmatch.dto.EpisodeDto;
import com.trujidev.screenmatch.dto.SeriesDto;
import com.trujidev.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

  @Autowired
  private SeriesService seriesService;

  @GetMapping()
  public List<SeriesDto> getAllSeries() {
    return this.seriesService.getAllSeries();
  }

  @GetMapping("/top5")
  public List<SeriesDto> getTop5Series() {
    return this.seriesService.getTop5Series();
  }

  @GetMapping("/premieres")
  public List<SeriesDto> getPremieres() {
    return this.seriesService.getPremieres();
  }

  @GetMapping("/{id}")
  public SeriesDto getSeriesById(@PathVariable Long id) {
    return this.seriesService.getSeriesById(id);
  }

  @GetMapping("/{id}/seasons/all")
  public List<EpisodeDto> getSeasons(@PathVariable Long id) {
    return this.seriesService.getAllSeasons(id);
  }

  @GetMapping("/{id}/seasons/{seasonNumber}")
  public List<EpisodeDto> getSeasonsByNumber(@PathVariable Long id, @PathVariable Long seasonNumber){
    return this.seriesService.getSeasonByNumber(id, seasonNumber);
  }

  @GetMapping("/category/{category}")
  public List<SeriesDto> getSeriesByCategory(@PathVariable String category) {
    return this.seriesService.getSeriesByCategory(category);
  }

  @GetMapping("/{id}/seasons/top")
  public List<EpisodeDto> getTopEpisodes(@PathVariable Long id){
    return this.seriesService.getTopEpisodes(id);
  }

}