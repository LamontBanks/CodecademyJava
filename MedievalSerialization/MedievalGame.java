import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class MedievalGame {

    /* Instance Variables */
    private Player player;

    /* Main Method */
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();

        game.player = game.start(console);

        game.addShortDelay();
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);

        game.addShortDelay();
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();

        game.addLongDelay();
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");

        game.addShortDelay();
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;

        game.addMediumDelay();
        game.player = game.load(charName, console);
        System.out.println("Loading character...");

        game.addMediumDelay();
        System.out.println("Now let's print out your character again to make sure everything loaded:");

        game.addShortDelay();
        System.out.println(game.player);
    } // End of main

    /* Instance Methods */
    private Player start(Scanner console) {
        Art.homeScreen();
        addShortDelay();

        System.out.println("Welcome!");
        addMediumDelay();

        // Continue or start new game
        System.out.println("Enter 'y' to load a game, 'n' to start a new game: ");
        System.out.print("Press CTRL+C to quit: ");
        String answer = console.next().toLowerCase();

        while (true) {

            if (answer.equals("y")) {
                System.out.println("You entered: " + answer);
                break;
            }

            else if (answer.equals("n")) {
                System.out.println("You entered: " + answer);
                break;
            }

            else {
                System.out.println("Unrecognized option: " + answer);
                addMediumDelay();
    
                System.out.println("Enter 'y' to load a game, 'n' to start a new game: ");
                System.out.print("Press CTRL+C to quit: ");

                answer = "";
                answer = console.next().toLowerCase();
            }
        }

        Player player = new Player("Test");

        return player;
    } // End of start

    private void save() {
        // Add save functionality here
        String filename = player.getName() + ".svr";

        try {
            FileOutputStream userSaveFile = new FileOutputStream(filename);
            ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
            playerSaver.writeObject(player);
            playerSaver.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // End of save

    private Player load(String playerName, Scanner console) {
        // Add load functionality here
        Player loadedPlayer = null;

        String filename = player + ".svr";
        try {
            FileInputStream userSaveFile = new FileInputStream(filename);
            ObjectInputStream playerLoader = new ObjectInputStream(userSaveFile);
            playerLoader.close();

            loadedPlayer = (Player) playerLoader.readObject();
        } catch (FileNotFoundException e) {
            addDelay(1500);
            System.out.println("Error: Could not find save file for player [" + playerName + "].");
            addLongDelay();
            System.out.println("Creating new player: " + playerName);
            addMediumDelay();
            loadedPlayer = new Player(playerName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (Player) loadedPlayer;

    } // End of load

    // Adds a delay to the console so it seems like the computer is "thinking"
    // or "responding" like a human, not instantly like a computer.
    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addShortDelay() {
        this.addDelay(1000);
    }

    private void addMediumDelay() {
        this.addDelay(2000);
    }

    private void addLongDelay() {
        this.addDelay(3000);
    }
}