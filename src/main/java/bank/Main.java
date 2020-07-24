package bank;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		System.out.println(new Date());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
		String s = simpleDateFormat.format(new Date());
		System.out.println(s);
		System.out.println(simpleDateFormat.parse(s));

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
