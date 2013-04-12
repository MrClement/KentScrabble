package SqueezyAI;
import core.*;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

public class S2 extends Player{
	Dictionary dic;
	int turn;
	
	public S2(LetterBag a){
		super(a);
		dic=new Dictionary();
		turn=0;
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
	
	private int getFarL(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
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
	
	private int getFarR(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
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
	
	private int getFarU(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
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
	
	private int getFarD(ArrayList<ArrayList> lettersFromBoard, int letterIndex, Board b){
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
			for(int k=0;k<lettersFromBoard.get(i).size();k++){
				a.get(i).add(lettersFromBoard.get(i).get(k));
			}
			int farL, farR, farU, farD;
			farL=getFarL(a, i, b);
			farR=getFarR(a, i, b);
			farU=getFarU(a, i, b);
			farD=getFarD(a, i, b);
			
			int x=(int)((Point)a.get(i).get(1)).getX();
			int y=(int)((Point)a.get(i).get(1)).getY();
			
			ArrayList<Word> temp=new ArrayList<Word>();
			for(int o=farL;o<x;o++){
				for(int p=x;p<farR;p++){
					String s="";
					for(int u=o;u<p;u++){
							if(u==x)s+=((Letter)a.get(i).get(0)).getCharacter();
							else s+='1';
					}				
					if(s.length()>1&&s.length()<9&&s.indexOf(((Letter)a.get(i).get(0)).getCharacter())!=-1){
						temp.add(new Word(s, new Point(farL, y ), 'H'));
					}
				}
			}
			for(int o=farU;o<y;o++){
				for(int p=y;p<farD;p++){
					String s="";
					for(int u=o;u<p;u++){
							if(u==x)s+=((Letter)a.get(i).get(0)).getCharacter();
							else s+='1';
					}				
					if(s.length()>1&&s.length()<9&&s.indexOf(((Letter)a.get(i).get(0)).getCharacter())!=-1){
						temp.add(new Word(s, new Point(x, farU ), 'V'));
					}
				}
			}
			
			for(int e=0;e<temp.size();e++){
				a.get(i).add(temp.get(e));
			}

		}
		
		
	
	
		return a;
	}

	private ArrayList<ArrayList> fillAllWords(ArrayList<ArrayList> lettersFromBoard){
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
	}
	
	private ArrayList<String> perm(ArrayList<String> a, String b, String c){
		ArrayList<String>d=a;
	    if(c.length() <= 1)
	    		d.add(b+c);
	    else{
	        for(int i=0; i <c.length();i++){
		          perm(d, b+c.charAt(i), c.substring(0, i) + c.substring(i+1, c.length()));
		        }           
	    }
	    return d;
		//source - http://stackoverflow.com/questions/4950085/permutations-of-a-string

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
	
	private ArrayList<Word> getPossWords(Word blank, ArrayList<Letter> a, boolean first){
		if(first==true){	
		String temp="";
		for(int i=0;i<a.size();i++){
			temp+=a.get(i).getCharacter();
		}
		ArrayList<String> b=perm(new ArrayList<String>(), "", temp);
		
		ArrayList<Word> f=new ArrayList<Word>();
		Dictionary d=new Dictionary();
		for(int i=0;i<b.size();i++){
				if(d.isWord(b.get(i))!=-1){
					f.add(new Word(b.get(i)));
				}
		}
		
		return f;
		}
		else return null;
	}
	
	public static void main(String args[]){
		
		LetterBag a=new LetterBag();
		Board b=new Board();
		S2 c=new S2(a);
		/*Word t=new Word("E", new Point(7,7), 'H');
		b.addWord(t);
		Word t1=new Word("T", new Point(3,4), 'H');
		b.addWord(t1);
		Word t2=new Word("R", new Point(5,8), 'H');
		b.addWord(t2);
		Word t4=new Word("S", new Point(9,12), 'H');
		b.addWord(t4);
		Word t5=new Word("E", new Point(11,3), 'H');
		b.addWord(t5);
		Word t6=new Word("I", new Point(13,1), 'H');
		b.addWord(t6);*/
				
		ArrayList<Word> f=c.getPossWords(new Word("A1"), c.getLetters());
		for(int i=0;i<f.size();i++){
				System.out.println(f.get(i).getWord());
		}
		
	}
	
	public Word makeMove(Board b) {
		if (turn==0&&b.getArr()[7][7].getLetter().getCharacter()=='0'){
			ArrayList<ArrayList> temp=new ArrayList<ArrayList>();
			temp.add(new ArrayList());
			temp.get(0).add(new Letter('1'));
			temp.get(0).add(new Point(7,7));
		}
		
		turn++;
		return null;
	}

}
