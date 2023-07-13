package com.niit.jukebox.service;

import com.niit.jukebox.DAO.PlaylistDAO;

import java.sql.SQLException;
import java.util.Hashtable;

public class PlaylistService {


    boolean addPlaylist(String playlistName, Hashtable<String,Integer> playlist)throws SQLException
    {
        boolean result=false;
        boolean playlistIsPresent=playlist.containsKey(playlistName);
        if(playlistIsPresent==false)
        {
            PlaylistDAO.createPlaylist(playlistName);
            result=true;
        }
        return result;
    }


    public  Hashtable<String,Integer> getAllPlaylist() throws SQLException
    {
        return PlaylistDAO.viewAllPlaylist();
    }

}
