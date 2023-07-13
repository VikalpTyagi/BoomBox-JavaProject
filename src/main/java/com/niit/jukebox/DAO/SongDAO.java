package com.niit.jukebox.DAO;

import com.niit.jukebox.model.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongDAO {

    public static boolean insertSong(Song song) throws SQLException {
        PreparedStatement insert= JukeboxConnection.getConnection()
                .prepareStatement("insert into songs(songName,artist_Name,genre,album,duration) values(?,?,?,?,?)");
        insert.setString(1,song.getSongName());
        insert.setString(2,song.getArtist_Name());
        insert.setString(3,song.getGenre());
        insert.setString(4,song.getAlbum());
        insert.setString(5,song.getDuration());
        int result= insert.executeUpdate();

        return result>0?true:false;
    }

    public static ArrayList<Song> allSongs() throws SQLException{
        ArrayList<Song> allSongs=null;
        Statement select=JukeboxConnection.getConnection().createStatement();
        ResultSet rs= select.executeQuery("select*from songs");
        if(rs.isBeforeFirst()){
            allSongs=new ArrayList<>();
            while(rs.next()){
                allSongs.add(new Song(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6) ));
            }
        }
        return allSongs;
    }
}
