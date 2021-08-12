package com.senlaCourses.adPlacementSystem;

import com.senlaCourses.adPlacementSystem.domain.model.Profile;

public class Main {

  public static void main(String[] args) {
    Profile profile = new Profile();
    profile.setRating(profile.getRating() + 1);
    System.out.println(profile.getRating());
  }
}
