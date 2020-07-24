package bank;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Socket socket = new Socket("localhost", 4200);
			Scanner userScanner = new Scanner(System.in);
			Scanner serverScanner = new Scanner(socket.getInputStream());
			Formatter formatter = new Formatter(socket.getOutputStream());
			
			while(true) {
				formatter.format(userScanner.nextLine() + "\n");
				formatter.flush();
				System.out.println(serverScanner.nextLine());
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
