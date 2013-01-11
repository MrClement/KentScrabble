package core;

import java.util.ArrayList;

public abstract class Player {
	private int score = 0;
	private ArrayList<Letter> availableCharacters = new ArrayList<Letter>();
	private LetterBag letterSack;
	private int numLetters = 0;

	public Player(LetterBag ls) {
		letterSack = ls;
		numLetters = 7;
		setAvailableCharacters();

	}

	public abstract Word makeMove();

	public int getScore() {
		return score;
	}

	public void setScore(int i) {
		score = i;
	}

	public void setAvailableCharacters() {
		availableCharacters = letterSack.draw(numLetters);
		numLetters = 0;
	}

	public void incrementScore(int i) {
		score += i;
	}

}
