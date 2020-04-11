package Model;

public class Point {
    private String userName;
    private int pointNumber;
    private Good good;

    public Point(String userName, int pointNumber, Good good) {
        this.userName = userName;
        this.pointNumber = pointNumber;
        this.good = good;
    }
}
