package com.ga;

public abstract class Player {
  private String name;
  private Hand hand;
  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Hand getHand() {
    return this.hand;
  }

  public void makeHand(String handType) {
    this.hand = new Hand(handType);
  }

  public int compareHandsWith(Player otherPlayer) {
    return this.hand.beatsOtherHand(otherPlayer.hand);
  }
}
