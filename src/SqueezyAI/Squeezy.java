package SqueezyAI;
import core.*;

import java.awt.Point;
import java.util.ArrayList;

public class Squeezy extends Player{
	SqueezyDictionary dic=new SqueezyDictionary();
	
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
		int c=-1;
		while(x+c>0&&b.getArr()[x+c][y].getLetter().getCharacter()=='0'){
			c--;
		}
		farL=x+c;
		
		int farR;
		int d=1;
		while(x+d<15&&b.getArr()[x+d][y].getLetter().getCharacter()=='0'){
			d++;
		}
		farR=x+d;

		for(int i=farL;i<=x;i++){
			for(int k=x;k<farR;k++){
					int length=farR-farL;
					String s="";
					for(int j=0;j<length;j++)s+=" ";
					l.add(new Word(s, new Point(i,y), 'H'));
			}
		}
		
		//vertical
		int farU;
		int r=-1;
		while(y+r>0&&b.getArr()[x][y+r].getLetter().getCharacter()=='0'){
			r--;
		}
		farU=y+r;
		
		int farD;
		int w=1;
		while(y+w<15&&b.getArr()[x][y+w].getLetter().getCharacter()=='0'){
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
	private ArrayList<ArrayList> fillWords(ArrayList<ArrayList> lettersFromBoard){
		for(int v=0;v<lettersFromBoard.size();v++){
		for(int z=0;z<lettersFromBoard.get(v).size();z++){
			
		ArrayList<Letter> possLetters=new ArrayList<Letter>();
		ArrayList<ArrayList>lettersFromBoardCopy=lettersFromBoard;
		for(int i=0;i<this.getLetters().size();i++)possLetters.add(this.getLetters().get(i));
		possLetters.add((Letter)lettersFromBoard.get(v).get(0));
		
		for(int i=2;i<lettersFromBoard.get(v).size();i++){
			ArrayList<Word> wordsOfSize=new ArrayList<Word>();
			ArrayList<Word> possWords=new ArrayList<Word>();
			for(String s:dic.getAllWords()){
				if(((Word)lettersFromBoard.get(v).get(i)).getWord().length()==s.length())wordsOfSize.add(new Word(s));
			}
			for(Word s:wordsOfSize){
				int p=0;
				while(((String)s.getWord()).contains(""+possLetters.get(p).getCharacter()))p++;
				if(p==possLetters.size())possWords.add(s);
			}
			
			int maxIndex=0;
			int max=0;
			for(Word s:possWords)if(s.getVal()>max)maxIndex=possWords.indexOf(s);
			
			lettersFromBoardCopy.get(v).add(z, possWords.get(maxIndex));
		}
		}
		}
		
		
		
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

	public static void main(String[] args){
		LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		Word d=new Word("A", new Point(7,7), 'H');
		b.addWord(d);
		ArrayList<ArrayList> e=new ArrayList<ArrayList>();
		ArrayList f=new ArrayList();
		f.add(new Letter('A'));
		f.add(new Point(7,7));
		e.add(f);
		System.out.println(b.getArr()[7][7].getLetter().getCharacter());
		System.out.print(c.findWordLengths(e,0,b).size());
	}
}

