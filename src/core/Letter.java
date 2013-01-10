package core;

/**
 * @author Alex Patel
 *
 *Letter - holds the character and point values representative of a scrabble letter tile. 
 *includes blanks, which can have a letter value but are worth no points
 */

public class Letter {
	private char letter;
	private int val;
	private boolean isBlank;
	
	public static int val1=0; //Blank tile
	public static int val2=1; //A,E,I,L,N,O,R,S,T,U
	public static int val3=2; //D and G
	public static int val4=3; //B,C,M,P
	public static int val5=4; //F,H,V,W,Y
	public static int val6=5; //K
	public static int val7=8; //J and X
	public static int val8=10; //Q and Z

	public Letter(char letter){
		this.letter=letter;
		setVal();
		if (letter=='0')isBlank=true;
		else isBlank=false;
	}
	
	
	/**
	 * @return the letter's character value
	 */
	public char getLetter(){
		return letter;
	}
	
	/**
	 * @return the letter's point value
	 */
	public int getVal(){
		return val;
	}
	
	
	/**
	 * internal method to set the value of the letter when it is created. 
	 * 
	 */
	private void setVal(){
		if(letter=='0'){ //char '0' == blank tile
			val=val1;
		}
		else if(letter=='A'||letter=='E'||letter=='I'||letter=='L'||letter=='N'||letter=='O'||letter=='R'||letter=='S'||letter=='T'||letter=='U'){
			val=val2;
		}
		else if(letter=='D'||letter=='G'){
			val=val3;
		}
		else if(letter=='B'||letter=='C'||letter=='M'||letter=='P'){
			val=val4;
		}
		else if(letter=='F'||letter=='H'||letter=='V'||letter=='W'||letter=='Y'){
			val=val5;
		}
		else if(letter=='K'){
			val=val6;
		}
		else if(letter=='J'||letter=='X'){
			val=val7;
		}
		else if(letter=='Q'||letter=='Z'){
			val=val8;
		}
	}

	
	/**
	 * Set the character value of the letter
	 * 
	 * @param character, '0' = blank
	 */
	public void setChar(char a){
		if(isBlank==true){
			letter=a;
			val=0;
		}
		else{
			letter=a;
			setVal();
		}
	}
}
