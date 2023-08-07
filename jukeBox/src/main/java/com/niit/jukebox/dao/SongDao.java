package com.niit.jukebox.dao;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongDao {
    public static boolean insertSong(Songs song)throws SQLException{
        PreparedStatement insertStatment=SongConnection.getSongsConnection().prepareStatement("insert into Songs(song_name,album_name,artist_name,geners,song_duration) values(?,?,?,?,?);");
                insertStatment.setString(1, song.getSong_name());
                insertStatment.setString(2,song.getAlbum_name());
                insertStatment.setString(3,song.getArtist_name());
                insertStatment.setString(4,song.getGeners());
                insertStatment.setString(5,song.getSong_duration());
                int result=insertStatment.executeUpdate();
                return result>0?true:false;
    }
    public static ArrayList<Songs> selectAllSongs() throws SQLException
    {
        ArrayList<Songs> songArrayList=null;
        Statement selectStatement=SongConnection.getSongsConnection().createStatement();
        ResultSet resultSet= selectStatement.executeQuery("select * from Songs");
       if(resultSet.isBeforeFirst())
       {
            songArrayList=new ArrayList<>();
            while (resultSet.next())
            {
                songArrayList.add(new Songs(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
            }
       }
        return songArrayList;
    }
}
