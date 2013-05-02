package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import core.Board;
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
		ObjectInputStream in;
		ObjectOutputStream out;
		ObjectInputStream in2;
		ObjectOutputStream out2;

		try {
			// setup clients
			System.out.println("Server started");
			serverSocket = new ServerSocket(port);
			client = serverSocket.accept();
			System.out.println("Got client 1");
			client2 = serverSocket.accept();
			System.out.println("Got client 2");
			b = new Board();
			lb = new LetterBag();
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
			out2 = new ObjectOutputStream(client2.getOutputStream());
			in2 = new ObjectInputStream(client2.getInputStream());

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
			}
		} catch (IOException e) {
			System.out.println("Listen failed: " + port);
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
