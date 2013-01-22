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
	private ArrayList<ArrayList> getLettersFromBoard(Board b){
		ArrayList<ArrayList> y=new ArrayList<ArrayList>();
		Space[][] a=b.getArr();
		for(int i=0;i<a.length;i++){
				for(int j=0;j<a[i].length;j++){
					if(a[i][j].getLetter().getCharacter()!='0'){
						ArrayList test=new ArrayList();
						test.add(a[i][j].getLetter());
						test.add(new Point(i, j));
						y.add(test);
						
					}
				}
		}
		return y;
	}
	
	private int getFarL(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());

		int farL;
		int c=-1;
		while(x+c>-1&&b.getArr()[x+c][y].getLetter().getCharacter()=='0'){
			c--;
		}
		farL=x+c;
		return farL;
	}
	
	private int getFarR(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());
		
		int farR;
		int d=1;
		while(x+d<15&&b.getArr()[x+d][y].getLetter().getCharacter()=='0'){
			d++;
		}
		farR=x+d;
		return farR;
	}
	
	private int getFarU(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());int farU;
		int r=-1;
		while(y+r>0&&b.getArr()[x][y+r].getLetter().getCharacter()=='0'){
			r--;
		}
		farU=y+r;
		return farU;
	}
	
	private int getFarD(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());
		int farD;
		int w=1;
		while(y+w<15&&b.getArr()[x][y+w].getLetter().getCharacter()=='0'){
			w++;
		}
		farD=x+w;
		return farD;
	}
	
	//for any given letter in the arraylist, fill that letter's arraylist with empty words placemarking all of the different sizes 
	//and directions of the possible words that could go around that space
	private ArrayList<Word> findWordLengths(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		ArrayList<Word> l=new ArrayList<Word>();	
		int x,y;
		x=(int)((Point)lettersFromBoard.get(letterIndex).get(1)).getX();
		y=(int)((Point)lettersFromBoard.get(letterIndex).get(1)).getY();
		
		//horizontal
		int farL, farR;
		farL=getFarL(lettersFromBoard, letterIndex, b);
		farR=getFarR(lettersFromBoard, letterIndex, b);

		for(int i=farL;i<=x;i++){
			for(int k=x;k<farR;k++){
					int length=farR-farL;
					String s="";
					for(int j=0;j<length;j++)s+=" ";
					l.add(new Word(s, new Point(i,y), 'H'));
			}
		}
		
		//vertical
		int farU=getFarU(lettersFromBoard, letterIndex, b);
		int farD=getFarU(lettersFromBoard, letterIndex, b);

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
		ArrayList<ArrayList>lettersFromBoardCopy=lettersFromBoard;		
		ArrayList<Letter> possLetters=new ArrayList<Letter>();
		for(int i=0;i<this.getLetters().size();i++)possLetters.add(this.getLetters().get(i));
		possLetters.add((Letter)lettersFromBoard.get(letterIndex).get(0));
		for(int i=2;i<lettersFromBoard.get(letterIndex).size();i++){
			ArrayList<Word> wordsOfSize=new ArrayList<Word>();
			ArrayList<Word> possWords=new ArrayList<Word>();
			for(String s:dic.getAllWords()){
				if(((Word)lettersFromBoard.get(letterIndex).get(i)).getWord().length()==s.length())wordsOfSize.add(new Word(s));
			}
			for(Word s:wordsOfSize){
				boolean wo=true;
				for(int ch=0;ch<s.getWord().length();ch++){
					if(!possLetters.contains(new Letter(s.getWord().charAt(ch)))){
						wo=false;
						break;
					}
				}
				
				if (wo=true)possWords.add(s);
			}
			
			int maxIndex=0;
			int max=0;
			for(Word s:possWords)if(s.getVal()>max)maxIndex=possWords.indexOf(s);
			lettersFromBoardCopy.get(letterIndex).remove(wordIndex);
			lettersFromBoardCopy.get(letterIndex).add(wordIndex, possWords.get(maxIndex));
			}
		
		return lettersFromBoardCopy;
	
	}
	
	public Word makeMove(Board b){
		return null;
	}

	public static void main(String[] args){
		LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		Word d=new Word("E", new Point(7,7), 'H');
		b.addWord(d);
		ArrayList<ArrayList> e=new ArrayList<ArrayList>();
		ArrayList f=new ArrayList();
		f.add(new Letter('A'));
		f.add(new Point(7,7));
		e.add(f);
		for(int i=0;i<c.findWordLengths(e,0,b).size();i++){
			e.get(0).add(c.findWordLengths(e,0,b).get(i));
		}

		c.fillWords(e,0,2);
		for(int i=2;i<e.get(0).size();i++){
			System.out.print(e.get(0).get(i)+", ");
			System.out.println(((Word)e.get(0).get(i)).getWord().length());
		}
		
	}
}

