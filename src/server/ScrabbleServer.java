package server;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import core.Board;
import core.Word;

public class ScrabbleServer {
	public static void main(String[] args) throws IOException {

		int port = 8080;
		int maxPlayers = 4;
		int players = 0;
		Board b;

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
			// client2 = myServerSocket.accept();
			// System.out.println("Got client 2");
			b = new Board();
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
			// in2 = new ObjectInputStream(client2.getInputStream());

			// out2 = new ObjectOutputStream(client2.getOutputStream());

			// play game
			System.out.println("here");
			b.addWord(new Word("HI", new Point(7, 7), 'h'));
			out.writeObject(b);
			b.addWord((Word) in.readObject());
		} catch (IOException e) {
			System.out.println("Listen failed: " + port);
			System.exit(-1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (players < maxPlayers) {
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				System.out.println("Accept failed:" + port);
				System.exit(-1);
			}
		}
	}
}
