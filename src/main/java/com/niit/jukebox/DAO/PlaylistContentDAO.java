package com.niit.jukebox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistContentDAO {

    public static boolean addPlaylist(int songID,int playlistID)throws SQLException{
        PreparedStatement add=JukeboxConnection.getConnection()
                .prepareStatement("insert into playlistContent(playlistID,songID) values(?,?)");
        add.setInt(1,playlistID);
        add.setInt(2,songID);
        int result=add.executeUpdate();

        return result>0?true:false;
    }

    public static ArrayList<Integer> viewSongInPlaylist(int playlistID)throws SQLException
    {
        ArrayList<Integer> songNumber=new ArrayList<>();
        PreparedStatement song=JukeboxConnection.getConnection()
                .prepareStatement("select * from playlistContent where playlistID=(?)");
        song.setInt(1,playlistID);
        ResultSet rs= song.executeQuery();
            while(rs.next())
                songNumber.add(rs.getInt(2));
        return songNumber;
    }
}
