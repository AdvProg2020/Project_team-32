package Model;

public class Salesman extends Account {
    private String factoryName;

    public Salesman(String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit, String factoryName) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
        this.factoryName = factoryName;
    }
}
