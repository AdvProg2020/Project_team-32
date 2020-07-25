package Server.Model.Chat;

import Server.Model.Customer;

public class ChatWithSupporter extends ChatBox {

    private Customer customer;

    public ChatWithSupporter(Customer customer) {
        super();
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
