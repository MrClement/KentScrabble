package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import core.Board;

public class ClientConnection implements Runnable {

	Socket client;
	BufferedReader in;
	PrintStream out;
	Board b;
	ObjectInputStream objIn;
	ObjectOutputStream objOut;

	public ClientConnection(Socket client) {
		this.client = client;

	}

	@Override
	public synchronized void run() {
		try {
			objIn = new ObjectInputStream(client.getInputStream());
			out = new PrintStream(client.getOutputStream());
			objOut = new ObjectOutputStream(client.getOutputStream());
			Object next;
			while ((next = objIn.readObject()) != null) {
				b = (Board) next;
				// make move
				out.println(b);

			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
