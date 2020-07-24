package Server.Database;

import Server.Model.Logs;
import Server.Model.SellLog;

import java.sql.*;
import java.util.Date;

public class Database {

    private String name;
    private String url;
    private Connection connection;
    private Statement statement;

    private Database(String url) {
        this.name = "org.sqlite.JDBC";
        this.url = url;
    }

    private static Database logsDatabase;
    private static Database goodsDatabase;

    public static Database getInstance(DatabaseType type) {
        switch (type) {
            case logsDatabase:
                if(logsDatabase == null) {
                    logsDatabase = new Database("jdbc:sqlite:logs.db");
                }
                return logsDatabase;
            case goodsDatabase:
                if(goodsDatabase == null) {
                    goodsDatabase = new Database("jdbc:sqlite:goods.db");
                }
                return goodsDatabase;
            default:
                return null;
        }
    }

    public synchronized void update(String s) {
        try {
            Class.forName(name);
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(s);
            statement.close();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load() {

    }

    private static void loadLogs() throws SQLException {
        loadBuyLogs();
        loadSellLogs();
    }

    private static void loadSellLogs() throws SQLException {
        String s = "SELECT * FROM sellLogs;";
        ResultSet resultSet = logsDatabase.readFromDatabase(s);
        while (resultSet.next()) {
            //new SellLog("a", new Date(), 1.2f, 0.6f, good, "alisharifi", "posted");
        }
    }

    private static void loadBuyLogs() {
    }

    private ResultSet readFromDatabase(String s) {
        try {
            Class.forName(name);
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            ResultSet rs = connection.createStatement().executeQuery(s);
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

