package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ScrabbleClient {
	private static String hostname = "localhost";
	private static int port = 8080;

	public static void main(String[] args) {

		ObjectInputStream in;
		ObjectOutputStream out;

		Scanner scan = new Scanner(System.in);

		Socket myClient;
		try {
			myClient = new Socket(hostname, port);
			in = new ObjectInputStream(myClient.getInputStream());
			out = new ObjectOutputStream(myClient.getOutputStream());
			while (scan.hasNext()) {
				System.out.println(scan.nextLine());
				System.out.println(in.readLine());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
}
}
