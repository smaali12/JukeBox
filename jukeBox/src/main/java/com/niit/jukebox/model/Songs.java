package com.niit.jukebox.model;

public class Songs {
    private int song_id;
    private String song_name;
    private String album_name;
    private String artist_name;
    private String geners;
    private String song_duration;

    public  Songs()
    {

        }
    public Songs(int song_id, String song_name, String album_name, String artist_name, String geners, String song_duration) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.album_name = album_name;
        this.artist_name = artist_name;
        this.geners = geners;
        this.song_duration = song_duration;
    }

    public Songs(String song_name, String album_name, String artist_name, String geners, String song_duration) {
        this.song_name = song_name;
        this.album_name = album_name;
        this.artist_name = artist_name;
        this.geners = geners;
        this.song_duration = song_duration;
    }

    public String getSong_name() {
        return song_name;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getGeners() {
        return geners;
    }

    public void setGeners(String geners) {
        this.geners = geners;
    }

    public String getSong_duration() {
        return song_duration;
    }

    public void setSong_duration(String song_duration) {
        this.song_duration = song_duration;
    }
    @Override
    public String toString() {
        return String.format("%10s\t%35s\t%20s\t%30s\t%20s\t%15s",song_id,song_name,album_name,artist_name,geners,song_duration);
    }
}
