package Server.Model;

import java.io.Serializable;

public class Comment implements Serializable {
    private String userName;
    private Good good;
    private String commentString;
    private  String commentStatus;
    private boolean commenterBoughtGood;
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

    public void setCommenterBoughtGood(boolean commenterBoughtGood) {
        this.commenterBoughtGood = commenterBoughtGood;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentString() {
        return commentString;
    }

    public void setCommentString(String commentString) {
        this.commentString = commentString;
    }

    public boolean getCommenterBoughtGood() {
        return commenterBoughtGood;
    }
}
