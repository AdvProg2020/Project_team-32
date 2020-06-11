package Model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String userName;
    private Good good;
    private String commentString;
    private  String commentStatus;
    //private int commenterBoughtGood;
    private String title;

    /*public Comment(String userName, Good good, String commentString, int commenterBoughtGood) {
        this.userName = userName;
        this.good = good;
        this.commentString = commentString;
        this.commenterBoughtGood = commenterBoughtGood;
    }*/

    public Comment(String userName, Good good, String commentString, String commentStatus, String title) {
        this.userName = userName;
        this.good = good;
        this.commentString = commentString;
        this.commentStatus = commentStatus;
        this.title = title;
    }
}
