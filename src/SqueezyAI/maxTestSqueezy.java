
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
		b.addWord(new Word("vagina", new Point(7,7),'H'));
		b.addWord(new Word("ass", new Point(8,7),'V'));

		w.showBoard(b);

	}
	
}
