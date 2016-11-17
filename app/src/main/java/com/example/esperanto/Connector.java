package com.example.esperanto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connector {

    private final int PORT = 3306;
    private Connection connection;
    private PreparedStatement stmt = null;

    public Connector(String HOST, String DB, String UN, String PW) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;
            connection = DriverManager.getConnection(url, UN, PW);
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public <E> ResultSet select(String query,E[] values )throws SQLException{
        stmt = connection.prepareStatement(query);
        ResultSet res = stmt.executeQuery();
        return res;
    }
    
}
