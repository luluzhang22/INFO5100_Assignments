
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");
        words.add("Human");
        words.add("Student");
        words.add("Northeastern");
        words.add("University");
        Hangman hangman = new Hangman(words);
        hangman.playGame();
    }

    private final String[] scaffold = {"------------",
                                        "|           |",
                                        "|",
                                        "|",
                                        "|",
                                        "|",
                                        "|",
                                        "------------------"};
    private final String[] hang = {"|           O",
                                    "|           |",
                                    "|       ---",
                                    "|       ---   ---",
                                    "|         /",
                                    "|         /   \\",
                                    "|      --",
                                    "|      --       --"};

    private final int MAX_WRONG_TIME = 8;

    private ArrayList<String> words;

    private HashSet<Character> correctList = new HashSet<>();

    private ArrayList<Character> wrongList = new ArrayList<>();

    private String selected;

    private int correctNum;


    Hangman(ArrayList<String> words){
        this.words = words;
    }

    public void chooseWord(){
        selected = words.get(new Random().nextInt(words.size()));
    }

    public void handleGuess(char c){
        c = Character.toLowerCase(c);
        if (selected.toLowerCase().contains(c+"")&&!correctList.contains(c)){
            correctList.add(c);
        }else
            wrongList.add(c);
    }

    public boolean gameWon(){
        if (correctNum == selected.length()){
            System.out.println("You win!");
            return true;
        }else
            return false;
    }

    public boolean gameOver(){
        if(wrongList.size() == MAX_WRONG_TIME) {
            printHangman();
            displayWord();
            System.out.println("Game Over!");
            return true;
        }else
            return false;
    }

    public void printHangman(){
        System.out.println();
        System.out.println("******************");
        System.out.println("Scaffold:");

        for(int i = 0,j=-2; i < scaffold.length; i++,j++){
            if(j>=0&&j<wrongList.size()){
                if((j==2 || j == 4 || j == 6) && j < wrongList.size() - 1)
                    j++;
                System.out.println(hang[j]);
            }else
                System.out.println(scaffold[i]);

        }
    }

    public void playGame(){
        cleanScreen();
        System.out.println("Welcome to play Hangman! Press \"s\" to start game, press \"q\" to exit!");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if(input.toLowerCase().equals("s")){
            chooseWord();
            while(!gameOver()){
                cleanScreen();
                printHangman();
                displayWord();
                if(gameWon()){
                    scanner.close();
                    return;
                }
                System.out.println("Please guess a letter:");
                char letter = scanner.next().charAt(0);
                handleGuess(letter);
            }
            scanner.close();
        }else if(input.toLowerCase().equals("q")){
            scanner.close();
            System.out.println("Exit!");
        }else{
            playGame();
        }
    }

    /*display the correctly guessed letters and hide the remaining with dashes.*/
    public void displayWord(){
        System.out.println();
        System.out.println("Selected Word:");
        correctNum = 0;
        for(int i = 0; i < selected.length(); i++){
            char c = selected.charAt(i);
            if(correctList.contains(Character.toLowerCase(c))) {
                correctNum ++;
                System.out.print(c+"  ");
            }else
                System.out.print("_  ");
        }
        System.out.println();
        System.out.println();
    }

    private void cleanScreen(){
        System.out.print("\033[H\033[2J");

        //        Runtime.getRuntime().exec("cls");
//        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//                System.console().flush();

    }
}
