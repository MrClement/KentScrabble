package SqueezyAI;
import core.*;

import java.awt.Point;
import java.util.ArrayList;

public class Squeezy extends Player{
	
	public Squeezy(LetterBag a){
		super(a);
	}
	
	//return ArrayList of ArrayList, with one ArrayList for each letter already placed on the board
	//the first box in each letter's ArrayList should be the Letter, the second is a Point with the letter's location on the board
	private ArrayList<ArrayList> getLettersFromBoard(){
		return null;
	}
	
	//for any given letter in the arraylist, fill that letter's arraylist with empty words placemarking all of the different sizes 
	//and directions of the possible words that could go around that space
	private ArrayList<Word> findWordLengths(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		ArrayList<Word> l=new ArrayList<Word>();	
		int x,y;
		x=(int)((Point)lettersFromBoard.get(letterIndex).get(1)).getX();
		y=(int)((Point)lettersFromBoard.get(letterIndex).get(1)).getY();
		//horizontal
		int farL;
		int c=0;
		while(b.getArr()[c][y].getLetter().getCharacter()=='0'){
			c--;
		}
		farL=x+c;
		
		int farR;
		int d=0;
		while(b.getArr()[d][y].getLetter().getCharacter()=='0'){
			d++;
		}
		farR=x+d;

		for(int i=farL;i<x;i++){
			for(int k=x;k<farR;k++){
					int length=farR-farL;
					String s="";
					for(int j=0;j<length;j++)s+=" ";
					l.add(new Word(s, new Point(i,y), 'H'));
			}
		}
		
		//vertical
		int farU;
		int r=0;
		while(b.getArr()[x][r].getLetter().getCharacter()=='0'){
			r--;
		}
		farU=y+r;
		
		int farD;
		int w=0;
		while(b.getArr()[x][w].getLetter().getCharacter()=='0'){
			w++;
		}
		farD=x+w;

		for(int i=farU;i<y;i++){
			for(int k=y;k<farD;k++){
					int length=farD-farU;
					String s="";
					for(int j=0;j<length;j++)s+=" ";
					l.add(new Word(s, new Point(i,y), 'V'));
			}
		}
		
		return l;
		
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
