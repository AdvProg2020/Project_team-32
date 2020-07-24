package Server.Model;

public class Boss extends Person {
    private static  int leastMoney =100;
    private static  int wage =5;

    public Boss(String userName, String passWord) {
        super(userName, passWord);
    }


    private void manageRequests() {

    }

    private void manageCategory() {

    }

    private void createDiscount() {

    }

    private void editDiscount(Discount discount) {

    }

    public static int getLeastMoney() {
        return leastMoney;
    }

    public static int getWage() {
        return wage;
    }
}