import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class EvilHangmanGame {
    private ArrayList<String> wordList;
    private String currentPattern;
    private ArrayList<Character> incorrectGuesses;

    public EvilHangmanGame(ArrayList<String> dictionary, int wordLength) {
        // Initialize with words of the chosen length
        wordList = new ArrayList<>();
        for (String word : dictionary) {
            if (word.length() == wordLength) {
                wordList.add(word);
            }
        }
        currentPattern = String.join("", Collections.nCopies(wordLength, "_"));
        incorrectGuesses = new ArrayList<>();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            System.out.println("Current word: " + currentPattern);
            System.out.println("Incorrect guesses: " + incorrectGuesses);

            System.out.print("Enter your guess: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input. Please guess a single letter.");
                continue;
            }

            char guessedLetter = input.charAt(0);
            if (currentPattern.contains(String.valueOf(guessedLetter)) || incorrectGuesses.contains(guessedLetter)) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            processGuess(guessedLetter);
        }

        System.out.println("Game over! The word was: " + wordList.get(0));
    }

    private void processGuess(char guessedLetter) {
        HashMap<String, ArrayList<String>> wordFamilies = new EvilHangman().partitionWordFamilies(wordList, guessedLetter);

        // Find the largest word family
        String bestPattern = null;
        int maxSize = 0;

        for (String pattern : wordFamilies.keySet()) {
            int size = wordFamilies.get(pattern).size();
            if (size > maxSize) {
                maxSize = size;
                bestPattern = pattern;
            }
        }

        // Update the current pattern and word list
        if (bestPattern != null && bestPattern.equals(currentPattern)) {
            incorrectGuesses.add(guessedLetter);
        } else {
            currentPattern = bestPattern;
        }

        wordList = wordFamilies.get(currentPattern);
    }

    private boolean isGameOver() {
        return !currentPattern.contains("_") || wordList.size() == 1;
    }
}
