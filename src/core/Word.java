package core;

import java.awt.Point;
import java.io.Serializable;

public class Word implements Serializable {

	private static final long serialVersionUID = 1L;
	private String word;
	private Letter[] wordL;
	private int val;
	private Point location;
	private char direction;

	public Word(String s) {
		word = s;
		wordL = new Letter[word.length()];
		transfer();
		val = valCalc();
		location = new Point();
		direction = 'H';
	}

	public Word(String s, Point loc, char dir) {
		word = s;
		wordL = new Letter[word.length()];
		transfer();
		val = valCalc();
		location = new Point(loc);
		direction = dir;
	}

	public Word(Letter[] a, Point loc, char dir) {
		word = "";
		wordL = new Letter[a.length];
		for (int i = 0; i < a.length; i++) {
			wordL[i] = a[i];
			word += a[i].getCharacter();
		}
		val = valCalc();
		location = new Point(loc);
		direction = dir;
	}

	private void transfer() {
		for (int i = 0; i < word.length(); i++) {
			wordL[i] = new Letter(word.charAt(i));
		}
	}

	public int valCalc() {
		int v = 0;
		for (int i = 0; i < wordL.length; i++) {
			v += wordL[i].getVal();
		}
		return v;
	}

	public String getWord() {
		return word;
	}

	public Letter[] getWordInLetters() {
		return wordL;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int i) {
		val = i;
	}

	public Point getLocation() {
		return location;
	}

	public char getDirection() {
		return direction;
	}

	public void setLocation(int x, int y) {
		location.setLocation(x, y);
	}

	public void setLocation(Point p) {
		location.setLocation(p);
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public String toString() {
		return this.getWord();
	}

}
