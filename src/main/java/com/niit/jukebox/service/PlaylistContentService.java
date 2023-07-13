package com.niit.jukebox.service;

import com.niit.jukebox.DAO.PlaylistContentDAO;
import com.niit.jukebox.model.Song;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class PlaylistContentService {

    public boolean addSongByName(ArrayList<Song> songlist, Hashtable<String,Integer> playlist, String songName, String playlistName)throws SQLException, JukeboxException
    {
        int playlistID;
        int songID=0;
        boolean result=false;
        if(songlist.isEmpty() || playlist.isEmpty() || songName==null || playlistName==null)
        {
            throw new JukeboxException("Please provide all the values");
        }
        else
        {
            playlistID=playlist.get(playlistName);
            for (Song song:songlist)
            {
                if(song.getSongName().equals(songName))
                {
                    songID=song.getSongID();
                    result=true;
                    break;
                }
            }
            if(playlistID==0)
                throw new JukeboxException("Playlist not Present");
            else if (songID==0)
                throw new JukeboxException("Song not Present");
            else
                PlaylistContentDAO.addPlaylist(songID,playlistID);
        }
        return result;
    }

    public boolean addSongByAlbumName(ArrayList<Song> songList, Hashtable<String,Integer> playlist, String albumName, String playlistName)throws SQLException, JukeboxException
    {
        int playlistID;
        boolean result=false;
        if(songList.isEmpty() || playlist.isEmpty() || albumName==null || playlistName==null)
            throw new JukeboxException("Please provide all the values");
        else
        {
            playlistID=playlist.get(playlistName);
            ArrayList<Integer>songIdList= new ArrayList<>();
            for(Song song:songList)
            {
                if(song.getAlbum().equals(albumName))
                {
                    songIdList.add(song.getSongID());
                    result=true;
                }
            }
            if(playlistID==0)
                throw new JukeboxException("Playlist name Not Present ");
            else if (songIdList.isEmpty())
                throw new JukeboxException("Song not Present");
            else
            {
                for(int id:songIdList)
                    PlaylistContentDAO.addPlaylist(id,playlistID);
            }
        }
        return result;
    }

    public ArrayList<Song> playlistContent(String playlistName,Hashtable<String,Integer> playlist, ArrayList<Song> songList) throws SQLException, JukeboxException
    {
        ArrayList<Song> forReturnList=new ArrayList<>();
        ArrayList<Integer> intArraylist;
        if( playlist.isEmpty() || songList==null || playlistName==null)
            throw new JukeboxException("Please provide all the values");
        else
        {
            int playListId= playlist.get(playlistName);
            if(playListId==0)
                throw new JukeboxException("Input correct value of Playlist");
            else {
                intArraylist = PlaylistContentDAO.viewSongInPlaylist(playListId);
            }
            if(intArraylist.isEmpty()==false)
            {
               // forReturnList =new ArrayList<>();
                for (int id:intArraylist)
                {
                    for(Song song: songList)
                    {
                        if(song.getSongID()==id)
                            forReturnList.add(song);
                    }
                }
            }
            else
                throw new JukeboxException("Playlist Empty");
        }
        return forReturnList;
    }
}
