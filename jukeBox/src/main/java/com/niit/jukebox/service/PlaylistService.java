package com.niit.jukebox.service;

import com.niit.jukebox.dao.PlaylistDao;
import com.niit.jukebox.dao.SongConnection;
import com.niit.jukebox.dao.SongDao;
import com.niit.jukebox.model.Songs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeSet;

public class PlaylistService {
    public boolean addToPlaylist(String playlistName, Hashtable<String, Integer> playlist) throws SQLException {
        boolean result=false;
        boolean playlistisPresent = playlist.containsKey(playlistName);
        if (playlistisPresent == false) {
           PlaylistDao.creatPlaylist(playlistName);
           result=true;
        }return result;
    }
        public Hashtable<String, Integer> getAllPlaylist () throws SQLException
        {

            return PlaylistDao.viewAllPlaylist();
        }
    }

