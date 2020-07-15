package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static ServerSocket serverSocket;
    public  static ArrayList<Socket> allConnectedSockets = new ArrayList<>();
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("ServerSocket awaiting connections...");
            while (true) {
                Socket socket = serverSocket.accept();
                allConnectedSockets.add(socket);
                new ThreadForClients(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static class  ThreadForClients extends Thread{
        Socket socket;

        public ThreadForClients(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            // create a DataInputStream so we can read data from it.
            String s = null;
            try {
                System.out.println("Connection from " + socket.getPort() + "!");
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (true){
                    s = inputStream.readUTF();
                    System.out.println(s);
                    //TODO ...
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
