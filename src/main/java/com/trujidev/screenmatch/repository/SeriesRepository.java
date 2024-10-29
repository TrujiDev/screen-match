package com.trujidev.screenmatch.repository;

import com.trujidev.screenmatch.model.Category;
import com.trujidev.screenmatch.model.Episode;
import com.trujidev.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {

  Optional<Series> findByTitleContainsIgnoreCase(String title);

  List<Series> findTop5ByOrderByRatingDesc();

  List<Series> findByGenre(Category genre);

  //  List<Series> findByTotalSeasonsLessThanEqualAndRatingGreaterThanEqual(int totalSeasons, double rating);

  @Query("SELECT s FROM Series s WHERE s.totalSeasons <= :totalSeasons AND s.rating >= :rating")
  List<Series> findByTotalSeasonsAndRating(int totalSeasons, double rating);

  @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:episodeName%")
  List<Episode> findEpisodeByName(String episodeName);

  @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.rating DESC LIMIT 5")
  List<Episode> top5Episodes(Series series);

  @Query("SELECT s FROM Series s JOIN s.episodes e GROUP BY s ORDER BY MAX(e.released) DESC LIMIT 5")
  List<Series> premieres();

  @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.rating DESC LIMIT 5")
  List<Episode> topEpisodesPerSeries(Series series);

  @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s.id = :id AND e.season = :seasonNumber")
  List<Episode> getSeasonByNumber(Long id, Long seasonNumber);

}