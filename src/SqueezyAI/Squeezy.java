package SqueezyAI;
import core.*;
import java.util.ArrayList;

public class Squeezy extends Player{

	private Board b;
	
	public Squeezy(LetterBag a, Board b) {
		super(a);
		updateBoard(b);
	}
	
	//update Squeezy's board to match a given board
	public void updateBoard(Board b){
	}
	
	//return ArrayList of ArrayList, with one ArrayList for each letter already placed on the board
	//the first box in each letter's ArrayList should be the Letter, the second is a Point with the letter's location on the board
	private ArrayList<ArrayList> getLettersFromBoard(){
		return null;
	}
	
	//for any given letter in the arraylist, fill that letter's arraylist with empty words placemarking all of the different sizes 
	//and directions of the possible words that could go around that space
	private ArrayList<ArrayList> findWordLengths(ArrayList<ArrayList> lettersFromBoard, int letterIndex){
		return null;
	}
	
	//for any given word length and direction for any given letter on the board, find the word of the highest point value
	//that includes only letters from squeezy's tray and the letter placed on the board
	private ArrayList<ArrayList> fillWords(ArrayList<ArrayList> lettersFromBoard, int letterIndex, int wordIndex){
		return null;
	}
	
	//returns an ArrayList with all of the words that are the length of size and include the letter letterFromBoard
	private ArrayList getWordsOfLength(int size, char letterFromBoard){
		return null;
	}
	
	//Find the word of the highest point value for any given letter
	private int getHighestPointWord_letter (ArrayList<ArrayList> a, int letterIndex){
		return 0;
	}	
	
	public Word makeMove(Board b){
		return null;
	}
}
