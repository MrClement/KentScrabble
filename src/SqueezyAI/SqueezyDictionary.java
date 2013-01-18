package SqueezyAI;

import java.util.ArrayList;
import java.util.Scanner;

import core.Dictionary;
import core.Word;

public class SqueezyDictionary {

	private String filename = "dictionary.txt";
	private ArrayList<String> words;

	public SqueezyDictionary(){
		words=new ArrayList<String>();
		Scanner scan = new Scanner(Dictionary.class.getClassLoader().getResourceAsStream(filename));
		while (scan.hasNext()) words.add(scan.nextLine());
		scan.close();
	}
	
	public int isWord(String s){
		s=s.toUpperCase();
		if(words.contains(s)){
			Word w=new Word(s);
			return w.getVal();
		}
		else return -1;
	}
	
	public ArrayList<String> getAllWords(){
		return words;
	}
}
