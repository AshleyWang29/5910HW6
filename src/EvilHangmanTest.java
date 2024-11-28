import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

class EvilHangmanTest {
    private final EvilHangman evilHangman = new EvilHangman("engDictionary.txt");

    @Test
    void isEnglishLetter() {
        char ch1 = 'a';
        Assertions.assertTrue(evilHangman.isLowerEnglishLetter(ch1));
        ch1 = 'A';
        Assertions.assertFalse(evilHangman.isLowerEnglishLetter(ch1));
        ch1 = '1';
        Assertions.assertFalse(evilHangman.isLowerEnglishLetter(ch1));
        ch1 = '&';
        Assertions.assertFalse(evilHangman.isLowerEnglishLetter(ch1));
        ch1 = '/';
        Assertions.assertFalse(evilHangman.isLowerEnglishLetter(ch1));

    }

    @Test
    void getRandomLength() {
        int length = evilHangman.getRandomLength(evilHangman.getWordList());
        assert (length >= 2) : length + " too short, there is no such words.";
        assert (length <= 22) : length + " too long, there is no such words.";
    }

    @Test
    void EvilHangman() {
        EvilHangman e1 = new EvilHangman();
        assert (!e1.getWordList().isEmpty()) : "fail to init EvilHangman with default dictionary.";
        EvilHangman e2 = new EvilHangman("miniDictionary.txt");
        assert (e2.getWordList().size() == 9) : "fail to init EvilHangman with miniDictionary.txt.";
    }

    @Test
    void Solution() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("abcd");
        arrayList.add("efgh");
        arrayList.add("abcn");
        arrayList.add("xyz");

        Solution solution = new Solution(arrayList, 4);
        assert (Objects.equals(solution.getTarget(), "_")) : "fail to init target.";

        ArrayList<Character> partialSolution = solution.getPartialSolution();
        StringBuilder sb = new StringBuilder();
        for (Character ch : partialSolution) {
            sb.append(ch);
        }
        assert (Objects.equals(sb.toString(), "____")) : "fail to init partialSolution.";

        assert (solution.getMissingChars() == 4) : "fail to init missingChars.";

        assert (solution.getLargestWordFamily().size() == 3) : "fail to init largestWordFamily.";

        assert (!solution.isSolved()) : "isSolved() check failed.";

        assert (!solution.addGuess('z')) : "addGuess with 'z' check failed.";
        assert (solution.getMissingChars() == 4) : "update missingChars failed.";
        assert (solution.getLargestWordFamily().size() == 3) : "update largestWordFamily failed.";

        assert (!solution.addGuess('h')) : "addGuess with 'h' check failed.";
        assert (solution.getMissingChars() == 4) : "update missingChars failed.";
        assert (solution.getLargestWordFamily().size() == 2) : "update largestWordFamily failed.";

        assert (solution.addGuess('a')) : "addGuess with 'a' check failed.";
        assert (solution.getMissingChars() == 3) : "update missingChars failed.";
        assert (solution.getLargestWordFamily().size() == 2) : "update largestWordFamily failed.";

        assert (solution.addGuess('b')) : "addGuess with 'b' check failed.";
        assert (solution.addGuess('c')) : "addGuess with 'c' check failed.";
        assert (!solution.addGuess('d')) : "addGuess with 'd' check failed.";
        assert (solution.addGuess('n')) : "addGuess with 'n' check failed.";

        StringBuilder sb2 = new StringBuilder();
        for (Character ch : partialSolution) {
            sb2.append(ch);
        }
        assert (Objects.equals(sb2.toString(), "abcn")) : "fail to update partialSolution.";
        assert (solution.getMissingChars() == 0) : "fail to update missingChars.";
        assert (solution.getLargestWordFamily().size() == 1) : "fail to update largestWordFamily.";
        assert (solution.isSolved()) : "isSolved() check failed.";
        assert (Objects.equals(solution.getTarget(), "abcn")) : "update target failed.";
    }
}