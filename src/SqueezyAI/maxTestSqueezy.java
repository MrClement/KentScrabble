
package SqueezyAI;
import core.Board;
import core.LetterBag;
import core.ScrabbleGUI;
import core.Word;
import java.awt.EventQueue;


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
		b.addWord(word);
		w.showBoard(b);

	}
	
}
