package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import core.Board;
import core.Letter;
import core.LetterBag;
import core.ScrabbleGUI;
import core.Word;

public class ScrabbleServer {
	public static void main(String[] args) throws IOException {

		int port = 8080;
		int maxPlayers = 4;
		int players = 0;
		Board b;
		LetterBag lb;

		int playerOneScore = 0;
		int playerTwoScore = 0;
		Word justPlayed;

		ServerSocket serverSocket = null;

		Socket client;
		Socket client2;
		Socket client3;
		Socket client4;
		ObjectInputStream in;
		ObjectOutputStream out;
		ObjectInputStream in2;
		ObjectOutputStream out2;
		ObjectInputStream in3;
		ObjectOutputStream out3;
		ObjectInputStream in4;
		ObjectOutputStream out4;

		try {
			// setup clients
			System.out.println("Server started");
			serverSocket = new ServerSocket(port);
			client = serverSocket.accept();
			System.out.println("Got client 1");
			client2 = serverSocket.accept();
			System.out.println("Got client 2");
			// client3 = serverSocket.accept();
			// System.out.println("Got client 3");
			// client4 = serverSocket.accept();
			// System.out.println("Got client 4");
			b = new Board();
			lb = new LetterBag();
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
			out2 = new ObjectOutputStream(client2.getOutputStream());
			in2 = new ObjectInputStream(client2.getInputStream());
			// out3 = new ObjectOutputStream(client3.getOutputStream());
			// in3 = new ObjectInputStream(client3.getInputStream());
			// out4 = new ObjectOutputStream(client4.getOutputStream());
			// in4 = new ObjectInputStream(client4.getInputStream());

			ScrabbleGUI window = new ScrabbleGUI();
			window.getFrame().setVisible(true);
			window.showBoard(b);
			window.redraw(b);

			// play game
			while (lb.getSize() > 0) {
				// client 1 action
				out.writeObject(b);
				out.writeObject(lb);
				justPlayed = (Word) in.readObject();
				b.addWord(justPlayed);
				b = new Board(b);
				lb = (LetterBag) in.readObject();
				window.redraw(b);
				System.out.println("Player 1 played: " + justPlayed.getWord() + " for " + b.getWordScore(justPlayed)
						+ " points.");
				printLetterBag(lb);
				// client 2 action
				out2.writeObject(b);
				out2.writeObject(lb);
				justPlayed = (Word) in2.readObject();
				b.addWord(justPlayed);
				b = new Board(b);
				lb = (LetterBag) in2.readObject();
				window.redraw(b);
				System.out.println("Player 2 played: " + justPlayed.getWord() + " for " + b.getWordScore(justPlayed)
						+ " points.");
				printLetterBag(lb);
				// client 3 action
				// out3.writeObject(b);
				// out3.writeObject(lb);
				// justPlayed = (Word) in3.readObject();
				// b.addWord(justPlayed);
				// b = new Board(b);
				// lb = (LetterBag) in3.readObject();
				// window.redraw(b);
				// System.out.println("Player 3 played: " + justPlayed.getWord()
				// + " for " + b.getWordScore(justPlayed)
				// + " points.");
				// printLetterBag(lb);
				// // client 4 action
				// out4.writeObject(b);
				// out4.writeObject(lb);
				// justPlayed = (Word) in4.readObject();
				// b.addWord(justPlayed);
				// b = new Board(b);
				// lb = (LetterBag) in4.readObject();
				// window.redraw(b);
				// System.out.println("Player 4 played: " + justPlayed.getWord()
				// + " for " + b.getWordScore(justPlayed)
				// + " points.");
				// printLetterBag(lb);
			}
		} catch (IOException e) {
			System.out.println("Listen failed: " + port);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void printLetterBag(LetterBag lb) {
		ArrayList<Letter> bag = lb.getBag();
		System.out.print("Bag: ");
		for (Letter letter : bag) {
			System.out.print(letter.getCharacter() + ", ");
		}
		System.out.println();
	}
}
