package br.com.earthpvp.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQL {

    private static Connection connection;
    private static Statement statement;
    private static String host, user, password, database;

    public MySQL(String host, String user, String password, String database) {
        MySQL.host = host;
        MySQL.user = user;
        MySQL.password = password;
        MySQL.database = database;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS team_speak_users (id VARCHAR(225), username VARCHAR(225), realname VARCHAR(16), grupo VARCHAR(16));");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
