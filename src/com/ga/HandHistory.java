package com.ga;

public class HandHistory {


  private String playerOneName;
  private String playerTwoName;
  private String playerOneHand;
  private String playerTwoHand;
  private int result;

  public HandHistory(String playerOneName, String playerOneHand, String playerTwoName,String playerTwoHand, int result) {
    this.playerOneName = playerOneName;
    this.playerOneHand = playerOneHand;
    this.playerTwoName = playerTwoName;
    this.playerTwoHand = playerTwoHand;
    this.result = result;
  }

  public int getResult() {
    return this.result;
  }

  public String toString() {
    String result;
    if (this.result == 1) {
      result = "=== Player one wins. Player one plays "
          + playerOneHand
          + ". " + playerTwoName + " plays "
          + playerTwoHand + ". ===";
    } else if (this.result == -1) {
      result = "=== " + playerTwoName + " wins. Player one plays "
          + playerOneHand
          + ". " + playerTwoName +" plays "
          + playerTwoHand + ". ===";;
    } else {
      result = "=== Draw both players play " + playerOneHand + ". ===";
    }

    return result;
  }
}
