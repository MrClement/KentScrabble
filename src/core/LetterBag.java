package core;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Alex Patel
 * 
 *         The collection of all the tiles used in a game. Starts with 100 tiles
 *         with the original Scrabble distribution.
 * 
 * 
 */
public class LetterBag {
	private ArrayList<Letter> bag;
	public static int numLetters = 100;

	public LetterBag() {
		bag = new ArrayList<Letter>();
		fill();
	}

	/**
	 * @return number of tiles in bag
	 */
	public int getSize() {
		return bag.size();
	}

	/**
	 * fills bag with original Scrabble distribution of letters. Randomly fills
	 * the bag if more letters requested and randomly takes away letters of low
	 * value away if less
	 */
	public void fill() {
		// normal letter distribution
		for (int i = 1; i < 13; i++) {
			bag.add(new Letter('E'));
			if (i < 2) {
				bag.add(new Letter('K'));
				bag.add(new Letter('J'));
				bag.add(new Letter('X'));
				bag.add(new Letter('Q'));
				bag.add(new Letter('Z'));
			}
			if (i < 3) {
				bag.add(new Letter('1'));
				bag.add(new Letter('B'));
				bag.add(new Letter('C'));
				bag.add(new Letter('M'));
				bag.add(new Letter('P'));
				bag.add(new Letter('F'));
				bag.add(new Letter('H'));
				bag.add(new Letter('V'));
				bag.add(new Letter('W'));
				bag.add(new Letter('Y'));
			}
			if (i < 4) {
				bag.add(new Letter('G'));
			}
			if (i < 5) {
				bag.add(new Letter('L'));
				bag.add(new Letter('S'));
				bag.add(new Letter('U'));
				bag.add(new Letter('D'));
			}
			if (i < 7) {
				bag.add(new Letter('N'));
				bag.add(new Letter('R'));
				bag.add(new Letter('T'));
			}
			if (i < 9) {
				bag.add(new Letter('O'));
			}
			if (i < 10) {
				bag.add(new Letter('A'));
				bag.add(new Letter('I'));
			}
		}

		// If more than 100 letters wanted in the bag, then add random letters
		// on top of normal distribution
		if (numLetters > 100) {
			for (int i = 0; i < numLetters - 100; i++) {
				String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				Random r = new Random();
				bag.add(new Letter(alph.charAt(r.nextInt(26))));
			}
		}

		// If fewer than 100 letters want in the bag, than take out random 1 and
		// 2 point letters until number is reached
		else if (numLetters < 100) {
			for (int i = 0; i < 100 - numLetters; i++) {
				Random r = new Random((long) (Math.random() * 466983456));
				int q = r.nextInt(bag.size());
				while (bag.get(q).getVal() != 1 || bag.get(q).getVal() != 2) {
					q = r.nextInt(bag.size());
				}
				bag.remove(q);
			}
		}

	}

	/**
	 * @param how
	 *            many (random) letters to draw from the bag
	 * @return ArrayList<Letter> with the requested random pieces
	 * 
	 *         also removes the pieces from the collection after given to player
	 */
	public ArrayList<Letter> draw(int numLetters) {
		ArrayList<Letter> d = new ArrayList<Letter>();
		for (int i = 0; i < numLetters; i++) {
			Random r = new Random();
			int j = r.nextInt(bag.size());
			d.add(new Letter(bag.get(j).getCharacter()));
			if (bag.size() > 0)
				bag.remove(j);
			j=r.nextInt(bag.size());
		}
		return d;
	}
}
