package SqueezyAI;
import core.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Squeezy extends Player{
	Dictionary dic=new Dictionary();
	
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
	
	private Word bestWord(ArrayList<Word> a)
	{
		return null;
	}
	//for any given word length and direction for any given letter on the board, find the word of the highest point value
	//that includes only letters from squeezy's tray and the letter placed on the board
	private ArrayList<ArrayList> fillWords(ArrayList<ArrayList> lettersFromBoard, int letterIndex, int wordIndex){
		
		//gets current word, ex: "11a1" (1=blank)
		Board randBoard=new Board();
		Space[][] tempSpaceArray=randBoard.getArr();
		Word a=(Word)lettersFromBoard.get(letterIndex).get(wordIndex);
		//this letter cannot be counted for double letters, etc.
		int indexOfCurrLetterAlreadyPlayed= -1;
		for(int i=0; i<a.toString().length(); i++){
			if(a.toString().substring(i,i+1)!="1") indexOfCurrLetterAlreadyPlayed= i;
		}
		
		ArrayList<Word> legalWordsOfLengthX = new ArrayList<Word>();
		legalWordsOfLengthX = getPossWords(a,this.getLetters());
		int numLettersPerWord= legalWordsOfLengthX.get(0).getWord().length();
		//add in arraylist of arraylist word in first index and score in 2nd index
		for(int i=0; i<legalWordsOfLengthX.size(); i++)
		{
			boolean isDoubleWord=false;
			boolean isTripleWord=false;
			int totalPointValue= 0;
			char dir = legalWordsOfLengthX.get(i).getDirection();
			Point loc =legalWordsOfLengthX.get(i).getLocation();
			for(int j=0; j<numLettersPerWord; j++)
			{
				if(j==indexOfCurrLetterAlreadyPlayed)
				{
					totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
					if(j!=numLettersPerWord-1)j++;
				}
				
				else if (dir== 'H'){
					loc.setLocation(loc.getX()+j,loc.getY());
					String typeOfSpace = tempSpaceArray[(int)loc.getX()][(int)loc.getY()].getTypeString();
					if(typeOfSpace!="Normal")
					{
						if(typeOfSpace=="Double Word"){
							isDoubleWord=true;
							totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();		}
						if(typeOfSpace=="Triple Word"){
							isTripleWord=true;
							totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();		
						}
						if(typeOfSpace=="Double Letter"){
							int timestwo= 2 * legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
							totalPointValue+=timestwo;
						}
						if(typeOfSpace=="Triple Letter"){
							int timesthree= 3 * legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
							totalPointValue+=timesthree;
						}
					}
					totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
				}
				else if (dir=='V'){
					loc.setLocation(loc.getX(),loc.getY()+j);
					String typeOfSpace = tempSpaceArray[(int)loc.getX()][(int)loc.getY()].getTypeString();
					if(typeOfSpace!="Normal")
					{
						if(typeOfSpace=="Double Word"){
							isDoubleWord=true;
							totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();		}
						if(typeOfSpace=="Triple Word"){
							isTripleWord=true;
							totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();		
						}
						if(typeOfSpace=="Double Letter"){
							int timestwo= 2 * legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
							totalPointValue+=timestwo;
						}
						if(typeOfSpace=="Triple Letter"){
							int timesthree= 3 * legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
							totalPointValue+=timesthree;
						}
					}
					totalPointValue+=legalWordsOfLengthX.get(i).getWordInLetters()[j].getVal();
				}
				
				if (isDoubleWord) totalPointValue=totalPointValue*2;
				if (isTripleWord) totalPointValue=totalPointValue*3;
				
				}
				
			}
		
		// LINE OF CODE TO ADD WORD AND ITS POINT VALUE IN AN ARRAY... OR AM I FINDING JUST HIGHEST POINT VALUE WORD?
		
		
		
		
		
		//find direction of word
		//look in first letter location for doub/trip letter/word, add integer value multiplied right away by a doub/trip
		//letter, or make booleans doub/trip letter score to be multiplied in
		//then move over and check location based
		//on direction so have a location (0,x) and keep doing x+/-1
		//***can't check status of letter already on board (check for that)
		//add up total word then return arraylist of arraylistsvalues, store string word in 1st, location of first
		//letter in 2nd, value in 3rd. (**where does location go?)
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
	
	private ArrayList<Word> getPossWords(Word blank, ArrayList<Letter> letters){
		int letIndex=0;
		while(blank.getWord().charAt(letIndex)=='1'){
			letIndex++;
		}
		
		
		ArrayList<Letter> a=new ArrayList<Letter>();
		for(int i=0;i<letters.size();i++){
			a.add(letters.get(i));
		}
		a.add(new Letter(blank.getWord().charAt(letIndex)));
		
		String temp="";
		for(int i=0;i<a.size();i++){
			temp+=a.get(i).getCharacter()+"";
		}
		
		ArrayList<String> b=perm(new ArrayList<String>(), "", temp);
		
		ArrayList<Word> f=new ArrayList<Word>();
		Dictionary d=new Dictionary();
		for(int i=0;i<b.size();i++){
			if(b.get(i).charAt(letIndex)==blank.getWord().charAt(letIndex)){
				if(d.isWord(b.get(i))!=-1){
					f.add(new Word(b.get(i)));
				}
			}
		}
		
		return f;
		
	
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
		
		/*LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		
		for(int i=0;i<c.perm(new ArrayList<String>(), "", "horse").size();i++){
			System.out.println(c.perm(new ArrayList<String>(), "", "horse").get(i));
		}
		
		*/
		
		LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		
		ArrayList<Letter>d=new ArrayList<Letter>();
		d.add(new Letter('C'));
		d.add(new Letter('T'));
		d.add(new Letter('S'));
		d.add(new Letter('E'));
		d.add(new Letter('R'));


		Word s=new Word("1A1111", new Point(0,0), 'H');
		ArrayList<Word> g=c.getPossWords(s, d);
		for(int i=0;i<g.size();i++){
			System.out.println(g.get(i).getWord());
		}
	}
}

