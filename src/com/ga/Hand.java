package com.ga;

public class Hand {
  private String handType;

  public Hand(String handType) {
    if (handType.equalsIgnoreCase("random")) {
      this.handType = this.generateRandomHand();
    } else {
      this.handType = handType;
    }
  }

  private String generateRandomHand() {
    return "rock";
  }

  public String getHandType() {
    return handType;
  }

  public int beatsOtherHand(Hand otherHand) {
    if (this.handType.equalsIgnoreCase(otherHand.handType)) {
      return 0;
    } else if (this.handType.equals("rock") && otherHand.handType.equals("scissors") ||
              this.handType.equals("scissors") && otherHand.handType.equals("paper") ||
              this.handType.equals("paper") && otherHand.handType.equals("rock")) {
      return 1;
    } else {
      return -1;
    }
  }
}
