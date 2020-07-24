package Server;

import View.Client;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public  class BankServer {

    public final static int BANK_SERVER_PORT_NUMBER = 4299;

    private  Scanner bankScanner;
    private  Formatter bankFormatter;
    private String serverToken = null;
    private Socket bankSocket = null;
    private int accountID=-1 ;

    public BankServer() {
        try {
            bankSocket = new Socket("localHost", BANK_SERVER_PORT_NUMBER);
            bankScanner = new Scanner(bankSocket.getInputStream());
            bankFormatter = new Formatter(bankSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageToBank(String message){
        bankFormatter.format(message+"\n");
        bankFormatter.flush();
    }
    public  String getMessageFromBank(){
        return bankScanner.nextLine();
    }

    public String getServerToken() {
        return serverToken;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }
}

