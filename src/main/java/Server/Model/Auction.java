package Server.Model;

import java.util.ArrayList;

public class Auction {
    private static ArrayList<Auction> auctions = new ArrayList<>();
    private  String ID;
    private Seller seller;
    private Good good;
    private int port;

    public Auction(String ID,Seller seller, Good good, int port) {
        this.ID =ID;
        this.seller = seller;
        this.good = good;
        this.port = port;
        auctions.add(this);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ServerSocket serverSocket = new ServerSocket(port);
//                    while (true){
//                        Socket socket = serverSocket.accept();
//                        new Thread()
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public String getID() {
        return ID;
    }

    public static ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public Seller getSeller() {
        return seller;
    }

    public Good getGood() {
        return good;
    }

    public int getPort() {
        return port;
    }
}
