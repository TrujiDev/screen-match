package com.trujidev.screenmatch.model;

public enum Category {
  DRAMA("Drama", "Drama"),
  ACTION("Action", "Acción"),
  CRIME("Crime", "Crimen"),
  FANTASY("Fantasy", "Fantasía"),
  COMEDY("Comedy", "Comedia"),
  ANIMATION("Animation", "Animación"),
  ADVENTURE("Adventure", "Aventura"),
  ROMANCE("Romance", "Romance");

  private final String OMDBCATEGORY;
  private final String INPUTCATEGORY;

  Category(String OMDBCATEGORY, String INPUTCATEGORY) {
    this.OMDBCATEGORY = OMDBCATEGORY;
    this.INPUTCATEGORY = INPUTCATEGORY;
  }

  public static Category fromString(String text) {
    for (Category category : Category.values()) {
      if (category.OMDBCATEGORY.equalsIgnoreCase(text)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Category not found: " + text);
  }

  public static Category fromInput(String text) {
    for (Category category : Category.values()) {
      if (category.INPUTCATEGORY.equalsIgnoreCase(text)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Category not found: " + text);
  }
}
