package com.niit.jukebox.service;

import com.niit.jukebox.model.Song;

import java.sql.SQLException;
import java.util.*;

public class SongMain {



    public static void main(String[] args) {
        // Objects of classes
        Scanner sc = new Scanner(System.in);
        SongService songObj = new SongService();
        PlaylistService playlistObj = new PlaylistService();
        PlaylistContentService playlistContentObj = new PlaylistContentService();

        // ArrayList for songlist and playlist
        ArrayList<Song> songList ;
        Hashtable<String, Integer> playlist ;

        //fetch the songs and playlist
        int subLoop1=0;
        try {
            songList = songObj.getSongs();
            playlist = playlistObj.getAllPlaylist();

            do {
                //displaying song
                System.out.println("***************************************************************************\t"+PURPLE+"BOOM-BOX"+RESET+"\t***********************************************************************************");
                System.out.println("                                                                      \tForget the World\t\n");
                songObj.display(songList);

                //Calling main Menu
                System.out.println("\nSelect one option:\n----------------------\n 1. Song\n 2. Playlist\n 3. Player\n 4. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: {

                        do{
                        System.out.println("Select one option:\n----------------------\n 1. Add a Song\n 2. Search Song By Name\n 3. Search By Genre\n 4. Search Song by Artist\n 5. Search song by Album\n 6. Return to Menu\n 7. Exit");
                        int option = sc.nextInt();
                        switch (option) {
                            case 1: {
                                sc.nextLine();
                                System.out.println(CYAN+"Enter the song Name: "+RESET);
                                String songName = sc.nextLine();
                                System.out.println(CYAN+"Enter album Name: "+RESET);
                                String albumName = sc.nextLine();
                                System.out.println(CYAN+"Enter artist Name: "+RESET);
                                String artistName = sc.nextLine();
                                System.out.println(CYAN+"Enter Genre: "+RESET);
                                String genre = sc.nextLine();
                                System.out.println(CYAN+"Enter Duration: "+RESET);
                                String duration = sc.next();
                                Song songs = new Song(songName, artistName, genre, albumName, duration);
                                if(songObj.addSong(songs, songList)) {
                                    songList = songObj.getSongs();
                                    System.out.println(GREEN+"Song added successfully"+RESET);
                                    songObj.display(songList);
                                }
                                else throw new JukeboxException(RED+"Song not added"+RESET);
                                break;
                            }
                            case 2: {
                                System.out.println(CYAN+"Enter the song Name: "+RESET);
                                sc.nextLine();
                                String songName = sc.nextLine();
                                    Song song = songObj.getSongByName(songName, songList);
                                    if(song!=null)
                                    System.out.println(song);
                                    else throw new JukeboxException(RED+"Given name not found"+RESET);
                                break;
                            }
                            case 3: {
                                System.out.println(CYAN+"Enter Genre: "+RESET);
                                sc.nextLine();
                                String genre = sc.nextLine();
                                    ArrayList<Song> song = songObj.getSongByGenre(genre, songList);
                                    if (!song.isEmpty())
                                    for (Song list : song)
                                        System.out.println(list);
                                else throw new JukeboxException(RED+"Given Genre not found"+RESET);
                                break;
                            }
                            case 4: {
                                System.out.println(CYAN+"Enter artist Name: "+RESET);
                                sc.nextLine();
                                String artistName = sc.nextLine();
                                    ArrayList<Song> song = songObj.getSongByArtistName(artistName, songList);
                                    if(!song.isEmpty())
                                    for (Song list : song)
                                        System.out.println(list);
                                else throw new JukeboxException(RED+"Given Artist name not found"+RESET);
                                break;
                            }
                            case 5: {
                                System.out.println(CYAN+"Enter album Name: "+RESET);
                                sc.nextLine();
                                String albumName = sc.nextLine();
                                    ArrayList<Song> song = songObj.getSongByAlbum(albumName, songList);
                                    if(!song.isEmpty())
                                    for (Song list : song)
                                        System.out.println(list);
                                else throw new JukeboxException(RED+"Album name not found"+RESET);
                                break;
                            }
                            case 6: {
                                break;
                            }
                            case 7: {
                                System.out.println("******Exiting Application******");
                                System.exit(0);
                            }
                            default:
                                System.out.println(RED+"Please enter from given options only"+RESET);
                        }
                        System.out.println("\nPress :\n----------------------\n 1. to return to Main menu\n 2. to return to Song menu");
                        //System.out.println("------------------------------------------------------");
                        subLoop1= sc.nextInt();
                        }
                        while(subLoop1==2);
                        break;
                    }
                    case 2: {
                        do {
                            System.out.println("\nSelect one option:\n----------------------\n 1. show all playlist\n 2. create playlist\n 3. add song to playlist\n 4. add album to playlist\n 5. display playlist content\n 6. Return to Menu\n 7. exit");
                            int option = sc.nextInt();
                            switch (option) {
                                case 1: {
                                    Set<String> playlistNames = playlist.keySet();
                                    System.out.println(playlistNames);
                                    break;
                                }
                                case 2: {
                                    System.out.println(CYAN+"Enter playlist name"+RESET);
                                    sc.nextLine();
                                    String playlistName = sc.nextLine();
                                    if (playlistObj.addPlaylist(playlistName, playlist)) {
                                        System.out.println(GREEN+"Playlist added successfully"+RESET);
                                        playlistObj.getAllPlaylist();
                                        playlist = playlistObj.getAllPlaylist();
                                    }
                                    else throw new JukeboxException(RED+"Playlist not added"+RESET);
                                    break;
                                }
                                case 3: {
                                    System.out.println(CYAN+"Enter playlist name"+RESET);
                                    sc.nextLine();
                                    String playlistName = sc.nextLine();
                                    if(playlist.containsKey(playlistName)) {
                                        System.out.println(CYAN + "Enter song name" + RESET);
                                        String songName = sc.nextLine();
                                        if (playlistContentObj.addSongByName(songList, playlist, songName, playlistName))
                                            System.out.println(GREEN + "Added Successfully" + RESET);
                                    }
                                    else throw new JukeboxException(RED+"Playlist name is not in database"+RESET);
                                    break;
                                }
                                case 4: {
                                    System.out.println("Enter playlist name");
                                    sc.nextLine();
                                    String playlistName = sc.nextLine();
                                    System.out.println("Enter album name");
                                    String albumName = sc.nextLine();
                                    if (playlistContentObj.addSongByAlbumName(songList, playlist, albumName, playlistName))
                                        System.out.println(GREEN+"Added Successfully"+RESET);
                                    else System.out.println(RED+"Unsuccessful entry"+RESET);
                                    break;
                                }
                                case 5: {
                                    System.out.println(CYAN+"Enter playlist name"+RESET);
                                    sc.nextLine();
                                    String playlistName = sc.nextLine();
                                    if (playlist.containsKey(playlistName)) {
                                        ArrayList<Song> content = playlistContentObj.playlistContent(playlistName, playlist, songList);
                                    songObj.display(content);
                                }
                                else throw new JukeboxException(RED+"Playlist Name is not present in Database"+RESET);
                                    break;
                                }
                                case 6: {
                                    break;
                                }
                                case 7: {
                                    System.out.println("******Exiting Application******");
                                    System.exit(0);
                                }
                                default:
                                    throw new JukeboxException(RED+"Please choose from given options only"+RESET);
                            }
                            System.out.println("\nPress :\n----------------------\n 1. to return to Main menu\n 2. to return to Playlist menu");
                            //System.out.println("------------------------------------------------------");
                            subLoop1= sc.nextInt();
                        }
                        while (subLoop1==2);
                        break;
                    }
                    case 3: {
                        //PLAYER
                        MusicService audioPlayer = new MusicService();
                        do {
                            System.out.println(CYAN+"Enter playlist name"+RESET);
                            sc.nextLine();
                            String playlistName = sc.nextLine();
                            if (playlist.containsKey(playlistName)) {
                                ArrayList<Song> content = playlistContentObj.playlistContent(playlistName, playlist, songList);
                                if (!content.isEmpty())
                                    songObj.display(content);
                                ArrayList<Integer> songID = new ArrayList<>();
                                for(Song song:content)
                                    songID.add(song.getSongID());
                                int ID = 0;
                                int Mchoice;
                                    do {
                                        audioPlayer.playMusic(songID.get(ID));
                                        while (true) {
                                            System.out.println("1. "+PURPLE+"Pause"+RESET);
                                            System.out.println("2. "+PURPLE+"Resume"+RESET);
                                            System.out.println("3. "+PURPLE+"Restart"+RESET);
                                            System.out.println("4. "+PURPLE+"Next"+RESET);
                                            System.out.println("5. "+PURPLE+"Previous"+RESET);
                                            System.out.println("6. "+PURPLE+"Stop"+RESET);

                                            Mchoice = sc.nextInt();
                                            audioPlayer.gotoChoice(Mchoice);
                                            if (Mchoice == 4) {
                                                ID++;
                                                break;
                                            }
                                            if (Mchoice == 5) {
                                                --ID;
                                                break;
                                            }
                                            if (Mchoice == 6)
                                                break;
                                        }
                                    }
                                    while (Mchoice == 4 || Mchoice == 5);
                            } else throw new JukeboxException(RED+"Playlist Name is not present in Database"+RESET);
                            System.out.println("\nPress :\n----------------------\n 1. Return to main menu\n 2. Give another playlist");
                            subLoop1= sc.nextInt();
                        }
                        while (subLoop1==2);
                        break;
                    }
                    case 4: {
                        System.out.println("******Exiting Application******");
                        System.exit(0);
                        break;
                    }
                    default:
                        throw new JukeboxException(RED+"Please enter correct values"+RESET);
                }
                if(subLoop1!=1) {
                    System.out.println("\nPress :\n----------------------\n 1. Return to Main menu\n 2. Exit");
                    subLoop1 = sc.nextInt();
                }
            }
            while(subLoop1==1);
        }catch(IndexOutOfBoundsException e) {
            System.out.println(RED + "No More songs available in playlist" + RESET);
        }
        catch (SQLException e) {
            System.out.println("SQL- "+e.getMessage()+"\t"+e.getCause());
        }
         catch(JukeboxException e){
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e){
            System.out.println(RED+"Please enter numerical input only"+RESET);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }




    }
    public static final String RESET = "\u001B[0m";
    public static final String BLACK= "\u001B[30m";
    public static final String RED ="\u001B[31m";
    public static final String GREEN ="\u001B[32m";
    public static final String YELLOW ="\u001B[33m";
    public static final String BLUE ="\u001B[34m";
    public static final String PURPLE ="\u001B[35m";
    public static final String CYAN ="\u001B[36m";
}







/*     Scanner sc;

    //Main MENU
    void mainMenu(int choice) {
        sc = new Scanner(System.in);
        int option;
        switch (choice) {
            case 1: {
                System.out.println("Press: 1. to Add a Song, 2. to Search Song By Name, 3. to Search By Genre, 4. to Search Song by Artist, 5. to Search song by Album, 6, to return to main menu");
                option = sc.nextInt();
                songMenu(option);
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
        }
    }

    void songMenu(int choice) {
        sc = new Scanner(System.in);
        switch (choice) {
            case 1: {

                break;
            }
        }

    }
 */