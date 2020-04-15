package Model;

public class Comment {
    private String userName;
    private Good good;
    private String commentString;
    private  String commentStatus;
    private int commenterBoughtGood;

    public Comment(String name, Good good, String commentString, int commenterBoughtGood) {
        this.userName = name;
        this.good = good;
        this.commentString = commentString;
        this.commenterBoughtGood = commenterBoughtGood;
    }
}
