
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScrabbleGUI {

	private JFrame frame;
	private String[][] pieces;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board b = new Board();
					IntegratedGUI window = new IntegratedGUI(b);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public IntegratedGUI(Board b) {
		pieces = new String[8][8];
		frame = new JFrame();
		frame.setSize(425, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		initializeBoard(b);
	}

	private void readBoard(Board board) {
		Piece[][] temp = board.getBoardArray();
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				pieces[i][j] = temp[i][j].toString();
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

	private void initializeBoard(Board b) {
		// b.randomize();
		readBoard(b);

		int i = 8;
		int j = 8;

		GridLayout steven = new GridLayout(i, j);
		steven.setHgap(2);
		steven.setVgap(2);

		JPanel[][] panelHolder = new JPanel[i][j];
		frame.setLayout(steven);

		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				panelHolder[m][n] = new JPanel();
				panelHolder[m][n].setSize(60, 60);
				if ((n % 2 + m % 2) % 2 == 0)
					panelHolder[m][n].setBackground(Color.WHITE);
				else
					panelHolder[m][n].setBackground(Color.GRAY);
				frame.add(panelHolder[m][n]);
			}
		}

		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {

				String pieceType = pieces[n][m];
				String tempResourceName = "" + Character.toLowerCase(pieceType.charAt(0));
				switch (pieceType.charAt(1)) {
					case 'X':
						tempResourceName = "Blank";
						break;
					case 'B':
						tempResourceName = tempResourceName.concat("Bishop");
						break;
					case 'R':
						tempResourceName = tempResourceName.concat("Rook");
						break;
					case 'P':
						tempResourceName = tempResourceName.concat("Pawn");
						break;
					case 'Q':
						tempResourceName = tempResourceName.concat("Queen");
						break;
					case 'K':
						tempResourceName = tempResourceName.concat("King");
						break;
					case 'N':
						tempResourceName = tempResourceName.concat("Knight");
						break;
					default:
						System.out.println("Reached default case in piece string array of BoardGUIDevelopment");
						System.out.println("Exiting");
						System.exit(1);
						break;
				}

				tempResourceName = tempResourceName.concat(".png");

				ImageIcon icon = createImageIcon(tempResourceName, "Derpy Spot");

				JLabel label = new JLabel();
				label.setIcon(icon);
				panelHolder[m][n].add(label);
			}
		}

		frame.getContentPane().setLayout(steven);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void redraw(Board b) {
		frame.getContentPane().removeAll();
		initializeBoard(b);
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
	}

}
