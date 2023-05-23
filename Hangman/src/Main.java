import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {  // main method

        System.out.println("Welcome to Hangman! Please type your answers in capital letters (i.e. A, B, F, Z) If you would like to leave at any point, please press 0.");
        Scanner keyboard = new Scanner(System.in);  // scanner function
        Scanner scan = new Scanner(new File("/Users/lleytonlam/IdeaProjects/Hangman/out/production/Hangman/HangmanWordsList.txt"));
        /*
        Finds the file
        Reads the file
         */

        List<String> words = new ArrayList<>();  // Array List

        while (scan.hasNext()) {  // while loop
            words.add(scan.nextLine());
        }

        Random rand = new Random();  // new class Random

        String word = words.get(rand.nextInt(words.size()));

        List<Character> playerGuesses = new ArrayList<>();

        int incorrect = 0;

        /*
        Welcomes the player
        Reading the Hangman word file
        Setting a while loop to choose a random word everytime the code opens.
        setting up a string to capture the user's input.
        setting up a incorrect tally to see how many wrong guesses the user has input.
         */

        while(true) {
            Hangman(incorrect);
            int guessesLeft = 6 - incorrect;


            if (incorrect >= 7) {  // when the player losses.
                System.out.println("You lost");
                System.out.println("The word was: " + word);
                break;
            }

            dashes(word, playerGuesses);
            if (!PlayerGuess(keyboard, word, playerGuesses)) {  // when user inputs incorrect guess
                if (guessesLeft > 1 || guessesLeft == 0){
                    incorrect++;
                    System.out.println("Incorrect, you have " + guessesLeft + " guesses left");
                }

                else if (guessesLeft == 1){
                    incorrect++;
                    System.out.println("Incorrect, you have " + guessesLeft + " more guess.");
                }

            }

            if(dashes(word, playerGuesses)) {  // player wins
                if (guessesLeft > 1) {
                    System.out.println("You win, the word was: " + word);
                    System.out.println("You had " + guessesLeft + " guesses left.");
                    break;
                }
                else if (guessesLeft == 1) {
                    System.out.println("You win, the word was: " + word);
                    System.out.println("You had " + guessesLeft + " guess left.");
                    break;
                }

            }

            if ((playerGuesses.get(playerGuesses.size() - 1) == '0')) {  // exit code
                System.out.println("Thanks for playing");
                System.exit(0);
            }
        }
    }

    private static boolean PlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        System.out.println("Please enter a letter");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean dashes(String word, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
    private static void Hangman(Integer incorrect) {
        System.out.println(" -------");
        if (incorrect >= 1) {
            System.out.println(" |");
        }

        if (incorrect >= 2) {
            System.out.println(" O");
        }

        if (incorrect >= 3) {
            System.out.print("\\ ");
            if (incorrect >= 3) {
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }

        if (incorrect >= 5) {
            System.out.println(" |");
        }

        if (incorrect >= 6) {
            System.out.print("/ ");
            if (incorrect >= 7) {
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }
}