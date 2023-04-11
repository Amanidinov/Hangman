package hangman;

import java.io.*;
import java.util.*;


    public class Hangman {


        public static void main (String[] args) throws FileNotFoundException {

            // Create a scanner object to read the words from the file
            Scanner fileScanner = new Scanner(new File("/Users/aitbek/Downloads/words.txt"));

            // Create a StringBuilder object to represent the hidden word
            StringBuilder hiddenWord = new StringBuilder("");

            // Create an ArrayList to store the words from the file
            List<String> wordsList = new ArrayList<>();

            // Adding words from the file to the list
            while(fileScanner.hasNext()){
                wordsList.add(fileScanner.nextLine());
            }

            // Choosing a random word
            Random random = new Random();
            String word = wordsList.get(random.nextInt(wordsList.size()));

            // Initializing a hidden word with all "_"
            hiddenWord.append("_".repeat(word.length()));

            // Starting the game
            playerGuess(hiddenWord, word);

        }

        // Method for prompting the user to guess a character and updating the hidden word
        private static void playerGuess(StringBuilder hiddenWord, String word) {
            int numTries = 0;
            Scanner characterScanner = new Scanner(System.in);
            System.out.print("length of the word:" + hiddenWord.length() + " " + hiddenWord);
            while(numTries<10 && !isGameOver(String.valueOf(hiddenWord))) {
                // Prompting user to enter a guess
                System.out.println("\nEnter a character to complete a word: ");
                char guess = Character.toLowerCase(characterScanner.nextLine().charAt(0));

                // Checking if the guess is correct
                if (word.indexOf(guess) == -1) {
                    // If the guess is incorrect, increment the number of tries and print the hangman
                    System.out.println("Missed, Try Again:)");
                    printHangman(numTries);
                    numTries++;
                }else {
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guess) {
                            // If the guess is correct, update the hidden word with the guessed character
                            // If the character has already been guessed, print a message and break out of the loop
                            if (hiddenWord.charAt(i) != guess) {
                                hiddenWord.setCharAt(i, guess);
                            } else {
                                System.out.println("You Already Found This Letter, Try Another One:) ");
                                break;
                            }
                        }
                    }
                }
                System.out.println("\n" + hiddenWord);
            }

            // Printing the result of the game
            System.out.println("Value Of Hidden Word: "+word);
            characterScanner.close();
        }


        // method to check if game is over or not
        public static boolean isGameOver(String hiddenWord){
            return !hiddenWord.contains("_");
        }


        // Method to print the Hangman based on the number of wrong guesses
        public static void printHangman(int numWrongGuesses) {
            if (numWrongGuesses>=1) {
                System.out.println(  "  -----------");
            }
            if (numWrongGuesses>=2) {
                System.out.println(  "  |         |");
            }
            if (numWrongGuesses >= 3) {
                System.out.println("  O");
            }
            if (numWrongGuesses >= 4) {
                System.out.print(" /");
            }
            if (numWrongGuesses >=5) {
                System.out.print("|");
            }
            if(numWrongGuesses>=6){
                System.out.println("\\");
            }
            if (numWrongGuesses >= 7) {
                System.out.println("  |");
            }
            if (numWrongGuesses >= 8) {
                System.out.print(" /");
            }
            if (numWrongGuesses >= 9) {
                System.out.print(" \\");
                System.out.println("\nGame Over, Hangman Is Dead!");
            }
        }
    }

