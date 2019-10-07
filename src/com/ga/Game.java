package com.ga;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    chooseGameType();
  }

  public static void chooseGameType() {
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
      game.computer = new ComputerPlayer("Computer");
      game.otherPlayer = game.computer;
      game.showMenu();
    } else {
      System.out.println("Invalid game type choice");
      chooseGameType();
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
    HandHistory handPlayed = new HandHistory(playerOne.getHand(), otherPlayer.getHand(), result);
    try {
      writeHistoryToFile("history.txt", playerOne, playerOne.getHand(), otherPlayer, otherPlayer.getHand(), result);
    } catch (IOException  e) {
      System.out.println("Failed to write to file");
    }
    handHistory.add(handPlayed);
    System.out.println(handPlayed.toString());
    playGame();
  }

  public void getHandInputFromPlayer(Player player) {
    if (player.getName().equalsIgnoreCase("Computer")) {
      player.makeHand("random");
      return;
    }

    String message = player.getName() + " : \n" +
        "Type 'rock', 'paper', 'scissors', or 'random' to play.\n"
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
        handType.equalsIgnoreCase("scissors") ||
        handType.equalsIgnoreCase("random")) {
      return true;
    } else {
      return false;
    }
  }

  public void showHistory() {
    int playerOnePoints = 0;
    int otherPlayerPoints = 0;
    int draws = 0;

    for (HandHistory handPlayed : handHistory) {
      if (handPlayed.getResult() == 0) {
        draws ++;
      } else if (handPlayed.getResult() == -1) {
        otherPlayerPoints ++;
      } else {
        playerOnePoints ++;
      }
    }
    String results = "Player one points = " + playerOnePoints +
                    ". Player two points = " + otherPlayerPoints +
                    ". Draws = " + draws+ ".";
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

  public void writeHistoryToFile(
      String fileName,
      Player playerOne,
      Hand playerOneHand,
      Player playerTwo,
      Hand playerTwoHand,
      int result) throws IOException {
    File file = new File(fileName);
    BufferedWriter writer = null;

    try {
      writer = new BufferedWriter(new FileWriter(file, true));
      String stringResults = playerOne.getName()
          +  ","
          +  playerOneHand.getHandType()
          + ","
          + playerTwo.getName()
          +  ","
          +  playerTwoHand.getHandType()
          +  ","
          + Integer.toString(result)
          + "\n";

      writer.write(stringResults);
    } catch (IOException e) {
      e.getStackTrace();
    } finally {
      writer.close();
    }
  }

  public void readFile(String fileName) throws IOException {
    File file = new File(fileName);
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));

      String currentLine = reader.readLine();

      while(currentLine != null) {
        String[] data = currentLine.split(",");

        currentLine = reader.readLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } finally {
      reader.close();
    }
  }

}
