package com.niit.jukebox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class PlaylistDAO {

    public static boolean createPlaylist(String playlistName) throws SQLException {
        PreparedStatement insert = JukeboxConnection.getConnection()
                .prepareStatement("insert into playlist(playlistName) values (?)");
        insert.setString(1, playlistName);
        int result = insert.executeUpdate();

        return result > 0 ? true : false;
    }

    public static Hashtable<String, Integer> viewAllPlaylist() throws SQLException
    {
        Hashtable<String, Integer> playlist = new Hashtable();
        Statement display=JukeboxConnection.getConnection().createStatement();
        ResultSet rs= display.executeQuery("select*from playlist");
        while (rs.next()) {
            playlist.put(rs.getString(2), rs.getInt(1));
        }
        return playlist;
    }
}
