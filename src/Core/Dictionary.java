package Core;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author MrClement
 * 
 *         The Dictionary class is designed to check whether words are in the
 *         scrabble dictionary and provide a list of all valid scrabble words
 * 
 */
public class Dictionary {

	private String filename = "dictionary.txt";
	private HashMap<String, Integer> allWords;

	public Dictionary() {
		allWords = new HashMap<String, Integer>();
		Scanner scan = new Scanner(Dictionary.class.getClassLoader().getResourceAsStream(filename));
		String line = "";
		Word w;
		while (scan.hasNext()) {
			line = scan.nextLine();
			w = new Word(line);
			allWords.put(line, w.getVal());
		}
		scan.close();
	}

	/**
	 * isWord takes a word returns its point value if it is a valid word, if it
	 * is not it return -1
	 * 
	 * @param guess
	 * @return -1 if invalid, point value if valid
	 */
	public int isWord(String guess) {
		guess = guess.toUpperCase();
		return allWords.containsKey(guess) ? allWords.get(guess) : -1;
	}

	/**
	 * @return a HashMap of all valid scrabble words (key) and their base values
	 *         (value)
	 */
	public HashMap<String, Integer> getAllWords() {
		return allWords;
	}

}
