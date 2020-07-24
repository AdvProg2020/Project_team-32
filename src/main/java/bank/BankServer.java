package bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.json.simple.JSONObject;

public class BankServer {
    private static int port = 4299;
    private static List<Integer> unusedTokens;
    private static List<Integer> usedTokens;
    private static Connection c;
    private static Statement s;

    private static class InvalidToken extends Exception {

    }

    private static class ExpiredToken extends Exception {

    }

    private static class NotEnoughMoneyInSourceAccount extends Exception {

    }

    private static void updateDatabase(String stmt) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            s = c.createStatement();
            s.executeUpdate(stmt);
            s.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ResultSet readFromDatabase(String stmt) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            ResultSet rs = c.createStatement().executeQuery(stmt);
            return rs;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private static void initializingUsedAndUnusedTokens() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db.db");
            c.setAutoCommit(false);
            s = c.createStatement();
            for(int i = 0; i < 10000; i++) {
                s.executeUpdate("INSERT INTO unused_tokens "
                        + "VALUES (" + unusedTokens.get(i) + ");");
            }
            s.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static interface Storable {
        void store();
        void update();
    }

    private static class Account implements Storable{

        private static List<Account> allAccounts = new ArrayList<>();

        private String firstname;
        private String lastname;
        private String username;
        private String password;
        private int accountId;
        private final List<Token> allTokens;
        private Token currentToken;
        private List<Receipt> receiptsWithThisAccountAsSource = new LinkedList<>();
        private List<Receipt> receiptssWithThisAccountAsDestination = new LinkedList<>();
        private int money = 1000;


        public Account(String firstname, String lastname, String username, String password ) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.username = username;
            this.password = password;
            allAccounts.add(this);
            this.accountId = allAccounts.size();
            this.allTokens = new LinkedList<>();
        }

        void addToken(Token token) {
            this.allTokens.add(token);
            this.currentToken = token;
        }

        static ValidatingTokenStatus checkToken(int accountId, String token) {
            for(Token t : Account.allAccounts.get(accountId - 1).allTokens) {
                if(t.token.equals(token)) {
                    if(Account.allAccounts.get(accountId - 1).currentToken == t && Account.allAccounts.get(accountId - 1).currentToken.expirationDate.after(new Date())){
                        return ValidatingTokenStatus.valid;
                    } else {
                        return ValidatingTokenStatus.expired;
                    }
                }
            }

            return ValidatingTokenStatus.invalid;
        }

        static int getAccountIdByToken(String token) throws ExpiredToken, InvalidToken {
            for(Account account: allAccounts) {
                if(account.currentToken.token.equals(token)) {
                    if(account.currentToken.expirationDate.before(new Date())) {
                        throw new ExpiredToken();
                    }
                    return account.accountId;
                }
                for(Token t : account.allTokens) {
                    if(t.token.equals("token")) {
                        throw new ExpiredToken();
                    }
                }
            }

            throw new InvalidToken();
        }

        @Override
        public void store() {

            String s = "INSERT INTO account (firstname, lastname, username, password, accountId, currentToken, money) "
                    + "VALUES ('" + firstname + "', '" +
                    lastname + "', '" + username + "', '" +
                    password + "', " + accountId + ", '" +
                    currentToken + "', " + money + ");";
            updateDatabase(s);
        }

        @Override
        public void update() {
            String s = "UPDATE account "
                    + "SET money=" + money + ", currentToken='" + currentToken.token +
                    "' Where accountId=" + accountId + ";";
            updateDatabase(s);
        }

