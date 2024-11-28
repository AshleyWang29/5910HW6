import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

public class EvilHangman {

	private ArrayList<String> wordList;
	private HashSet<Character> previousGuesses;
	private TreeSet<Character> incorrectGuesses; // behaves like a hash set, but orders the entries!
	private Solution solution;
	private Scanner inputScanner;

	public EvilHangman() {
		this("engDictionary.txt");
	}

	public EvilHangman(String filename) {
		try {
			wordList = dictionaryToList(filename);
		} catch (IOException e) {
			System.out.printf(
					"Couldn't read from the file %s. Verify that you have it in the right place and try running again.",
					filename);
			e.printStackTrace();
			System.exit(0); // stop the program--no point in trying if you don't have a dictionary
		}

		previousGuesses = new HashSet<>();
		incorrectGuesses = new TreeSet<>();

		int randomLength = getRandomLength(wordList);

		solution = new Solution(wordList, randomLength);
		inputScanner = new Scanner(System.in);

	}


		int randomIndex = new Random().nextInt(lengthSet.size());

        return new ArrayList<>(lengthSet).get(randomIndex);
	}

	public void start() {
		while (!solution.isSolved()) {
			char guess = promptForGuess();
			recordGuess(guess);
		}
		printVictory();
	}

	private char promptForGuess() {
		while (true) {
			System.out.println("Guess a letter.\n");
			solution.printProgress();
			System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
			String input = inputScanner.next();
			if (input.length() != 1 || !isLowerEnglishLetter(input.charAt(0))) {
				System.out.println("Please enter a single character in lowercase.");
			} else if (previousGuesses.contains(input.charAt(0))) {
				System.out.println("You've already guessed that.");
			} else {
				return input.charAt(0);
			}
		}

	}

	public boolean isLowerEnglishLetter(char ch) {
		return Character.isLetter(ch) && 'a' <= ch && ch <= 'z';
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	private void recordGuess(char guess) {
		previousGuesses.add(guess);
		boolean isCorrect = solution.addGuess(guess);
		if (!isCorrect) {
			incorrectGuesses.add(guess);
		}
	}

	private void printVictory() {
		System.out.printf("Congrats! The word was %s%n", solution.getTarget());
	}

	private static ArrayList<String> dictionaryToList(String filename) throws IOException {
		FileInputStream fs = new FileInputStream(filename);
		Scanner scnr = new Scanner(fs);

		ArrayList<String> wordList = new ArrayList<>();

		while (scnr.hasNext()) {
			wordList.add(scnr.next());
		}

		return wordList;
	}

}
