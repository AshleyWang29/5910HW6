import java.util.ArrayList;
import java.util.HashMap;

public class EvilHangman {

    // Partition the word list into families based on guessed letters
    public HashMap<String, ArrayList<String>> partitionWordFamilies(ArrayList<String> wordList, char guessedLetter) {
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
                pattern.append('_');
            }
        }

        return pattern.toString();
    }
}
