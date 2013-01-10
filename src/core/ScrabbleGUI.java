package core;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		frame.setSize(675,675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Board test = new Board();
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		test.arr[(int)(Math.random()*13)][(int)(Math.random()*13)] =  new Space(0, new Letter((char) ('A' + (int)(Math.random()*24))));
		
		//showBoard(test);
		showBoard(null);
	}

	
	//Colors
	
	
	private void showBoard(Board b) {
		
		if(b == null)b = new Board();
		
		int i = 15;
		int j = 15;

		GridLayout layout = new GridLayout(i, j);
		layout.setHgap(2);
		layout.setVgap(2);

		JPanel[][] panelHolder = new JPanel[i][j];
		frame.setLayout(layout);
		
		for (int m = 0; m < i; m++) {
			for (int n = 0; n < j; n++) {
				panelHolder[m][n] = new JPanel(new GridLayout()); //GridLayout here simply a way to make there be no padding for tiles. Strange workaround, but it works
				
				// 0 is normal space, 1 is double letter, 2 is triple letter,
				 // 3 is double word, 4 is triple word
				
				Space sp = b.arr[m][n];

				switch(sp.type){
					case 0:
						panelHolder[m][n].setBackground(Color.WHITE);
						break;
					case 1:
						panelHolder[m][n].setBackground(Color.BLUE);
						break;
					case 2:
						panelHolder[m][n].setBackground(Color.GREEN);
						break;
					case 3:
						panelHolder[m][n].setBackground(Color.RED);
						break;
					case 4:
						panelHolder[m][n].setBackground(Color.ORANGE);
						break;
					default:
						panelHolder[m][n].setBackground(Color.BLACK);
						break;
				}
				
				//Evaluate space to see if it has an associated letter, if so, dump the image into the panel
				if(sp.getLetter().getLetter() != '0') {
					String resourceName = "";
					//There's an actual letter here, let's stick the image in
					char uppercase = Character.toUpperCase(sp.getLetter().getLetter());
					if(uppercase >= 'A' && uppercase <= 'Z')resourceName = uppercase + resourceName;
					resourceName = resourceName + ".png";
					
					ImageIcon icon = createImageIcon(resourceName);
					JLabel label = new JLabel();
					label.setIcon(icon);
					
					panelHolder[m][n].add(label);
				}
				
				frame.add(panelHolder[m][n]);
			}
		}
		
	}

	protected ImageIcon createImageIcon(String path) {
		java.net.URL url = getClass().getResource(path);
		if (url != null) {
			return new ImageIcon(url, "");
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
