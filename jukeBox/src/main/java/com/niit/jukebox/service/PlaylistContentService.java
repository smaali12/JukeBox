package com.niit.jukebox.service;

import com.niit.jukebox.dao.PlaylistContentDao;
import com.niit.jukebox.dao.PlaylistDao;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlaylistContentService
{
    public boolean addSongBySongName(ArrayList<Songs>songlist, Hashtable<String,Integer>playlist,String songName,String playlistName)throws SQLException,jukeBoxException
    {
        int playlistId;
        int songId;
        boolean result=false;
        if (songlist.isEmpty()||playlist.isEmpty()||songName==null||playlistName==null) {
            throw new jukeBoxException("Please provide all values");
        }
            else
            {
                if (playlist.get(playlistName)==null){
                    throw new jukeBoxException("Playlist not found ");
                }

                playlistId=playlist.get(playlistName);
                songId=0;
                for (Songs song:songlist) {
                    if (song.getSong_name().equals(songName)) {
                        songId = song.getSong_id();
                        break;
                    }
                }
                    if (playlistId==0)
                        throw new jukeBoxException("Playlist is not available");
                    else if (songId==0) {
                        throw new jukeBoxException("Song is not Present in database");
                    }
                    else
                        result= PlaylistContentDao.addSongsToPlaylist(songId,playlistId);

            }
                 return result;
    }

    public boolean addSongByAlbumName(ArrayList<Songs>songlist, Hashtable<String,Integer>playlist,String albumName,String playlistName)throws SQLException,jukeBoxException
    {
        int playlistId;
        boolean result=false;
        if (songlist.isEmpty()||playlist.isEmpty()||albumName==null||playlistName==null) {
            throw new jukeBoxException("Please provide all values");
        }
        else
        {
            if (playlist.get(playlistName)==null){
                throw new jukeBoxException("Playlist not found ");
            }
            playlistId=playlist.get(playlistName);
            ArrayList<Integer>songIdlist=new ArrayList<>();

            for (Songs song:songlist) {
                if (song.getAlbum_name().equals(albumName)) {
                    songIdlist.add(song.getSong_id());
                    result = true;

                }
            }
                if (playlistId==0)
                    throw new jukeBoxException("Playlist is not Present");
                else if (songIdlist.isEmpty()) {
                    throw new jukeBoxException("Song is not Present ");
                }
                else
                {
                    for (int songid:songIdlist)
                        result=PlaylistContentDao.addSongsToPlaylist(songid,playlistId);
                }

        }
        return result;
    }
    public ArrayList<Songs>playlistContent(String playlistName, Hashtable<String, Integer> playlist, ArrayList<Songs> songsArrayList) throws jukeBoxException, SQLException {
        ArrayList<Integer>songIdlist;
        ArrayList<Songs>songlist=null;
        if (playlistName==null||playlist.isEmpty()||songsArrayList.isEmpty())
            throw new jukeBoxException("Provide all the details");
        else {
            int playListId=0;
            if (playlist.containsKey(playlistName) == false)
                throw new jukeBoxException("Playlist name is not found ");
            else {
                playListId=playlist.get(playlistName);
                songIdlist =PlaylistContentDao.ViewSongsInPlaylist(playListId);
                if (songsArrayList.isEmpty()==false){
                    songlist=new ArrayList<>();
                    for (int id:songIdlist)
                    {
                          for (Songs song:songsArrayList){
                            if (song.getSong_id()==id)
                                songlist.add(song);
                        }
                    }
                }else
                    throw new jukeBoxException("Playlist is Empty");
            }
        }return songlist;
    }
}
