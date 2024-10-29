package com.trujidev.screenmatch.dto;

import com.trujidev.screenmatch.model.Category;

public record SeriesDto(
    Long id,
    String title,
    Integer totalSeasons,
    Double rating,
    Category genre,
    String plot,
    String poster,
    String actors
) {
}
