package com.niit.jukebox.service;

import com.niit.jukebox.dao.SongDao;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongsService {

    public Songs  getASongBySongName (String song_name,ArrayList<Songs> songsArrayList) throws jukeBoxException
    {
        Songs selectSongByName=null;
        if (songsArrayList.isEmpty()==false && song_name!=null){
            for (Songs song:songsArrayList){
                if (song.getSong_name().equals(song_name))
                {
                  selectSongByName=new Songs();
                  selectSongByName=song;
                  break;
                }
            }
        }
        else
            throw new jukeBoxException("Enter proper values");
        return selectSongByName;
    }
        public boolean addSongs (Songs song,ArrayList<Songs>songsArrayList) throws Exception {
            boolean result = false;
            if (getASongBySongName(song.getSong_name(),songsArrayList)==null)
            {
                  SongDao.insertSong(song);
                result=true;
            }

            return result;
        }
       public void displaySongs (ArrayList<Songs>songsArrayList) {

           System.out.println("******************************************************************************************************************************************************************************");
            System.out.format("%10s\t%35s\t%20s\t%30s\t%20s\t%15s","SONGID","SONG NAME","ALBUM NAME","ARTIST NAME","GENERS","SONG DURATION\n");
           System.out.println("******************************************************************************************************************************************************************************");
            if (songsArrayList == null) {
                System.out.println("Table is Empty");
            } else {
                Iterator<Songs> displaysong = songsArrayList.listIterator();
                while (displaysong.hasNext()) {
                    System.out.println(displaysong.next());
//
                }
            }
        }
   public ArrayList<Songs>getSongsByAlbumName(String album_name,ArrayList<Songs>songsArrayList) {
        ArrayList<Songs>filterByAlbumName=null;
        if (songsArrayList.isEmpty()==false&&album_name!=null){
            filterByAlbumName=new ArrayList<>();
            for (Songs song:songsArrayList){
                if (song.getAlbum_name().equals(album_name))
                    filterByAlbumName.add(song);
            }
        }
        return filterByAlbumName;
    }
   public ArrayList<Songs>getSongsByArtistName(String artist_name,ArrayList<Songs>songsArrayList) {
        ArrayList<Songs>filterByArtistName=null;
        if (songsArrayList.isEmpty()==false && artist_name!=null){
            filterByArtistName =new ArrayList<>();
            for (Songs song:songsArrayList){
                if (song.getArtist_name().contains(artist_name))
                    filterByArtistName.add(song);
            }
        }
        return filterByArtistName;
    }
   public ArrayList<Songs>getSongsByGeners(String geners,ArrayList<Songs>songsArrayList) {
        ArrayList<Songs>filterByGenerse=null;
        if (songsArrayList.isEmpty()==false && geners!=null){
            filterByGenerse =new ArrayList<>();
            for (Songs song:songsArrayList){
                if (song.getGeners().equals(geners))
                    filterByGenerse.add(song);
            }
        }
        return filterByGenerse;
    }
    public ArrayList<Songs>getAllSongs()throws Exception
    {
        return SongDao.selectAllSongs();
    }
    }


