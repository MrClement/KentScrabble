import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {

	private String filename = "dictionary.txt";
	private HashMap<Integer, String> allWords;

	public Dictionary() {
		allWords = new HashMap<Integer, String>();
		Scanner scan = new Scanner(Dictionary.class.getClassLoader().getResourceAsStream(filename));
		String line = "";
		Word w;
		while (scan.hasNext()) {
			line = scan.nextLine();
			w = new Word(line);
			allWords.put(w.getVal(), line);
		}
	}

	public boolean isWord(String guess) {
		guess = guess.toUpperCase();
		return allWords.containsValue(guess);
	}

	public HashMap<Integer, String> getAllWords() {
		return allWords;
	}

}
