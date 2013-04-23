package core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	private static int port = 8080;

	private static Board b;

	public synchronized static void main(String[] args) {

		ServerSocket myServerSocket;

		Socket client;
		Socket client2;
		ObjectInputStream in;
		PrintStream out;
		ObjectInputStream in2;
		PrintStream out2;

		Scanner scan = new Scanner(System.in);

		try {
			System.out.println("Server started");
			myServerSocket = new ServerSocket(port);
			client = myServerSocket.accept();
			System.out.println("Got client 1");
			// client2 = myServerSocket.accept();
			// System.out.println("Got client 2");
			b = new Board();
			in = new ObjectInputStream(client.getInputStream());
			// in2 = new ObjectInputStream(client2.getInputStream());
			Word w = (Word) in.readObject();
			System.out.println(w.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
