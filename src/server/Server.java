package server;

import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import core.Board;
import core.Word;

public class Server {
	private static int port = 8080;

	private static Board b;

	public synchronized static void main(String[] args) {

		ServerSocket myServerSocket;

		Socket client;
		Socket client2;
		ObjectInputStream in;
		ObjectOutputStream out;
		ObjectInputStream in2;
		ObjectOutputStream out2;

		Scanner scan = new Scanner(System.in);

		try {

			// setup clients
			System.out.println("Server started");
			myServerSocket = new ServerSocket(port);
			client = myServerSocket.accept();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
