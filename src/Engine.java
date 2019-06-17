import Game.Computer;
import Game.Player;

public class Engine {
    public Engine() {
    }

    public void Run() {
        Player player = new Player();
        Computer computer = new Computer();

        String nextToPlay = "player";
        Boolean winner = false;

        while (!winner) {
            switch (nextToPlay) {
                case "player":
                    System.out.println("Player's turn (try to guess the number of the computer)");
                    winner = player.Play();
                    nextToPlay = "computer";
                    break;
                case "computer":
                    try {
                        winner = computer.Play();
                        nextToPlay = "player";
                    } catch (IllegalArgumentException ex) {
                        System.out.println("You have lied!");
                        winner = true;
                    }
                    break;
            }
        }
    }
}
