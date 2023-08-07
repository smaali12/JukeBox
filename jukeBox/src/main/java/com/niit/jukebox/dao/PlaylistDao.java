package com.niit.jukebox.dao;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlaylistDao {
   public static boolean creatPlaylist(String playlistName) throws SQLException {
        PreparedStatement insertStatment=SongConnection.getSongsConnection().prepareStatement("insert into Playlist(playlist_name) values(?);");
        insertStatment.setString(1, playlistName);
        int result=insertStatment.executeUpdate();
        return result>0?true:false;
    }
    public static Hashtable<String,Integer>viewAllPlaylist() throws SQLException
    {
        Hashtable<String,Integer> playlisthashtable=new Hashtable<>();
        Statement selectStatement=SongConnection.getSongsConnection().createStatement();
        ResultSet resultSet= selectStatement.executeQuery("select * from Playlist");
            while (resultSet.next()) {
                playlisthashtable.put(resultSet.getString(2),resultSet.getInt(1));
            }
        return playlisthashtable;
    }
    }

