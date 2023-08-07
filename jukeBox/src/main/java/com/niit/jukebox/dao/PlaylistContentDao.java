package com.niit.jukebox.dao;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PlaylistContentDao {

    public static boolean addSongsToPlaylist(int songId, int playlistId) throws SQLException {
        PreparedStatement insertStatment=SongConnection.getSongsConnection().prepareStatement("insert into PlaylistContent(PlaylistId,songId) values(?,?);");
        insertStatment.setInt(1,playlistId);
        insertStatment.setInt(2, songId);
        int result=insertStatment.executeUpdate();
        return result>0?true:false;
    }
    public static ArrayList<Integer> ViewSongsInPlaylist(int playlistId) throws SQLException
    {
        ArrayList<Integer>integerArrayList=new ArrayList<>();
        PreparedStatement viewSongsList=SongConnection.getSongsConnection().prepareStatement("select * from PlaylistContent where PlaylistId=?;");
        viewSongsList.setInt(1,playlistId);
        ResultSet resultSet= viewSongsList.executeQuery();
            while (resultSet.next()) {
                integerArrayList.add(resultSet.getInt(2));
            }
            return integerArrayList;
    }
}