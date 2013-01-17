package core;
import java.util.ArrayList;

public abstract class Player {
	
	public static final int numLetters=7;
	protected ArrayList<Letter> letters;
	protected int score;
	protected LetterBag bag;
	
	public Player(LetterBag bag){
		letters = new ArrayList<Letter>();
		this.bag = bag;
		score = 0;
	}
	
	public abstract Word makeMove(Board b);
	
	public int getScore(){
		return score;
	}
	
	public ArrayList<Letter> getLetters(){
		return letters;
	}
	
	public void draw(int num){
		for(int i=0;i<num;i++){
			letters.add(bag.draw(num).get(i));
		}
	}
	
	public void fill(LetterBag a){
		while(letters.size()<7||a.getSize()>0){
			letters.add(a.draw(1).get(0));
		}
	}
	
	public void addScore(int i){
		score+=i;
	}
	
	
}


/*

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

*/
