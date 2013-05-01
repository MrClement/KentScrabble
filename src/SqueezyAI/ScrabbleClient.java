package SqueezyAI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import core.Board;
import core.DemoAI;
import core.LetterBag;
import core.Player;
import core.Word;

public class ScrabbleClient {
	private static String hostname = "10.80.4.159";
	private static int port = 8080;

	public synchronized static void main(String[] args) {

		ObjectOutputStream out;
		ObjectInputStream in;
		Board b;
		LetterBag lb;
		Player me;
		Word moveToMake;
		Word temp;

		Socket myClient;
		try {
			myClient = new Socket(hostname, port);
			in = new ObjectInputStream(myClient.getInputStream());
			out = new ObjectOutputStream(myClient.getOutputStream());
			b = (Board) in.readObject();
			lb = (LetterBag) in.readObject();
			// replace this
			me = new Squeezy(lb);
			temp = me.makeMove(b);
			moveToMake = new Word(temp.getWordInLetters(), temp.getLocation(), temp.getDirection());
			out.writeObject(moveToMake);
			out.writeObject(lb);

			while (true) {

				b = new Board((Board) in.readObject());
				lb = (LetterBag) in.readObject();
				me.updateLetterBag(lb);
				temp = me.makeMove(b);
				moveToMake = new Word(temp.getWordInLetters(), temp.getLocation(), temp.getDirection());
				out.writeObject(moveToMake);
				out.writeObject(lb);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
