package com.niit.jukebox.model;

public class Song {

    private int songID;
    private String songName;
    private String artist_Name;
    private String genre;
    private String album;
    private String duration;

    public Song() {
    }

    public Song(String songName, String artist_Name, String genre, String album, String duration) {
        this.songName = songName;
        this.artist_Name = artist_Name;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
    }

    public Song(int songID , String songName, String artist_Name, String genre, String album, String duration) {
        this.songID = songID;
        this.songName = songName;
        this.artist_Name = artist_Name;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist_Name() {
        return artist_Name;
    }

    public void setArtist_Name(String artist_Name) {
        this.artist_Name = artist_Name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("\t%10s\t%49s\t%60s\t%30s\t%30s\t%20s",songID ,BLUE+songName+RESET,artist_Name,genre,album,duration);
    }

    public static final String BLUE ="\u001B[34m";
    public static final String RESET = "\u001B[0m";
}
