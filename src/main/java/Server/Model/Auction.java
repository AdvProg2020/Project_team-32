package Server.Model;

import Server.Model.Chat.ChatBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Auction implements Serializable {
    private static ArrayList<Auction> auctions = new ArrayList<>();
    private  String ID;
    private Seller seller;
    private Good good;
    private Date expire;
    private String buyerUserName;
    private  int price;
    private ChatBox chatBox;
    public Auction(Seller seller, Good good,Date expire  ) {
        chatBox = new ChatBox();
        this.seller = seller;
        this.good = good;
        this.expire =expire;
        price=0;
        this.ID =chatBox.getChatId();
        auctions.add(this);

    }

    public ChatBox getChatBox() {
        return chatBox;
    }

    public String getID() {
        return ID;
    }

    public Date getExpire() {
        return expire;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
}
