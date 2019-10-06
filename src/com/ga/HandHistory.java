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
}
