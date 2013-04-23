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
		System.out.println(d.isWord("vowoof"));
		ArrayList<Character> temp = new ArrayList<>();
		temp.add('a');
		temp.add('a');
		temp.add('r');
		System.out.println(temp);
		System.out.println(d.allStringsWithLetters(temp));

		Letter l1 = new Letter('k');
		Letter l2 = new Letter('k');
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(l1.hashCode());
		System.out.println(l2.hashCode());
		System.out.println(l1.equals(l2));
		ArrayList<Letter> test = new ArrayList<Letter>();
		test.add(l1);
		test.add(l2);
		System.out.println(test);
		test.remove(new Letter('k'));
		System.out.println(test);
		test.remove(new Letter('k'));
		System.out.println(test);
	}
}
