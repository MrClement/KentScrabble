package core;

import java.util.ArrayList;

public class TestDriver {

	/**
	 * Just a generic driver to test classes as they are worked on and finished
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		System.out.println(d.isWord("aa"));
		ArrayList<Character> temp = new ArrayList<>();
		temp.add('q');
		temp.add('z');
		System.out.println(temp);
		System.out.println(d.allStringsWithLetters(temp));

	}
}
