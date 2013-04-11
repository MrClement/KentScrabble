
package SqueezyAI;
import core.Board;
import core.LetterBag;
import core.ScrabbleGUI;
import core.Word;
import java.awt.EventQueue;
import java.awt.Point;


public class maxTestSqueezy {

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
	
	private static void cont(ScrabbleGUI w) {
		
		Board b = new Board();
		
		Squeezy s = new Squeezy(new LetterBag());
		Word word = s.makeMove(b);
		Word t=new Word("E", new Point(7,7), 'H');
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

	}
	
}
