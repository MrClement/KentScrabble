package SqueezyAI;
import core.*;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Squeezy extends Player{
	Dictionary dic;
	
	public Squeezy(LetterBag a){
		super(a);
		dic=new Dictionary();
	}

	public void setLetters(ArrayList<Letter> a){
		letters=a;
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
					for(int u=o;u<=p;u++){
							if(u==x)s+=((Letter)a.get(i).get(0)).getCharacter();
							else s+='1';
					}	
					boolean parallel=false;
					for(int c=o;c<o+s.length();c++){
						if(c!=x){
							if(y+1<15&&b.getArr()[c][y+1].getLetter().getCharacter()!='0')parallel=true;
							if(y-1>-1&&b.getArr()[c][y-1].getLetter().getCharacter()!='0')parallel=true;
						}
					}
					if(o+1+s.length()<15&&b.getArr()[o+1+s.length()][y].getLetter().getCharacter()!='0')parallel=true;
					if(o-1>-1&&b.getArr()[o-1][y].getLetter().getCharacter()!='0')parallel=true;
				
					if(parallel==false&&s.length()>1&&s.length()<9&&s.indexOf(((Letter)a.get(i).get(0)).getCharacter())!=-1){
						temp.add(new Word(s, new Point(o, y), 'H'));
					}
				}
			}
			for(int o=farU;o<y;o++){
				for(int p=y;p<farD;p++){
					String s="";
					for(int u=o;u<=p;u++){
							if(u==y)s+=((Letter)a.get(i).get(0)).getCharacter();
							else s+='1';
					}	
					boolean parallel=false;
					for(int c=o;c<o+s.length();c++){
						if(c!=y){
							if(x+1<15&&b.getArr()[x+1][c].getLetter().getCharacter()!='0')parallel=true;
							if(x-1>-1&&b.getArr()[x-1][c].getLetter().getCharacter()!='0')parallel=true;
						}
					}
					if(o+1+s.length()<15&&b.getArr()[x][o+1+s.length()].getLetter().getCharacter()!='0')parallel=true;
					if(o-1>-1&&b.getArr()[x][o-1].getLetter().getCharacter()!='0')parallel=true;
					
					if(parallel==false&&s.length()>1&&s.length()<8&&s.indexOf(((Letter)a.get(i).get(0)).getCharacter())!=-1){
						temp.add(new Word(s, new Point(x, o), 'V'));
					}
				}
			}
			
			for(int e=0;e<temp.size();e++){
				a.get(i).add(temp.get(e));
			}

		}
		
	
		/*for(int i=0;i<a.size();i++){
			for(int k=2;k<a.get(i).size();k++){
				System.out.println(((Word)a.get(i).get(k))+" - "+((Word)a.get(i).get(k)).getLocation()+" - "+((Word)a.get(i).get(k)).getDirection());
			}
		}*/
		return a;
	}

	public int getWordScore(Word w, Board b){
		char dir=w.getDirection();
		Point st=w.getLocation();
		int length=w.getWord().length();
		int score=0;
		if(dir=='H'){
			for(int i=(int)st.getX();i<(int)st.getX()+length;i++){
				if(b.getArr()[i][(int)st.getY()].getTypeInt()==0){
					score+=w.getWordInLetters()[i-(int)st.getX()].getVal();
				}
				else if(b.getArr()[i][(int)st.getY()].getTypeInt()==1){
					score+=w.getWordInLetters()[i-(int)st.getX()].getVal()*2;
				}
				else if(b.getArr()[i][(int)st.getY()].getTypeInt()==2){
					score+=w.getWordInLetters()[i-(int)st.getX()].getVal()*3;
				}
			}
			boolean dWord=false;
			boolean tWord=false;
			for(int i=(int)st.getX();i<(int)st.getX()+length;i++){
				if(b.getArr()[i][(int)st.getY()].getTypeInt()==3)dWord=true;
				if(b.getArr()[i][(int)st.getY()].getTypeInt()==4)tWord=true;
				
			}
			if(dWord==true)score*=2;
			if(tWord==true)score*=3;
			
			
		}
		else if(dir=='V'){
			for(int i=(int)st.getY();i<(int)st.getY()+length;i++){
				if(b.getArr()[(int)st.getX()][i].getTypeInt()==0){
					score+=w.getWordInLetters()[i-(int)st.getY()].getVal();
				}
				else if(b.getArr()[(int)st.getX()][i].getTypeInt()==1){
					score+=w.getWordInLetters()[i-(int)st.getY()].getVal()*2;
				}
				else if(b.getArr()[(int)st.getX()][i].getTypeInt()==2){
					score+=w.getWordInLetters()[i-(int)st.getY()].getVal()*3;
				}
			}
			boolean dWord=false;
			boolean tWord=false;
			for(int i=(int)st.getY();i<(int)st.getY()+length;i++){
				if(b.getArr()[(int)st.getX()][i].getTypeInt()==3)dWord=true;
				if(b.getArr()[(int)st.getX()][i].getTypeInt()==4)tWord=true;
			}
			if(dWord==true)score*=2;
			if(tWord==true)score*=3;
		}
		
		return score;
	}

	private ArrayList<Word> getPossWords(Word blank){
		boolean blankcheck=false;
		for(int i=0;i<letters.size();i++){
			if(letters.get(i).getCharacter()=='1')blankcheck=true;
		}
		
		if(blankcheck==false){
			ArrayList<String> b=new ArrayList<String>();
			
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
			
			ArrayList<String>tem=perm(new ArrayList<String>(),"",temp);
			for(String s:tem){
				b.add(s);
			}
		
			ArrayList<Word> f=new ArrayList<Word>();
			for(int i=0;i<b.size();i++){
				if(dic.isWord(b.get(i))!=-1&&b.get(i).length()==blank.getWord().length()&&b.get(i).charAt(letIndex)==blank.getWord().charAt(letIndex)){
					f.add(new Word(b.get(i), blank.getLocation(), blank.getDirection()));
				}
			}

			return f;
		}
		else{
			ArrayList<Word> f=new ArrayList<Word>();
			for(int i='A';i<'A'+26;i++){

				ArrayList<String> b=new ArrayList<String>();
				
				int letIndex=0;
				while(blank.getWord().charAt(letIndex)=='1'){
					letIndex++;
				}
				
				ArrayList<Letter> a=new ArrayList<Letter>();
				for(int k=0;k<letters.size();k++){
					if(letters.get(k).getCharacter()!='1'){
						a.add(letters.get(k));
					}
					else{
						a.add(new Letter((char)i));
					}
				}
				a.add(new Letter(blank.getWord().charAt(letIndex)));
				
				String temp="";
				for(int k=0;k<a.size();k++){
					temp+=a.get(k).getCharacter()+"";
				}
				
				ArrayList<String>tem=perm(new ArrayList<String>(),"",temp);
				for(String s:tem){
					b.add(s);
				}
			
				for(int k=0;k<b.size();k++){
					if(dic.isWord(b.get(k))!=-1&&b.get(k).length()==blank.getWord().length()&&b.get(k).charAt(letIndex)==blank.getWord().charAt(letIndex)){
						f.add(new Word(b.get(k), blank.getLocation(), blank.getDirection()));
					}
				}
			}
			return f;
		}
	}

	private ArrayList<Word> getPossWordsFirst(Word blank){
		boolean blankcheck=false;
		for(int i=0;i<letters.size();i++){
			if(letters.get(i).getCharacter()=='1')blankcheck=true;
		}
		
		if(blankcheck==false){
		ArrayList<Letter> a=new ArrayList<Letter>();
		for(int i=0;i<this.getLetters().size();i++){
			a.add(this.getLetters().get(i));
		}
		
		String temp="";
		for(int i=0;i<a.size();i++){
			temp+=a.get(i).getCharacter()+"";
		}
		
		ArrayList<String> b=perm(new ArrayList<String>(),"",temp);
	
		ArrayList<Word> f=new ArrayList<Word>();
		for(int i=0;i<b.size();i++){
			if(b.get(i).length()==blank.getWord().length()){
				if(dic.isWord(b.get(i))!=-1)f.add(new Word(b.get(i), blank.getLocation(), blank.getDirection()));
			}
		}
		
		return f;
		}
		else{
			ArrayList<Word> f=new ArrayList<Word>();
			for(int i='A';i<'A'+26;i++){
				ArrayList<Letter> a=new ArrayList<Letter>();
					for(int k=0;k<this.getLetters().size();k++){
						if(letters.get(k).getCharacter()!='1')
						{a.add(this.getLetters().get(k));}
						else{
							a.add(new Letter((char)i));
						}
					}	
				String temp="";
				for(int k=0;k<a.size();k++){
					temp+=a.get(k).getCharacter()+"";
				}
				ArrayList<String>b=perm(new ArrayList<String>(),"",temp);
				for(int k=0;k<b.size();k++){
					if(dic.isWord(b.get(k))!=-1&&b.get(k).length()==blank.getWord().length()){
						f.add(new Word(b.get(k), blank.getLocation(), blank.getDirection()));
					}
				}
			}
			return f;
		}
	}

	public ArrayList<String> perm(ArrayList<String> f, String prefix, String str) {
	    ArrayList<String> a=f;
	    a.add(prefix);
	       for (int i = 0; i < str.length(); i++) {
	           perm(a,prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, str.length()));
	        }
	    return a;
	}

	public ArrayList<ArrayList> getBestWords(ArrayList<ArrayList> a, Board b){
		ArrayList<ArrayList> c=new ArrayList<ArrayList>();
		for(int i=0;i<a.size();i++){
			c.add(new ArrayList());
			for(int k=0;k<a.get(i).size();k++){
				c.get(i).add(a.get(i).get(k));
			}
		}
		
		for(ArrayList d:c){
			for(int i=2;i<d.size();i++){
				Word w=(Word)d.get(i);
				ArrayList<Word> e=getPossWords(w);
				if(e.size()==0){
					d.remove(i);
					i--;
				}
				else{
				int maxScore=0;
				int index=0;
				for(int k=0;k<e.size();k++){
					if(getWordScore(e.get(k), b)>maxScore){
						maxScore=getWordScore(e.get(k),b);
						index=k;
					}
				}
				d.set(i, e.get(index));
				}
			}
		}

		return c;
	}
	
	public ArrayList<ArrayList> getBestWordsFirst(ArrayList<ArrayList> a, Board b){
		ArrayList<ArrayList> c=new ArrayList<ArrayList>();
		for(int i=0;i<a.size();i++){
			c.add(new ArrayList());
			for(int k=0;k<a.get(i).size();k++){
				c.get(i).add(a.get(i).get(k));
			}
		}
		
		for(ArrayList d:c){
			for(int i=2;i<d.size();i++){
				Word w=(Word)d.get(i);
				ArrayList<Word> e=getPossWordsFirst(w);
				if(e.size()==0){
					d.remove(i);
					i--;
				}
				else{
				int maxScore=0;
				int index=0;
				for(int k=0;k<e.size();k++){
					if(getWordScore(e.get(k), b)>maxScore){
						maxScore=getWordScore(e.get(k),b);
						index=k;
					}
				}
				d.set(i, e.get(index));
				}
			}
		}
		return c;
	}

	public Word bestWord(ArrayList<ArrayList> a, Board b){
		int maxScore=0;
		int lIndex=0;
		int wIndex=0;
		for(int i=0;i<a.size();i++){
			for(int k=2;k<a.get(i).size();k++){
				if(getWordScore((Word)a.get(i).get(k), b)>maxScore){
					maxScore=getWordScore((Word)a.get(i).get(k),b);
					lIndex=i;
					wIndex=k;
				}
			}
		}
		
		if(wIndex!=0){
			Word w=(Word)a.get(lIndex).get(wIndex);
			return w;
		}
		else{
			String s="";
			for(int i=0;i<7;i++){
				s+=letters.get(i);
			}
			replaceLetters(new Word(s));
			System.out.println("REPLACED ALL LETTERS");
			Word w=bestWord(getBestWords(findWordLengths(getLettersFromBoard(b),b),b),b);
			return w;
		}
	}
	
	public void replaceLetters(Word w){
		ArrayList<Letter> a=new ArrayList<Letter>();
		for(int i=0;i<w.getWordInLetters().length;i++){
			a.add(w.getWordInLetters()[i]);
		}
		
		for(int i=0;i<a.size();i++){
			for(int k=0;k<letters.size();k++){
				if(letters.get(k).getCharacter()==a.get(i).getCharacter()){
					letters.remove(k);
					break;
				}
			}
		}
		draw(w.getWord().length());
		ArrayList<Letter> d=new ArrayList<Letter>();
		for(int i=0;i<7;i++){
			d.add(letters.get(i));
		}
		letters=d;
		
	}

	public Word makeMove(Board b) {
		for(int i=0;i<letters.size();i++){
			System.out.print(letters.get(i).getCharacter()+", ");
		}
		System.out.println();
		if (getLettersFromBoard(b).size()==0&&b.getArr()[7][7].getLetter().getCharacter()=='0'){
			ArrayList<ArrayList> a=new ArrayList<ArrayList>();
			a.add(new ArrayList());
			a.get(0).add(new Letter('1'));
			a.get(0).add(new Point(7,7));
			ArrayList<ArrayList>c=findWordLengths(a,b);
			ArrayList<ArrayList>d=getBestWordsFirst(c,b);
			Word w=bestWord(d,b);
			w.setLocation(new Point(7,7));
			w.setDirection('V');

			replaceLetters(w);
			System.out.println(w.getWord()+" - ("+w.getLocation().getX()+", "+w.getLocation().getY()+") - "+w.getDirection());
			System.out.println();

			return w;
		}
		else{
			Word w=bestWord(getBestWords(findWordLengths(getLettersFromBoard(b),b),b),b);
			replaceLetters(w);
			System.out.println(w.getWord()+" - ("+w.getLocation().getX()+", "+w.getLocation().getY()+") - "+w.getDirection());
			System.out.println();

			return w;
		}
	}

	public static void main(String[] args) {
	
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleGUI window = new ScrabbleGUI();
					window.getFrame().setVisible(true);
					window.getFrame().setTitle("Scrabble");
					cont(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	private static void cont(ScrabbleGUI w) {
	
		Board b = new Board();
		Squeezy s = new Squeezy(new LetterBag());
		
		for(int k=0;k<15;k++){
			for(int i=0;i<s.getLetters().size();i++)System.out.println(s.getLetters().get(i));
			System.out.println("-----");
			Word word=s.makeMove(b);
			b.addWord(word);

		}
	
		
		w.showBoard(b);

		
		

	}

	
}
