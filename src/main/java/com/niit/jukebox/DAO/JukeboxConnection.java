package com.niit.jukebox.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JukeboxConnection {

   public static Connection getConnection () throws SQLException {
       Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","password");

        return connect;
    }
}
