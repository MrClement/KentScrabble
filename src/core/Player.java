package core;

import java.util.ArrayList;

public abstract class Player {
	private int score = 0;
	private ArrayList<Letter> availableCharacters = new ArrayList<Letter>();
	private LetterBag letterSack;
	private int numLetters = 0;

	public Player(LetterBag ls) {
		letterSack = ls;
		setNumLetters(7);
		setAvailableCharacters();

	}

	public abstract Word makeMove(Board b);

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public void setAvailableCharacters() {
		availableCharacters = letterSack.draw(getNumLetters());
		setNumLetters(0);
	}

	public void incrementScore(int i) {
		score += i;
	}

	public ArrayList<Letter> getAvailableCharacters() {
		return availableCharacters;
	}

	public int getNumLetters() {
		return numLetters;
	}

	public void setNumLetters(int numLetters) {
		this.numLetters = numLetters;
	}

}
