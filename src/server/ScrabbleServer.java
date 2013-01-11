package server;

import java.net.*;
import java.io.*;

public class ScrabbleServer {
	public static void main(String[]args) throws IOException{
		int port=8080;
		int maxPlayers=4;
		int players=0;
		ServerSocket serverSocket=null;
		try{
			 serverSocket=new ServerSocket(port);
		}
		catch(IOException e){
			System.out.println("Listen failed: "+port);
			System.exit(-1);
		}
		
		while(players<maxPlayers){
		Socket clientSocket=null;
		try{
			clientSocket=serverSocket.accept();
		}
		catch (IOException e){
			System.out.println("Accept failed:"+port);
			System.exit(-1);
		}
		}
	}
}