        public void loadingAllTokens() {
            for(Token t: Token.allTokens) {
                if(t.accountId == accountId) {
                    allTokens.add(t);
                }
            }
        }
    }

    private enum ValidatingTokenStatus {
        valid, invalid, expired
    }

    private static class Token implements Storable {

        private static List<Token> allTokens = new LinkedList<>();

        private String token;
        private Date expirationDate;
        private int accountId;

        public Token(String token, int accountId) {
            this.token = token;
            this.accountId = accountId;
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.HOUR, 1);
            this.expirationDate = cal.getTime();
            allTokens.add(this);
        }

        @Override
        public void store() {
            String s = "INSERT INTO token "
                    + "VALUES ('" + token + "', "+
                    expirationDate.getTime() + ", " +
                    accountId + ");";
            updateDatabase(s);
        }

        @Override
        public void update() {

        }

        static Token getToken(String token) {
            for(Token t : allTokens) {
                if(t.token.equals(token)) {
                    return t;
                }
            }
            return null;
        }

    }

    private static class ReceiptFactory {
        public static Receipt createReceipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
            if(type.equals(ReceiptTypes.deposit) ) {
                return new DepositReceipt(money, sourceId, destId, description);
            } else if (type.equals(ReceiptTypes.withdraw)) {
                return new WithdrawReceipt(money, sourceId, destId, description);

            } else  {
                return new MoveReceipt(money, sourceId, destId, description);
            }
        }
    }

    private enum ReceiptTypes {
        deposit, withdraw, move
    }

    private static abstract class Receipt implements Storable {
        static List<Receipt> allReceipts = new ArrayList<Receipt>();

        ReceiptTypes type;
        int money;
        int sourceId;
        int destId;
        String description;
        int receiptId;
        boolean payStatus;

        public void setPayStatus() {
            payStatus = true;
        }

        public Receipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
            this.type = type;
            this.money = money;
            this.sourceId = sourceId;
            this.destId = destId;
            this.description = description;
            allReceipts.add(this);
            this.receiptId = allReceipts.size();
            if(type == ReceiptTypes.deposit) {
                Account.allAccounts.get(destId - 1).receiptssWithThisAccountAsDestination.add(this);
            } else if(type == ReceiptTypes.withdraw) {
                Account.allAccounts.get(sourceId - 1).receiptsWithThisAccountAsSource.add(this);
            } else {
                Account.allAccounts.get(sourceId - 1).receiptsWithThisAccountAsSource.add(this);
                Account.allAccounts.get(destId - 1).receiptssWithThisAccountAsDestination.add(this);
            }
        }

        static ReceiptTypes getTypeByName(String string) {
            switch(string) {
                case "deposit" :
                    return ReceiptTypes.deposit;
                case "withdraw":
                    return ReceiptTypes.withdraw;
                case "move":
                    return ReceiptTypes.move;
            }
            return null;
        }

        static List<Receipt> getAllReceiptsWithSourceAccount(int id) {
            List<Receipt> output = new LinkedList<Receipt>();
            for(Receipt receipt: allReceipts) {
                if(receipt.sourceId == id) {
                    output.add(receipt);
                }
            }
            return output;

        }

        static List<Receipt> getAllReceiptsWithDestinationAccount(int id) {
            List<Receipt> output = new LinkedList<Receipt>();
            for(Receipt receipt: allReceipts) {
                if(receipt.destId == id) {
                    output.add(receipt);
                }
            }
            return output;
        }

        static List<Receipt> getAllReceiptsWithAccount(int id) {
            List<Receipt> output = new LinkedList<>();
            output.addAll(getAllReceiptsWithDestinationAccount(id));
            output.addAll(getAllReceiptsWithSourceAccount(id));
            return output;

        }


        JSONObject convertToJSONObject() {
            JSONObject object = new JSONObject();
            object.put("receiptTypes", type);
            object.put("money", money);
            object.put("sourceAccountID", sourceId);
            object.put("destAccountID", destId);
            object.put("description", description);
            object.put("id", receiptId);
            object.put("paid", payStatus);
            return object;
        }

        @Override
        public void store() {
            String s = "INSERT INTO receipt "
                    + "VALUES ('" + type + "', " +
                    money + ", " + sourceId + ", " +
                    destId + ", " + payStatus + ", '"+
                    description + "', " + receiptId + ");";
            updateDatabase(s);
        }

        @Override
        public void update() {
            String s = "UPDATE receipt "
                    + "SET payStatus=" + payStatus +
                    " WHERE receiptId=" + receiptId + ";";
            updateDatabase(s);
        }


        public abstract void pay() throws Exception;

    }

    private static class DepositReceipt extends Receipt {
        public DepositReceipt(int money, int sourceId, int destId, String description) {
            super(ReceiptTypes.deposit , money, sourceId, destId, description);
        }

        @Override
        public void pay() throws Exception {
            Account.allAccounts.get(destId - 1).money += money;
            Account.allAccounts.get(destId - 1).update();
            setPayStatus();
            update();
        }

    }

    private static class WithdrawReceipt extends Receipt {
        public WithdrawReceipt(int money, int sourceId, int destId, String description) {
            super(ReceiptTypes.withdraw, money, sourceId, destId, description);
        }

        @Override
        public void pay() throws Exception {
            Account sourceAccount;
            if((sourceAccount = Account.allAccounts.get(sourceId - 1)).money < money) {
                throw new NotEnoughMoneyInSourceAccount();
            } else {
                sourceAccount.money -= money;
                sourceAccount.update();
                setPayStatus();
                update();
            }

        }

    }

    private static class MoveReceipt extends Receipt {

        public MoveReceipt(int money, int sourceId, int destId, String description) {
            super(ReceiptTypes.move, money, sourceId, destId, description);
        }

        @Override
        public void pay() throws Exception {
            Account sourceAccount;
            Account destAccount;
            if((sourceAccount = Account.allAccounts.get(sourceId - 1)).money < money) {
                throw new NotEnoughMoneyInSourceAccount();
            } else {
                destAccount = Account.allAccounts.get(destId - 1);
                sourceAccount.money -= money;
                destAccount.money += money;
                sourceAccount.update();
                destAccount.update();
                setPayStatus();
                update();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        load();
        if(unusedTokens.size() == 0) {
            unusedTokens = new ArrayList<>(10000);
            usedTokens = new LinkedList<>();
            for (int i = 1; i <= 10000; i++) {
                unusedTokens.add(i);
            }
            Collections.shuffle(unusedTokens);
            initializingUsedAndUnusedTokens();
        }
        startListeningOnPort();
    }

    public static void startListeningOnPort() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while(true) {
            new HandleClientThread(serverSocket.accept()).start();
        }
    }

    private static class HandleClientThread extends Thread {

        private Scanner scanner;
        private Formatter formatter;

        public HandleClientThread(Socket socket) {
            try {
                scanner = new Scanner(socket.getInputStream());
                formatter = new Formatter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String command;
            while(!(command = scanner.nextLine()).equals("exit")) {
                String[] commandParts = command.split(" ");
                switch (commandParts[0]) {
                    case "create_account":
                        if (commandParts.length != 6) {
                            format("invalid input");
                            break;
                        }
                        format(createAccount(commandParts));
                        break;
                    case "get_token":
                        if (commandParts.length != 3) {
                            format("invalid input");
                            break;
                        }
                        format(getToken(commandParts) + "");
                        break;
                    case "create_receipt":
                        if (commandParts.length != 7) {
                            format("invalid input");
                            break;
                        }
                        format(createReceipt(commandParts));
                        break;
                    case "get_transactions":
                        if (commandParts.length != 3) {
                            format("invalid input");
                            break;
                        }
                        format(getTransactions(commandParts));
                        break;
                    case "pay":
                        if (commandParts.length != 2) {
                            format("invalid input");
                            break;
                        }
                        format(pay(commandParts));
                        break;
                    case "get_balance":
                        if (commandParts.length != 2) {
                            format("invalid input");
                            break;
                        }
                        format(getBalance(commandParts));
                        break;
                    default:
                        format("invalid input");
                }
            }
        }

        private void format(String msg) {
            formatter.format(msg + "\n");
            formatter.flush();
        }
    }

    public static String createAccount(String[] commandParts) {
        String firstname = commandParts[1];
        String lastname = commandParts[2];
        String username = commandParts[3];
        String password = commandParts[4];
        String repeatedPassword = commandParts[5];

        if(!repeatedPassword.equals(password)) {
            return "passowrds do not match";
        }

        for(Account account: Account.allAccounts) {
            if(account.username.equals(username)) {
                return "username is not available";
            }
        }

        Account account = new Account(firstname, lastname, username, password);
        account.store();
        return account.accountId + "";
    }

    public static String getToken(String[] commandParts) {
        String username = commandParts[1];
        String password = commandParts[2];
        for(Account account: Account.allAccounts) {
            if(account.username.equals(username) && account.password.equals(password)) {
                return generateToken(account);
            }
        }
        return "invalid username or password";
    }

    public static String createReceipt(String[] commandParts) {
        String token = commandParts[1];
        ReceiptTypes type = Receipt.getTypeByName(commandParts[2]);
        int money;
        int sourceId;
        int destId;


        if(type == null) {
            return "invalid receipt type";
        }

        try {
            money = Integer.parseInt(commandParts[3]);
            if(money <= 0) {
                throw new Exception();
            }
        } catch(Exception e)  {
            return "invalid money";
        }


        try {
            sourceId = Integer.parseInt(commandParts[4]);
            if(type == ReceiptTypes.deposit && (sourceId!= -1 )) {
                throw new Exception();
            }
            if(sourceId > Account.allAccounts.size()) {
                throw new Exception();
            }
        } catch(Exception e) {
            return "source account id is invalid";
        }


        try {
            destId = Integer.parseInt(commandParts[5]);
            if(type == ReceiptTypes.withdraw && destId!= -1) {
                throw new Exception();
            }
            if(destId > Account.allAccounts.size()) {
                throw new Exception();
            }
        } catch(Exception e) {
            return "dest account id is invalid";
        }

        if(type == ReceiptTypes.deposit) {
            if(Account.checkToken(destId, token) == ValidatingTokenStatus.invalid) {
                return "token is invalid";
            }
            if(Account.checkToken(destId, token) == ValidatingTokenStatus.expired) {
                return "token expired";
            }
        } else if(type == ReceiptTypes.withdraw || type == ReceiptTypes.move) {
            if(Account.checkToken(sourceId, token) == ValidatingTokenStatus.invalid) {
                return "token is invalid";
            }
            if(Account.checkToken(sourceId, token) == ValidatingTokenStatus.expired) {
                return "token expired";
            }
        }

        if(sourceId == destId) {
            return "equal source and dest account";
        }


        String description = commandParts[6];

        Receipt receipt = ReceiptFactory.createReceipt(type, money, sourceId, destId, description);
        receipt.store();

        return receipt.receiptId + "";
    }

    public static String getTransactions(String[] commandParts) {

        String token = commandParts[1];
        String type = commandParts[2];

        try {
            String answer = "";
            int accountId = Account.getAccountIdByToken(token);
            if(type.matches("\\d+")) {
                for(Receipt receipt : Receipt.getAllReceiptsWithAccount(accountId)) {
                    if(receipt.receiptId == Integer.parseInt(type)) {
                        return receipt.convertToJSONObject() + "";
                    }
                }

                return "invalid receipt id";
            }

            switch(type) {
                case "*":
                    for(Receipt receipt : Receipt.getAllReceiptsWithAccount(accountId)) {
                        answer += receipt.convertToJSONObject();
                        answer += "*";
                    }
                    break;
                case "+":
                    for(Receipt receipt : Receipt.getAllReceiptsWithDestinationAccount(accountId)) {
                        answer += receipt.convertToJSONObject();
                        answer += "*";
                    }
                    break;
                case "-":
                    for(Receipt receipt : Receipt.getAllReceiptsWithSourceAccount(accountId)) {
                        answer += receipt.convertToJSONObject();
                        answer += "*";
                    }
                    break;
            }

            return answer;
        } catch (ExpiredToken e) {
            return "token expired";
        } catch (InvalidToken e) {
            return "token is invalid";
        }
    }

    public static String pay(String[] commandParts) {

        int receiptId;
        Receipt receipt;

        try {
            receiptId = Integer.parseInt(commandParts[1]);
            if(receiptId > Receipt.allReceipts.size()) {
                throw new Exception();
            }

        } catch(Exception e) {
            return "invalid receipt id";
        }

        if((receipt = Receipt.allReceipts.get(receiptId - 1)).payStatus) {
            return "receipt is paid before";
        } else {
            try {
                receipt.pay();
            } catch(NotEnoughMoneyInSourceAccount e) {
                return "source account does not have enough money";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "done successfully";
    }

    public static String getBalance(String[] commandParts) {
        String token = commandParts[1];
        try {
            Account account = Account.allAccounts.get(Account.getAccountIdByToken(token) - 1);
            return account.money + "";
        } catch (ExpiredToken e) {
            return "token expired";
        } catch (InvalidToken e) {
            return "token is invalid";
        }
    }

    public static String generateToken(Account account) {
        Integer random = unusedTokens.get(1);
        unusedTokens.remove(random);
        usedTokens.add(random);
        Token token = new Token( random + "", account.accountId);
        account.addToken(token);
        token.store();
        account.update();
        String s = "INSERT INTO used_tokens "
                + "VALUES (" + random + ");";
        updateDatabase(s);
        s = "DELETE FROM unused_tokens "
                + "WHERE token=" + random + ";";
        updateDatabase(s);
        return token.token;
    }

    public static void load() {
        unusedTokens = new ArrayList<>(10000);
        usedTokens = new LinkedList<>();
        String s = "SELECT * FROM unused_tokens;";
        ResultSet rs = readFromDatabase(s);
        try {
            while(rs.next()) {
                unusedTokens.add(rs.getInt("token"));
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        s = "SELECT * FROM used_tokens;";
        rs = readFromDatabase(s);
        try {
            while(rs.next()) {
                usedTokens.add(rs.getInt("token"));
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        s = "SELECT * FROM token;";
        rs = readFromDatabase(s);
        try {
            while(rs.next()) {
                Token token = new Token(rs.getString("token"), rs.getInt("account id"));
                token.expirationDate = rs.getTime("expiration date");
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        s = "SELECT * FROM account ORDER BY accountId;";
        rs = readFromDatabase(s);
        try {
            while(rs.next()) {
                new Account(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"),
                        rs.getString("password")).currentToken = Token.getToken(rs.getString("currentToken"));
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Account a: Account.allAccounts) {
            a.loadingAllTokens();
        }

        s = "SELECT * FROM receipt ORDER BY receiptId;";
        rs = readFromDatabase(s);
        try {
            while(rs.next()) {
                ReceiptFactory.createReceipt(Receipt.getTypeByName(rs.getString("type")), rs.getInt("money"), rs.getInt("sourceId")
                        , rs.getInt("destId"), rs.getString("description"));
            }
            rs.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}