import java.util.*;
import java.io.*;

public class Hangman {
   public static void main(String[] args) throws FileNotFoundException {
      String word;
      int score = 0;
      for (int i = 0; i < 10; i++) {
         System.out.println("Round " + (i + 1) + " of 10");
         word = "";
         while (word.length() < 4) {
            word = selectWord();
         }
         score += createGame(word);
      }
      System.out.println("Total score: " + score);
   }
   
   public static String selectWord() throws FileNotFoundException {
      Scanner fileLineCount = new Scanner(new File("wordbank.txt"));
      int lines = 0;
      while (fileLineCount.hasNextLine()) {
         fileLineCount.nextLine();
         lines++;
      }
      Scanner s = new Scanner(new File("wordbank.txt"));
      int wordIndex = (int) (Math.random() * lines) + 1;
      for (int i = 1; i < wordIndex; i++) {
         s.nextLine();
      }
      return s.nextLine();
   }
   
   public static int createGame(String word) {
      System.out.println("I'm thinking of a " + word.length() + "-letter word...");
      WordGuess wg = new WordGuess(word);
      Scanner console = new Scanner(System.in);
      while (wg.getChances() > 0 && !wg.userWin()) {
         System.out.print("Guess letter: ");
         char c = console.next().charAt(0);
         wg.guess(c);
         wg.printState();
         System.out.println("Guesses remaining: " + wg.getChances());
         System.out.println();
      }
      if (wg.getChances() == 0) {
         System.out.println("You lose!");
         System.out.println("The word was: " + word);
      } else {
         System.out.println("You win!");
         if (wg.getChances() == 10) {
            System.out.println("Perfect game!");
         }
      }
      System.out.println("Score for this round: " + wg.getChances());
      System.out.println();
      return wg.getChances();
   }
}