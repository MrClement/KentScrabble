package core;

public class Space {
	Letter letter;
	int type;// 0 is normal space, 1 is double letter, 2 is triple letter,
				// 3 is double word, 4 is triple word

	public Space(int type) {
		Letter blank=new Letter ('#');
		this.letter=blank;
		this.type=type;
	}

	public Space(int type, Letter letter) {
		this.letter = letter;
		this.type = type;
	}

	public Letter getLetter() {
		return letter;
	}

	public String getTypeString() {
		if (type == 0) {
			return "Normal";
		}
		if (type == 1) {
			return "Double Letter";
		}
		if (type == 2) {
			return "Triple Letter";
		}
		if (type == 3) {
			return "Double Word";
		}
		if (type == 4) {
			return "Triple Word";
		}
		return null;
	}

	public int getTypeInt() {
		return type;
	}

	public void setLetter(Letter l) {
		this.letter = l;
	}
}
