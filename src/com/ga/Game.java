package com.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
  private HumanPlayer humanPlayer;
  private ComputerPlayer computer;
  private Scanner scanner = new Scanner(System.in);
  private List<HandHistory> handHistory = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println("Welcome to Rock, Paper, Scissors!");
    Game game = new Game();
    System.out.println("Enter your name");
    String playerName = game.scanner.nextLine();

    game.humanPlayer = new HumanPlayer(playerName);
    game.computer = new ComputerPlayer("computer");

    game.showMenu();
  }

  public void showMenu() {

    String message =
        "MAIN MENU \n "
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
    String message =
        "Type 'rock', 'paper', or 'scissors' to play.\n"
          + "Type 'quit' to go back to the main menu.\n";
    System.out.println(message);

    String playerChoice = scanner.nextLine();

    if (validHandChoice(playerChoice)) {
      playThisHand(playerChoice);
    } else if (playerChoice.equalsIgnoreCase("quit")) {
      showMenu();
    } else {
      System.out.println("Invalid selection");
      playGame();
    }
  }

  public void playThisHand(String playerChoice) {
    humanPlayer.makeHand(playerChoice);
    computer.makeHand("random");
    int result = humanPlayer.compareHandsWith(computer);
    HandHistory handPlayed = new HandHistory(humanPlayer.getHand(), computer.getHand(), humanPlayer.getName(), result);
    handHistory.add(handPlayed);

    System.out.println(handPlayed.toString());

    playGame();
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
    System.out.println(results);
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
