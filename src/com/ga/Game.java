package com.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
  private HumanPlayer playerOne;
  private HumanPlayer playerTwo;
  private ComputerPlayer computer;
  private Player otherPlayer;
  private Scanner scanner = new Scanner(System.in);
  private List<HandHistory> handHistory = new ArrayList<>();


  public static void main(String[] args) {
    System.out.println("Welcome to Rock, Paper, Scissors!");
    chooseGameTypeV();
  }

  public static void chooseGameTypeV() {
    System.out.println("Type 'twoplayer' for two player or 'vscomputer' to play against computer");
    Game game = new Game();
    String playerChoice = game.scanner.nextLine();

    if (playerChoice.equalsIgnoreCase("twoplayer")) {
      game.playerOne = new HumanPlayer("Player one");
      game.playerTwo = new HumanPlayer("Player two");
      game.otherPlayer = game.playerTwo;
      game.showMenu();
    } else if (playerChoice.equalsIgnoreCase("vscomputer")) {
      game.playerOne = new HumanPlayer("Player one");
      game.computer = new ComputerPlayer("computer");
      game.otherPlayer = game.computer;
      game.showMenu();
    } else {
      System.out.println("Invalid game type choice");
      chooseGameTypeV();
    }

  }

  public void showMenu() {

    String message = "MAIN MENU \n "
                    + "=====\n"
                    + "1. Type 'play' to play.\n"
                    + "2. Type 'history' to view your game history. \n"
                    + "3. Type 'quit' to stop playing.";
    System.out.println(message);
    String playerChoice = scanner.nextLine();

    if (playerChoice.equalsIgnoreCase("play")) {
      playGame();
    } else if (playerChoice.equalsIgnoreCase("history")) {
      showHistory();
    } else if (playerChoice.equalsIgnoreCase("quit")) {
      System.exit(0);
    } else {
      System.out.println("Invalid choice pick again \n");
      showMenu();
    }

  }

  public void playGame() {
    getHandInputFromPlayer(playerOne);
    getHandInputFromPlayer(otherPlayer);
    int result = playerOne.compareHandsWith(otherPlayer);
    System.out.println(result);
    playGame();
  }

  public void getHandInputFromPlayer(Player player) {
    if (player.getName().equals("computer")) {
      player.makeHand("random");
      return;
    }

    String message = player.getName() + "\n" +
        "Type 'rock', 'paper', or 'scissors' to play.\n"
            + "Type 'quit' to go back to the main menu.\n";
    System.out.println(message);

    String playerChoice = scanner.nextLine();

    if (validHandChoice(playerChoice)) {
      player.makeHand(playerChoice);
    } else if (playerChoice.equalsIgnoreCase("quit")) {
      showMenu();
    } else {
      System.out.println("Invalid selection");
      getHandInputFromPlayer(player);
    }
  }

  public boolean validHandChoice(String handType) {
    if (handType.equalsIgnoreCase("rock") ||
        handType.equalsIgnoreCase("paper") ||
        handType.equalsIgnoreCase("scissors")) {
      return true;
    } else {
      return false;
    }
  }

  public void showHistory() {
    int wins = 0;
    int losses = 0;
    int draws = 0;

    for (HandHistory handPlayed : handHistory) {
      if (handPlayed.getResult() == 0) {
        draws ++;
      } else if (handPlayed.getResult() == -1) {
        losses ++;
      } else {
        wins ++;
      }
    }
    String results = "Wins = " + wins + ". Losses = " + losses +". Draws = " + draws+ ".";
    System.out.println("=== GAME HISTORY ===\n" +results);
    for (HandHistory handPlayed : handHistory) {
      System.out.println(handPlayed.toString());
    }
    continueGame();

  }

  public void continueGame() {
    String message =
        "Type 'play' to continue playing.\n"
            + "Type 'quit' to go back to the main menu.\n";
    System.out.println(message);

    String playerChoice = scanner.nextLine();
    if (playerChoice.equalsIgnoreCase("play")) {
      playGame();
    } else if (playerChoice.equalsIgnoreCase("quit")) {
      showMenu();
    } else {
      System.out.println("Invalid choice");
      continueGame();
    }
  }
}
