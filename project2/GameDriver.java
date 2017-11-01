
import java.util.Scanner;

public class GameDriver {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (true){
            Game game = new Game(5);
            game.playAGame();
            System.out.println("Play another game (y/n)?");
            String input = scanner.next();
            while (!input.equalsIgnoreCase("y")
                    && !input.equalsIgnoreCase("n")){
                System.out.println("Please enter 'y' or 'n'!\nPlay another game (y/n)?");
                input = scanner.next();
            }
            if(input.equalsIgnoreCase("n")){
                break;
            }
        }
        scanner.close();
    }
}
