package com.ga;

import java.util.Random;

public class Hand {
  private String handType;
  public static String[] handChoices = {"rock", "paper", "scissors"};

  public Hand(String handType) {
    if (handType.equalsIgnoreCase("random")) {
      this.handType = this.generateRandomHand();
    } else {
      this.handType = handType;
    }
  }

  private String generateRandomHand() {
    Random rand = new Random();
    int n = rand.nextInt(3);
    return Hand.handChoices[n];
  }

  public String getHandType() {
    return handType;
  }

  public int beatsOtherHand(Hand otherHand) {
    String convertedHand = this.handType.toLowerCase();
    String convertedOtherHand = otherHand.handType.toLowerCase();

    if (convertedHand.equals(convertedOtherHand)) {
      return 0;
    } else if (convertedHand.equals("rock") && convertedOtherHand.equals("scissors") ||
              convertedHand.equals("scissors") && convertedOtherHand.equals("paper") ||
              convertedHand.equals("paper") && convertedOtherHand.equals("rock")) {
      return 1;
    } else {
      return -1;
    }
  }
}
