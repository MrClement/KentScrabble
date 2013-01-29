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
	
	public int getFarL(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());

		int farL;
		int c=-1;
		while(x+c>0&&b.getArr()[x+c][y].getLetter().getCharacter()=='0'){
			c--;
		}
		farL=x+c+1;
		if(x+c==0)farL=0;
		return farL;
	}
	
	public int getFarR(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());
		
		int farR;
		int d=1;
		while(x+d<15&&b.getArr()[x+d][y].getLetter().getCharacter()=='0'){
			d++;
		}
		farR=x+d-1;
		if(x+d==14)farR=15;
		return farR;
	}
	
	public int getFarU(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());int farU;
		int r=-1;
		while(y+r>0&&b.getArr()[x][y+r].getLetter().getCharacter()=='0'){
			r--;
		}
		farU=y+r+1;
		if(y+r==0)farU=0;

		return farU;
	}
	
	public int getFarD(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());
		int farD;
		int w=1;
		while(y+w<15&&b.getArr()[x][y+w].getLetter().getCharacter()=='0'){
			w++;
		}
		farD=y+w-1;
		if(y+w==14)farD=14;
		return farD;
	}
	
	//for any given letter in the arraylist, fill that letter's arraylist with empty words placemarking all of the different sizes 
	//and directions of the possible words that could go around that space
	private ArrayList<Word> findWordLengths(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
		ArrayList blanks=new ArrayList();
		
		int farL, farR, farU, farD;
		farL=this.getFarL(lettersFromBoard, letterIndex, b);
		farR=this.getFarR(lettersFromBoard, letterIndex, b);
		farU=this.getFarU(lettersFromBoard, letterIndex, b);
		farD=this.getFarD(lettersFromBoard, letterIndex, b);
		
		int x=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getX());
		int y=(int)(((Point)lettersFromBoard.get(letterIndex).get(1)).getY());
		
		for(int i=farL; i<x;i++){
			for(int j=x;x<=farR;x++){
				if(j-i<=8){
					String s="";
					for(int e=0;e<j-i;e++){
						s+=" ";
					}
					Word t=new Word(s, new Point(i, y), 'H');
					blanks.add(t);
				}
			}
		}
		
		for(int i=farU; i<y;i++){
			for(int j=y;j<=farD;j++){
				if(j-i<=8){
					String s="";
					for(int e=0;e<j-i;e++){
						s+=" ";
					}
					Word t=new Word(s, new Point(x, i), 'V');
					blanks.add(t);
				}
			}
		}

		return blanks;
	}
	
	private ArrayList<Word> getPossWords(Word blank, ArrayList<Letter> letters){
		return null;
	}
	
	private Word bestWord(ArrayList<Word> a)
	{
		return null;
	}
	//for any given word length and direction for any given letter on the board, find the word of the highest point value
	//that includes only letters from squeezy's tray and the letter placed on the board
	private ArrayList<ArrayList> fillWords(ArrayList<ArrayList> lettersFromBoard, int letterIndex, int wordIndex){
		Word a=(Word)lettersFromBoard.get(letterIndex).get(wordIndex);
		ArrayList<Letter>letters=this.getLetters();
		for(int i=0;i<getLetters().size();i++){
			
		}
		letters.add((Letter)lettersFromBoard.get(letterIndex).get(0));
		
		
		return null;
		
		/*ArrayList<ArrayList>lettersFromBoardCopy=lettersFromBoard;		
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
		*/
	
	}
	
	//source - le internet - http://stackoverflow.com/questions/4950085/permutations-of-a-string
	public ArrayList<String> perm(ArrayList<String> a, String b, String c){
		ArrayList<String>d=a;
	    if(c.length() <= 1)
	    		d.add(b+c);
	    else{
	        for(int i=0; i <c.length();i++){
	           perm(d, b+c.charAt(i), c.substring(0, i) + c.substring(i+1, c.length()));
	        }           
	    }
	    return d;
	}
	
	public Word makeMove(Board b){
		ArrayList<ArrayList> a=new ArrayList<ArrayList>();
		a=getLettersFromBoard(b);
		for(int i=0;i<a.size();i++){
			a.add(i, findWordLengths(a, i, b));
			a.remove(i+1);
		}
		for(int i=0;i<a.size();i++){
			for(int k=0;k<a.get(i).size();k++){
				
			}
		}
		return null;
	}

	
	public static void main(String[] args){
		/*LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		Word d=new Word("E", new Point(7,7), 'H');
		b.addWord(d);
		Word f=new Word("E", new Point(4,7), 'V');
		b.addWord(f);
		System.out.println(c.getLettersFromBoard(b).get(0).get(0));
		System.out.println(c.getLettersFromBoard(b).get(0).get(1));
		
		System.out.println(c.getFarL(c.getLettersFromBoard(b), 0, b));
		System.out.println(c.getFarR(c.getLettersFromBoard(b), 0, b));
		System.out.println(c.getFarU(c.getLettersFromBoard(b), 0, b));
		System.out.println(c.getFarD(c.getLettersFromBoard(b), 0, b));
		
		for(int i=2;i<c.findWordLengths(c.getL[LettersFromBoard(b), 0, b).size();i++){
			System.out.println(((Word)c.findWordLengths(c.getLettersFromBoard(b), 0, b).get(i)).getWord());
		}
		*/
		
		LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		
		for(int i=0;i<c.perm(new ArrayList<String>(), "", "horse").size();i++){
			System.out.println(c.perm(new ArrayList<String>(), "", "horse").get(i));
		}
		
		
	}
}

