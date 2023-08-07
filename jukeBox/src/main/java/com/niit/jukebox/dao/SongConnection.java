package com.niit.jukebox.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  SongConnection {
    public static Connection getSongsConnection() throws SQLException{
        Connection connnection= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeBox","root","Shradha@2023");
        return connnection;
}
}
