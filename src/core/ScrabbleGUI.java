
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScrabbleGUI {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrabbleGUI window = new ScrabbleGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScrabbleGUI() {
		frame = new JFrame();
		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		showBoard();
	}

	
	//Colors
	
	
	private void showBoard() {
		int i = 16;
		int j = 16;

		GridLayout layout = new GridLayout(i, j);
		layout.setHgap(2);
		layout.setVgap(2);

		JPanel[][] panelHolder = new JPanel[i][j];
		frame.setLayout(layout);
		
		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				panelHolder[m][n] = new JPanel();
				panelHolder[m][n].setSize(60, 60);
				/*if ((n % 2 + m % 2) % 2 == 0)
					panelHolder[m][n].setBackground(Color.WHITE);
				else
					panelHolder[m][n].setBackground(Color.GRAY);
					*/
				
				frame.add(panelHolder[m][n]);
			}
		}
		
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL url = getClass().getResource(path);
		if (url != null) {
			return new ImageIcon(url, description);
		} else {
			System.err.println("DERPYFILE COULDN'T BE LOCATED: " + path);
			return null;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */


	public JFrame getFrame() {
		return frame;
	}


}
