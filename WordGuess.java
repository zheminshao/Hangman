import java.util.*;

public class WordGuess {
   private String word;
   private ArrayList<Character> chars;
   private ArrayList<Character> state;
   private ArrayList<Character> guesses;
   private int length;
   private int chances;
   private boolean userWin;
   
   public WordGuess(String word) {
      this.word = word;
      this.chars = new ArrayList<Character>();
      for (int i = 0; i < word.length(); i++) {
         chars.add(word.charAt(i));
      }
      this.length = chars.size();
      this.state = new ArrayList<Character>();
      for (int i = 0; i < length; i++) {
         state.add('_');
      }
      this.chances = 10;
      this.userWin = false;
      this.guesses = new ArrayList<Character>();
      printState();
      System.out.println();
   }
   
   public void printState() {
      for (char c: state) {
         System.out.print(c);
         System.out.print(" ");
      }
      System.out.println();
   }
   
   public void editState(int index, char c) {
      state.set(index, c);
   }
   
   public void guess(char c) {
      if (!guesses.contains(c)) {
         guesses.add(c);
         if (chars.contains(c)) {
            for (int i = 0; i < length; i++) {
               if (chars.get(i) == c) {
                  state.set(i, c);
               }
            }
            if (!state.contains('_')) {
               userWin = true;
            }
         } else {
            System.out.println("Not that one!");
            chances--;
         }
      } else {
         System.out.println("You have already guessed this letter. Please try again.");
      }
   }
   
   public int getChances() {
      return this.chances;
   }
   
   public boolean userWin() {
      return userWin;
   }
}