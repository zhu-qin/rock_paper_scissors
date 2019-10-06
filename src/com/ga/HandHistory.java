package com.ga;

public class HandHistory {
  private Hand playerOneHand;
  private Hand otherPlayerHand;
  private int result;

  public HandHistory(Hand playerOneHand, Hand otherPlayerHand, int result) {
    this.playerOneHand = playerOneHand;
    this.otherPlayerHand = otherPlayerHand;;
    this.result = result;
  }

  public int getResult() {
    return this.result;
  }

  public String toString() {
    String result;
    if (this.result == 1) {
      result = "=== Player one wins. Player one plays "
          + playerOneHand.getHandType()
          + ". Player two plays "
          + otherPlayerHand.getHandType() + ". ===";
    } else if (this.result == -1) {
      result = "=== Player two wins. Player one plays "
          + playerOneHand.getHandType()
          + ". Player two plays "
          + otherPlayerHand.getHandType() + ". ===";;
    } else {
      result = "=== Draw both players play " + playerOneHand.getHandType() + ". ===";
    }

    return result;
  }

}
