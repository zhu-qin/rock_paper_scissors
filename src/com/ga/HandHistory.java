package com.ga;

public class HandHistory {
  private Hand playerHand;
  private Hand computerHand;
  private String playerName;
  private int result;

  public HandHistory(Hand playerHand, Hand computerHand, String playerName, int result) {
    this.playerHand = playerHand;
    this.computerHand = computerHand;
    this.playerName = playerName;
    this.result = result;
  }

  public int getResult() {
    return this.result;
  }

  public String toString() {
    String result;
    if (this.result == 1) {
      result = "win";
    } else if (this.result == -1) {
      result = "lose";
    } else {
      result = "draw";
    }

    return result + " : " + playerName + " plays " + playerHand.getHandType() + ". Computer plays " + computerHand.getHandType() +".";
  }

}
