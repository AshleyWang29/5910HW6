import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {

	private String target;
	private ArrayList<String> largestWordFamily;
	private ArrayList<Character> partialSolution;
	private int missingChars;

	public Solution(ArrayList<String> list, int length) {
		// init partialSolution like "______"
		missingChars = length;
		partialSolution = new ArrayList<>(missingChars);
		for (int i = 0; i < length; i++) {
			partialSolution.add('_');
		}

		// init wordFamily with all words whose length satisfied the parameter "length"
		largestWordFamily = filterByLength(list, length);

		// init target with any string not in dictionary, target is unknown currently.
		target = "_";
	}

	private ArrayList<String> filterByLength(ArrayList<String> list, int length) {
		ArrayList<String> result = new ArrayList<>();
		for (String s : list) {
			if (s.length() == length) {
				result.add(s);
			}
		}
		return result;
	}

	public boolean isSolved() {
		return missingChars == 0;
	}

	public void printProgress() {
		for (char c : partialSolution) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	// Partition the word list into families based on guessed letters
	private HashMap<String, ArrayList<String>> partitionWordFamilies(ArrayList<String> wordList, char guessedLetter) {
		HashMap<String, ArrayList<String>> wordFamilies = new HashMap<>();

		for (String word : wordList) {
			// Generate the pattern for the current word
			String pattern = generatePattern(word, guessedLetter);

			// Add the word to the corresponding family
			wordFamilies.putIfAbsent(pattern, new ArrayList<>());
			wordFamilies.get(pattern).add(word);
		}

		return wordFamilies;
	}

	// Generate a pattern for the word based on the guessed letter
	private String generatePattern(String word, char guessedLetter) {
		StringBuilder pattern = new StringBuilder();

		for (char c : word.toCharArray()) {
			if (c == guessedLetter) {
				pattern.append(c);
			} else {
				pattern.append('-');
			}
		}

		return pattern.toString();
	}


	public boolean addGuess(char guess) {
		HashMap<String, ArrayList<String>> families = partitionWordFamilies(largestWordFamily, guess);
		/*
		  To be "evil", we want the user guess as more times as possible, so if two families have the same length and
		  one contains the character the user is currently guessing but the other does not, we tend to choose the one
		  that is not included. And if there are multiple families that are not included, we randomly choose one.

		  i.e. Now candidate words are: "tea", "tec", and uer already confirm character "t" and "e", when user guesses
		  "a", we choose "tec" to be the final target and show "Incorrect guesses" to the user.

		  To realize this, we need 2 steps as below:
		      step1: find the length of largest word families;
		      step2: select the family that is not included
		 */

		// find the length of largest word families;
		int largestLength = -1;
		for (Map.Entry<String, ArrayList<String>> entry : families.entrySet()) {
			if (entry.getValue().size() >= largestLength) {
				largestLength = entry.getValue().size();
			}
		}

		// select the family that is not included
		String oneLargestkey = "";
		String largestAndNoContainGuessKey = "";
		for (Map.Entry<String, ArrayList<String>> entry : families.entrySet()) {
			if (entry.getValue().size() == largestLength) {
				oneLargestkey = entry.getKey();
				if (!entry.getKey().contains(String.valueOf(guess))) {
					largestAndNoContainGuessKey = entry.getKey();
				}
			}
		}
		String key = largestAndNoContainGuessKey.isEmpty() ? oneLargestkey : largestAndNoContainGuessKey;
		largestWordFamily = families.get(key);

		boolean guessCorrect = false;
		for (int i = 0; i < key.length(); i++) {
			if (key.charAt(i) == guess) {
				partialSolution.set(i, guess);
				missingChars--;
				guessCorrect = true;
			}
		}
		if (missingChars == 0) {
			StringBuilder sb = new StringBuilder();
			for (Character ch : partialSolution) {
				sb.append(ch);
			}
			target = sb.toString();
		}
		return guessCorrect;
	}

	public String getTarget() {
		return target;
	}

	public ArrayList<Character> getPartialSolution() {
		return partialSolution;
	}

	public ArrayList<String> getLargestWordFamily() {
		return largestWordFamily;
	}

	public int getMissingChars() {
		return missingChars;
	}
}
