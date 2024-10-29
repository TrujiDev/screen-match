package com.trujidev.screenmatch.presentation;

import com.trujidev.screenmatch.model.*;
import com.trujidev.screenmatch.repository.SeriesRepository;
import com.trujidev.screenmatch.service.*;

import java.util.*;
import java.util.stream.Collectors;

public class Menu {
  private final DataConverter DATACONVERTER = new DataConverter();
  private final Scanner SC = new Scanner(System.in);
  private final SeriesRepository SERIESREPOSITORY;

  private final String URL = "http://www.omdbapi.com/?t=";
  private final String API_KEY = "&apikey=f75dc7e7";
  private List<Series> series;
  private Optional<Series> foundSeries;

  public Menu(SeriesRepository SERIESREPOSITORY) {
    this.SERIESREPOSITORY = SERIESREPOSITORY;
  }

  public void showMenu() {
    var option = -1;

    while (option != 0) {
      var menu = """
          1 - Search series
          2 - Search episodes
          3 - Searched series history
          4 - Searched series by name
          5 - Searched top 5 series
          6 - Search by category
          7 - Search by total seasons and rating
          8 - Search episode by name
          9 - Top 5 episodes per series
          
          0 - Exit
          Choose an option:\s""";
      System.out.print(menu);
      option = SC.nextInt();
      SC.nextLine();

      switch (option) {
        case 1:
          searchWebSeries();
          break;
        case 2:
          searchEpisodeBySeries();
          break;
        case 3:
          searchedSeriesHistory();
          break;
        case 4:
          searchedSeriesByTitle();
          break;
        case 5:
          getTop5Series();
          break;
        case 6:
          searchByCategory();
          break;
        case 7:
          searchByTotalSeasonsAndRating();
          break;
        case 8:
          searchEpisodeByName();
          break;
        case 9:
          top5Episodes();
          break;
        case 0:
          System.out.println("Closing the application...");
          break;
        default:
          System.out.println("Invalid option.");
      }
    }
  }

  private SeriesData getSeriesData() {
    System.out.print("Type the name of the series: ");
    String seriesName = SC.nextLine().replace(" ", "+").toLowerCase();
    String json = ConsumeApi.getData(URL + seriesName + API_KEY);
    return DATACONVERTER.jsonToObject(json, SeriesData.class);
  }

  private void searchWebSeries() {
    SeriesData seriesData = getSeriesData();
    Series series = new Series(seriesData);
    SERIESREPOSITORY.save(series);
    System.out.println(seriesData);
  }

  private void searchEpisodeBySeries() {
    searchedSeriesHistory();

    System.out.print("Type the name of the series to view its episodes: ");
    var seriesName = SC.nextLine();

    Optional<Series> series = this.series.stream()
        .filter(s -> s.getTitle().toLowerCase().contains(seriesName.toLowerCase()))
        .findFirst();

    if (series.isPresent()) {
      var foundSeries = series.get();

      List<SeasonData> seasons = new ArrayList<>();

      for (int i = 1; i <= foundSeries.getTotalSeasons(); i++) {
        var json = ConsumeApi.getData(URL + foundSeries.getTitle().toLowerCase().replace(" ", "+") + "&season=" + i + API_KEY);
        SeasonData seasonData = DATACONVERTER.jsonToObject(json, SeasonData.class);
        seasons.add(seasonData);
      }
      seasons.forEach(System.out::println);

      List<Episode> episodes = seasons.stream()
          .flatMap(s -> s.episodes().stream()
              .map(e -> new Episode(s.season(), e)))
          .collect(Collectors.toList());

      foundSeries.setEpisodes(episodes);
      SERIESREPOSITORY.save(foundSeries);
    }
  }

  private void searchedSeriesHistory() {
    series = SERIESREPOSITORY.findAll();

    series.stream()
        .sorted(Comparator.comparing(Series::getGenre))
        .forEach(System.out::println);
  }

  private void searchedSeriesByTitle() {
    System.out.print("Type the name of the series: ");
    String title = SC.nextLine();

    foundSeries = SERIESREPOSITORY.findByTitleContainsIgnoreCase(title);

    if (foundSeries.isPresent()) {
      System.out.println("The series info is: " + foundSeries.get());
    } else {
      System.out.println("The series has not found");
    }
  }

  private void getTop5Series() {
    List<Series> top5 = SERIESREPOSITORY.findTop5ByOrderByRatingDesc();
    top5.forEach(s -> System.out.println("Title: " + s.getTitle() + " Rating: " + s.getRating()));
  }

  private void searchByCategory() {
    System.out.print("Type the genre of the series: ");
    var inputGenre = SC.nextLine();
    var genre = Category.fromInput(inputGenre);
    List<Series> seriesByGenre = SERIESREPOSITORY.findByGenre(genre);
    System.out.println("These are the genre series " + inputGenre + ":");
    seriesByGenre.forEach(System.out::println);
  }

  private void searchByTotalSeasonsAndRating() {
    System.out.print("How many seasons is the series you are looking for?: ");
    var totalSeasons = SC.nextInt();
    System.out.print("What is the series evaluation you are looking for?: ");
    var rating = SC.nextDouble();

    List<Series> searchResult = SERIESREPOSITORY.findByTotalSeasonsAndRating(totalSeasons, rating);
    System.out.println("These are the series that meet their criteria: ");
    System.out.println(searchResult);
  }

  private void searchEpisodeByName() {
    System.out.println("What is the name of the episode you are looking for?: ");
    var episodeName = SC.nextLine();

    List<Episode> searchResult = SERIESREPOSITORY.findEpisodeByName(episodeName);
    searchResult.forEach(e -> System.out.printf("""
        {
        \tSeries: %s
        \tSeason: %s
        \tEpisode: %s
        \tRating: %s
        }""", e.getSeries().getTitle(), e.getSeason(), e.getEpisode(), e.getRating()
    ));
  }

  private void top5Episodes() {
    searchedSeriesByTitle();

    if (foundSeries.isPresent()) {
      Series seriesName = foundSeries.get();
      List<Episode> topEpisodes = this.SERIESREPOSITORY.top5Episodes(seriesName);
      topEpisodes.forEach(e -> System.out.printf("""
          {
          \tSeries: %s
          \tSeason: %s
          \tEpisode: %s
          \tTitle: %s
          \tRating: %s
          }
          """, e.getSeries().getTitle(), e.getSeason(), e.getEpisode(), e.getTitle(), e.getRating()
      ));
    } else {
      System.out.println("Series not found.");
    }
  }
}
