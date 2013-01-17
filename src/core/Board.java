package core;

import java.io.Serializable;

public class Board implements Serializable {

	private static final long serialVersionUID = 7807581621565148545L;
	Space[][] arr;

	public Board() {
		arr = new Space[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				arr[i][j] = new Space(0);
			}
		}
		// all of the triple word spaces
		arr[0][0] = new Space(4);
		arr[7][0] = new Space(4);
		arr[14][0] = new Space(4);
		arr[0][7] = new Space(4);
		arr[14][7] = new Space(4);
		arr[0][14] = new Space(4);
		arr[7][14] = new Space(4);
		arr[14][14] = new Space(4);

		// all of the double word spaces
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (i == j)
					arr[i][j] = new Space(3);
			}
			for (int j = 13; j > 9; j--) {
				if (14 - i == j)
					arr[i][j] = new Space(3);
			}
		}
		for (int i = 13; i > 9; i--) {
			for (int j = 1; j < 5; j++) {
				if (14 - i == j)
					arr[i][j] = new Space(3);
			}
			for (int j = 13; j > 9; j--) {
				if (i == j)
					arr[i][j] = new Space(3);
			}
		}
		// all of the triple letter spaces
		for (int i = 1; i < 14; i += 4) {
			for (int j = 5; j < 10; j += 4) {
				arr[i][j] = new Space(2);
			}
		}
		arr[5][1] = new Space(2);
		arr[9][1] = new Space(2);
		arr[5][13] = new Space(2);
		arr[9][13] = new Space(2);
		// all of the double letter spaces
		arr[3][0] = new Space(1);
		arr[0][3] = new Space(1);
		arr[6][2] = new Space(1);
		arr[2][6] = new Space(1);
		arr[7][3] = new Space(1);
		arr[3][7] = new Space(1);
		arr[8][2] = new Space(1);
		arr[2][8] = new Space(1);
		arr[11][0] = new Space(1);
		arr[0][11] = new Space(1);
		arr[6][6] = new Space(1);
		arr[8][6] = new Space(1);
		arr[6][8] = new Space(1);
		arr[8][8] = new Space(1);
		arr[14][3] = new Space(1);
		arr[3][14] = new Space(1);
		arr[12][6] = new Space(1);
		arr[6][12] = new Space(1);
		arr[11][7] = new Space(1);
		arr[7][11] = new Space(1);
		arr[12][8] = new Space(1);
		arr[8][12] = new Space(1);
		arr[14][11] = new Space(1);
		arr[11][14] = new Space(1);
	}

	public Space[][] getArr() {
		return arr;
	}

	public void addWord(Word w) {
		int x = (int) w.getLocation().getX();
		int y = (int) w.getLocation().getY();
		Letter[] word = w.getWordInLetters();
		for (int i = 0; i < word.length; i++) {
			arr[x][y].setLetter(word[i]);
			if (w.getDirection() == 'H') {
				y++;
			} else {
				x++;
			}
		}
	}

}
