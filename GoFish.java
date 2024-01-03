import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GoFish {

  public ArrayList<Card> pond;
  public String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K",};
  public String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
  public boolean yourTurn = false;
  public boolean gameRun = true;
  public Scanner scnr = new Scanner(System.in);

  public void newGame(Player player, Computer computer) {
    this.pond = new ArrayList<Card>();

    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < values.length; j++) {
        this.pond.add(new Card(values[j], suits[i]));
      }
    }

    Collections.shuffle(this.pond);

    player.hand = new ArrayList<Card>();
    computer.hand = new ArrayList<Card>();
    player.pairs = new ArrayList<Pair>();
    computer.pairs = new ArrayList<Pair>();
    player.points = 0;
    player.name = "Player";
    computer.name = "Computer";
    computer.points = 0;
    computer.memory = new ArrayList<String>();
    computer.badMemory = new ArrayList<String>();

    Random random = new Random();
    yourTurn = random.nextBoolean();

    for (int k = 0; k < 14; k++) {
      if (yourTurn) {
        player.hand.add(this.pond.get(0));
        this.pond.remove(0);
        yourTurn = false;
      } else {
        computer.hand.add(this.pond.get(0));
        this.pond.remove(0);
        yourTurn = true;
      }
    }
    player.assessPairs();
    player.assessPairs();
    player.assessPairs();
    computer.assessPairs();
    computer.assessPairs();
    computer.assessPairs();

    System.out.println("Your starting hand: " + player.handToString());
    // System.out.println("Computer hand: " + computer.handToString());

  }

  public void run(Player player, Computer computer) {
    String request = "L";
    boolean validInput = false;
    if (yourTurn) {
      System.out.println("It's your turn. This is your hand: " + player.handToString());
      System.out.println("You have " + player.points + " points.");
      System.out.println("What would you like to ask for?");
      while (!validInput) {
        request = scnr.nextLine();
        for (int i = 0; i < player.hand.size(); i++) {
          if (player.hand.get(i).value.equals(request)) {
            validInput = true;
            break;
          }
        }
        if (validInput)
          break;
        System.out.println("Invalid input. What would you like to ask for?");
      }
      if (!computer.memory.contains(request))
        computer.memory.add(request);
      if (computer.badMemory.contains(request)) {
        computer.badMemory.remove(request);
      }
    } else {
      request = computer.strategize();
      System.out.println("Computer asked for a " + request);
    }

    boolean foundCard = false;

    if (yourTurn) {
      for (int i = 0; i < computer.hand.size(); i++) {
        if (request.equals(computer.hand.get(i).value)) {
          System.out.println("Computer had a " + request + "! You get to go again!");
          player.hand.add(computer.hand.get(i));
          computer.hand.remove(i);
          if (computer.memory.contains(request))
            computer.memory.remove(request);
          if (computer.badMemory.contains(request))
            computer.badMemory.remove(request);
          foundCard = true;
          player.assessPairs();
        }
      }
    } else {
      for (int i = 0; i < player.hand.size(); i++) {
        if (request.equals(player.hand.get(i).value)) {
          System.out.println("Computer gets to move again");
          computer.hand.add(player.hand.get(i));
          player.hand.remove(i);
          if (computer.memory.contains(request)) {
            computer.memory.remove(request);
          }
          if (computer.badMemory.contains(request)) {
            computer.badMemory.remove(request);
          }
          foundCard = true;
          computer.assessPairs();
        }
      }
    }


    if (foundCard == false) {
      System.out.println("Go fish!");
      if (yourTurn) {
        if (this.pond.size() != 0) {
          player.hand.add(pond.get(0));
          System.out.println("You drew a " + pond.get(0).value + "!");
        }
      } else {
        if (this.pond.size() != 0) {
          computer.hand.add(pond.get(0));
          computer.badMemory.add(request);
        }
      }

      if (pond.size() != 0 && pond.get(0).value.equals(request)) {
        if (!yourTurn) {
          System.out.println("Computer drew a " + pond.get(0).value + "! They go again!");
        } else {
          System.out.println("You get to go again!");
          computer.memory.remove(request);
        }
      } else {
        yourTurn = !yourTurn;
      }
      if (pond.size() != 0)
        pond.remove(0);
      player.assessPairs();
      computer.assessPairs();
    }

    // + "\nComputer hand: "+ computer.handToString()
    System.out.println("\nYour hand now: " + player.handToString() + "\nScore (you - computer): "
        + player.points + " - " + computer.points + "\nCards left in pond: " + pond.size()
        + "\nPress enter to continue.");
    scnr.nextLine();

    if (player.hand.size() == 0 || computer.hand.size() == 0) {
      if (pond.size() == 0) {
        player.assessPairs();
        player.assessPairs();
        computer.assessPairs();
        computer.assessPairs();
        gameRun = false;
      } else {
        if (player.hand.size() == 0) {
          player.hand.add(pond.get(0));
          pond.remove(0);
        }
        if (computer.hand.size() == 0) {
          computer.hand.add(pond.get(0));
          pond.remove(0);
        }
      }
    }

  }


  public static void main(String[] args) {
    Player player = new Player();
    Computer computer = new Computer();
    GoFish game = new GoFish();
    game.newGame(player, computer);
    System.out.println();
    while (game.gameRun) {
      game.run(player, computer);
    }
    game.scnr.close();

  }

}
