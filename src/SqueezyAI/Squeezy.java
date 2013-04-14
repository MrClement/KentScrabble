package SqueezyAI;
import core.*;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Squeezy extends Player{
	Dictionary dic=new Dictionary();
	
	public Squeezy(LetterBag a){
		super(a);
	}
	
	public ArrayList<ArrayList> getLettersFromBoard(Board b){
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
	
	public ArrayList<ArrayList> findWordLengths(ArrayList<ArrayList> lettersFromBoard, Board b){
		ArrayList<ArrayList> a=new ArrayList<ArrayList>();
		for(int i=0;i<lettersFromBoard.size();i++){
			a.add(new ArrayList());
			a.get(i).add(lettersFromBoard.get(i).get(0));
			a.get(i).add(lettersFromBoard.get(i).get(1));
		}
		
		for(int letterIndex=0;letterIndex<lettersFromBoard.size();letterIndex++){
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
						String s="";
						int indOfLet=j-i;
						for(int e=0;e<j-i;e++){
							 s+="1";
						}
						Word t=new Word(s, new Point(i, y), 'H');
						blanks.add(t);
					
				}
			}
			
			for(int i=farU; i<y;i++){
				for(int j=y;j<=farD;j++){
						String s="";
						for(int e=0;e<j-i;e++){
							s+="1";
						}
						Word t=new Word(s, new Point(x, i), 'V');
						blanks.add(t);
				
				}
			}

			for(int t=0;t<blanks.size();t++){
				a.get(letterIndex).add(blanks.get(t));
			}
		}
		
		return a;
	}
	
	private ArrayList<ArrayList> fillAllWords(ArrayList<ArrayList> lettersFromBoard){
		ArrayList<ArrayList> t=new ArrayList<ArrayList>();
		for(int letterIndex=0;letterIndex<lettersFromBoard.size();letterIndex++){
			t.add(new ArrayList());
			t.get(letterIndex).add(lettersFromBoard.get(letterIndex).get(0));
			for(int wordIndex=0;wordIndex<lettersFromBoard.get(letterIndex).size();wordIndex++){
				t.get(letterIndex).add(fillWords(lettersFromBoard, letterIndex, wordIndex));
			}
		}
		return t;
	}
	
	private Word fillWords(ArrayList<ArrayList> lettersFromBoard, int letterIndex, int wordIndex){
		
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
		Word highestWord = new Word(legalWordsOfLengthX.get(0).getWordInLetters(),legalWordsOfLengthX.get(0).getLocation(), legalWordsOfLengthX.get(0).getDirection());
		//add in arraylist of arraylist word in first index and score in 2nd index
		int currHighValue=0;
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
				if (totalPointValue>currHighValue) {
					currHighValue=totalPointValue;
					highestWord = legalWordsOfLengthX.get(i);	
				}
				}
				
			}
		
		
		return highestWord;
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
		
		return null;
	}
	
	/*private static void cont(ScrabbleGUI w) {
		
		LetterBag a=new LetterBag();
		Board b=new Board();
		Squeezy c=new Squeezy(a);
		Word t=new Word("E", new Point(1,3), 'H');
		b.addWord(t);
		Word t1=new Word("T", new Point(3,4), 'H');
		b.addWord(t1);
		Word t2=new Word("R", new Point(5,8), 'H');
		b.addWord(t2);
		Word t3=new Word("A", new Point(7,14), 'H');
		b.addWord(t3);
		Word t4=new Word("S", new Point(9,12), 'H');
		b.addWord(t4);
		Word t5=new Word("E", new Point(11,3), 'H');
		b.addWord(t5);
		Word t6=new Word("I", new Point(13,1), 'H');
		b.addWord(t6);
		w.showBoard(b);
		c.makeMove(b);

	}
	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleGUI window = new ScrabbleGUI();
					window.getFrame().setVisible(true);
					window.getFrame().setTitle("Scarble");
					cont(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	*/
	
	/*
	 * private ArrayList<ArrayList> fillAllWords(ArrayList<ArrayList> lettersFromBoard){
			ArrayList<ArrayList> a = new ArrayList<ArrayList>();
			for(int i=0;i<lettersFromBoard.size();i++){
				a.add(new ArrayList());
				a.get(i).add(lettersFromBoard.get(i).get(0));
				a.get(i).add(lettersFromBoard.get(i).get(1));
				for (int k=0; k<lettersFromBoard.get(i).size();k++){
					a.get(i).add(getWord(lettersFromBoard, i, k));
				}
			}
			return a;
		}
		
		public Word getWord(ArrayList<ArrayList> lettersFromBoard, int letterIndex, int wordIndex){
			
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
			Word highestWord = new Word(legalWordsOfLengthX.get(0).getWordInLetters(),legalWordsOfLengthX.get(0).getLocation(), legalWordsOfLengthX.get(0).getDirection());
			//add in arraylist of arraylist word in first index and score in 2nd index
			int currHighValue=0;
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
					if (totalPointValue>currHighValue) {
						currHighValue=totalPointValue;
						highestWord = legalWordsOfLengthX.get(i);	
					}
					}
					
				}
			
			return highestWord;
		}*/

	
	public static void main(String[] args){
			LetterBag a=new LetterBag();
			Board b=new Board();
			Squeezy c=new Squeezy(a);
			Word t=new Word("E", new Point(1,3), 'H');
			b.addWord(t);
			Word t1=new Word("T", new Point(3,4), 'H');
			b.addWord(t1);
			Word t2=new Word("R", new Point(5,8), 'H');
			b.addWord(t2);
			Word t3=new Word("A", new Point(7,14), 'H');
			b.addWord(t3);
			Word t4=new Word("S", new Point(9,12), 'H');
			b.addWord(t4);
			Word t5=new Word("E", new Point(11,3), 'H');
			b.addWord(t5);
			Word t6=new Word("I", new Point(13,1), 'H');
			b.addWord(t6);
			ArrayList<ArrayList> d=c.findWordLengths(c.getLettersFromBoard(b), b);
			for(int i=0;i<d.size();i++){
				for(int k=0;k<d.get(i).size();k++){
					System.out.println(d.get(i).get(k));
				}
				System.out.println();
			}
			
	}
	
}

