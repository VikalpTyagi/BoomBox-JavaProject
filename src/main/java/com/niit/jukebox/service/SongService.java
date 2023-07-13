package com.niit.jukebox.service;

import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Song;

import java.sql.SQLException;
import java.util.ArrayList;

public class SongService {

    private Song getASong(String songName,ArrayList<Song> songList)throws JukeboxException{
        Song result;
        if (songName!=null && songList!=null) {
            result=new Song();
            for (Song list : songList)
                if (songName.equals(list)) {
                    result = list;
                    break;
                }
        }
        else
            throw new JukeboxException("Input all the values");

        return result;
    }

    public boolean addSong(Song song,ArrayList<Song> songList) throws SQLException, JukeboxException {
        boolean result=false;
        if(getASong(song.getSongName(),songList)!=null) {
            SongDAO.insertSong(song);
            System.out.println("Added Successfully");
            result=true;
        }
        return result;
    }

    ArrayList<Song> getSongs() throws SQLException{
        return SongDAO.allSongs();
    }

    Song getSongByName(String name,ArrayList<Song>songList) throws JukeboxException{
        Song result=null;
        if(songList.isEmpty() && name==null)
        {
            throw new JukeboxException("Please input all the values");
        }
        else {
            for (Song list : songList) {
                if (list.getSongName().equals(name)) {
                    result = list;
                    break;
                }
            }
        }
        return result;
    }
    ArrayList<Song>getSongByAlbum(String album,ArrayList<Song>songList) throws JukeboxException{
        ArrayList<Song> list;
        if(!songList.isEmpty() && album!=null){
            list=new ArrayList<>();
            for(Song song:songList){
                if(song.getAlbum().equals(album)) {
                    list.add(song);
                    break;
                }
            }
        }
        else
            throw new JukeboxException("Please input all the values");

        return list;
    }

    ArrayList<Song>getSongByArtistName(String artistName,ArrayList<Song>songList) throws JukeboxException{
        ArrayList<Song> list;
        if(songList.isEmpty()==false && artistName!=null){
            list=new ArrayList<>();
            for(Song song : songList)
                if(song.getArtist_Name().contains(artistName))
                    list.add(song);
        }
        else
            throw new JukeboxException("Please input all the values");
        return list;
    }

    ArrayList<Song>getSongByGenre(String genre,ArrayList<Song>songList) throws JukeboxException{
        ArrayList<Song> list;
        if(songList.isEmpty()==false && genre!=null){
            list=new ArrayList<>();
            for(Song song : songList)
                if(song.getGenre().contains(genre))
                    list.add(song);
        }
        else
            throw new JukeboxException("Please input all the values");
        return list;
    }

    public void display(ArrayList<Song> songList){
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format(GREEN+"\t%10s\t%40s\t%60s\t%30s\t%30s\t%20s\n","songID" ,"songName","artist_Name","genre","album","duration"+RESET);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        for (Song list:songList)
            System.out.println(list);
    }

    public static final String GREEN ="\u001B[32m";
    public static final String RESET = "\u001B[0m";
}
