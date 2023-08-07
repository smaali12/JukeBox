package com.niit.jukebox.Implementation;
import com.niit.jukebox.dao.PlaylistDao;
import com.niit.jukebox.model.Songs;
import com.niit.jukebox.service.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

public class JukeBoxMain
{
    public static void main(String[] args) throws SQLException, jukeBoxException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Welcome To Jukebox >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        SongsService songsService = new SongsService();
        PlaylistService playlistService = new PlaylistService();
        PlaylistContentService playlistContentService = new PlaylistContentService();
        PlayersServices playersServices = new PlayersServices();
        ArrayList<Songs> songlist;
        Hashtable<String, Integer> playlistHashtable;

        try {
            songlist = songsService.getAllSongs();
            playlistHashtable = playlistService.getAllPlaylist();
            songsService.displaySongs(songlist);
            int choice;
            int repeat;
            do {
                repeat = 0;
                System.out.println("Enter \n1  SONG\n2  PLAYLIST\n3  PLAYERS\n4  EXIT");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter\n1) Add a Song\n2) Search a song By Song Name\n3) Search Song by Geners\n4) Search Song By Artist\n5) Search Song  By Album Name\n6) Go to Main Menue\n7) Exit");
                        int option;
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("Enter song Name :- ");
                                String songName = scanner.nextLine();
                                System.out.println("Enter Album Name :-  ");
                                String albumName = scanner.nextLine();
                                System.out.println("Enter Artist Name :-  ");
                                String artistName = scanner.nextLine();
                                System.out.println("Enter Gener  :-  ");
                                String geners = scanner.nextLine();
                                System.out.println("Enter song Duration MM:SS :-  ");
                                String songDuration = scanner.nextLine();

                                Songs songs = new Songs(songName, albumName, artistName, geners, songDuration);
                                boolean result = songsService.addSongs(songs, songlist);
                                if (result = true) {
                                    songlist = songsService.getAllSongs();
                                    songsService.displaySongs(songlist);
                                } else {
                                    System.out.println("Song not added");
                                }
                                break;
                            case 2:
                                System.out.println("Enter song Name for searching the song  ");
                                scanner.nextLine();
                                String song_Name = scanner.nextLine();
                                Songs search_song = songsService.getASongBySongName(song_Name, songlist);
                                if (search_song != null) {
                                    System.out.println(search_song);
                                }
                                else
                                {
                                    System.out.println("Song with " + song_Name + "is not present");
                                }
                                break;
                            case 3:
                                System.out.println("Enter song gener for searching the song ");
                                scanner.nextLine();
                                String songGener = scanner.nextLine();
                                ArrayList<Songs> songs2 = songsService.getSongsByGeners(songGener, songlist);
                                if (!songs2.isEmpty())
                                {
                                    songsService.displaySongs(songs2);
                                }
                                else
                                {
                                    System.out.println("Song with " + songGener + " is not present");
                                }
                                break;
                            case 4:
                                System.out.println("Enter the Artist name for searching the song ");
                                scanner.nextLine();
                                String artis_tName = scanner.nextLine();
                                ArrayList<Songs> songs3 = songsService.getSongsByArtistName(artis_tName, songlist);
                                if (!songs3.isEmpty()) {
                                    songsService.displaySongs(songs3);
                                } else
                                {
                                    System.out.println("Song with " + artis_tName + "is not present");
                                }
                                break;
                            case 5:
                                System.out.println("Enter the Album name for searching the song ");
                                scanner.nextLine();
                                String album_Name = scanner.nextLine();
                                ArrayList<Songs> songs4 = songsService.getSongsByAlbumName(album_Name, songlist);
                                if (!songs4.isEmpty()) {
                                    songsService.displaySongs(songs4);
                                } else {
                                    System.out.println("Song with " + album_Name + "is not present");
                                }
                                break;
                            case 6:
                                System.out.println("Running to main ");
                                repeat = 1;
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Enter correct option as per instruction.");
                        }
                        break;
                    case 2:

                        System.out.println("Enter \n1) Show All Playlist \n2) Create Playlist\n3) Add Song To Playlist\n4) Add  Album To Playlist\n5) Display Playlist Content\n6) Back to Main Menu\n7) for Exit");
                        int choose;
                        choose = scanner.nextInt();
                        switch (choose) {
                            case 1:
                                Set<String> getkeysValuesFromtHashtable = playlistHashtable.keySet();
                                Iterator<String> interatorOfSet = getkeysValuesFromtHashtable.iterator();
                                while (interatorOfSet.hasNext()) {
                                    System.out.println(interatorOfSet.next());
                                }
                                break;
                            case 2:
                                scanner.nextLine();
                                System.out.println("Enter the playlist name You want to add");
                                String playListName = scanner.nextLine();
                                boolean result = playlistService.addToPlaylist(playListName, playlistHashtable);
                                if (result) {
                                    System.out.println("Playlist added successfully");
                                    System.out.println(playlistService.getAllPlaylist());
                                } else {
                                    System.out.println("Playlist is already present");
                                }
                                break;
                            case 3:
                                scanner.nextLine();
                                System.out.println("Enter the playlist name ");
                                String playListName1 = scanner.nextLine();
                                System.out.println("Enter the song name");
                                String songName = scanner.nextLine();
                                boolean conclusion = playlistContentService.addSongBySongName(songlist, playlistHashtable, songName, playListName1);
                                if (conclusion = true) {
                                    System.out.println(songName + "Song added successfully to " + playListName1);
                                } else {
                                    System.out.println("Song not added to playlist ");
                                }
                                break;
                            case 4:
                                scanner.nextLine();
                                System.out.println("Enter the playlist name ");
                                String nameOfplaylist = scanner.nextLine();
                                System.out.println("Enter the album name");
                                String albumName = scanner.nextLine();
                                boolean result1 = playlistContentService.addSongByAlbumName(songlist, playlistHashtable, albumName, nameOfplaylist);
                                if (result1 = true) {
                                    System.out.println(albumName + "Album  added successfully to " + nameOfplaylist);

                                }else{
                                    System.out.println("Album  not added to playlist ");
                                }
                                break;
                            case 5:
                                scanner.nextLine();
                                System.out.println("Enter the playlist name ");
                                String enterPlaylistname = scanner.nextLine();
                                ArrayList<Songs> playListContent = playlistContentService.playlistContent(enterPlaylistname, playlistHashtable, songlist);
                                songsService.displaySongs(playListContent);
                                break;
                            case 6:
                                System.out.println("Running to main ");
                                repeat = 1;
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Enter correct option as per instruction.");
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("Enter\n 1 Play Song By Playlist Name\n 2 Play Song By Song Name\n 3 Main Menu\n 4 Exit");

                               scanner.nextLine();
                               int chooseOne=0;
                               chooseOne=scanner.nextInt();
                               switch (chooseOne){
                                   case 1:
                                       scanner.nextLine();
                                       System.out.println("Enter the playlist name");
                                        String enterPlaylistname = scanner.nextLine();
                                       ArrayList<Integer> storeSongId = new ArrayList<>();
                                      ArrayList<Songs> songsArrayList = playlistContentService.playlistContent(enterPlaylistname, playlistHashtable, songlist);
                                       songsService.displaySongs(songsArrayList);
//
                                Iterator<Songs> songsIterator = songsArrayList.iterator();
                                while (songsIterator.hasNext()) {
                                    storeSongId.add(songsIterator.next().getSong_id());
                                }
                                int songId = 0;
                                int choiceForPlay;
                                try {
                                    do {
                                        playersServices.playSong(storeSongId.get(songId));
                                        while (true)
                                        {
                                            System.out.println("1)PREVIOUS\n 2)PAUSE\n 3)RESUME\n 4)NEXT\n 5)RESTART\n 6)STOP");
                                            choiceForPlay = scanner.nextInt();
                                            playersServices.gotoChoiceForPlaylist(choiceForPlay);
                                            if (choiceForPlay == 1) {
                                                --songId;
                                                break;
                                            }
                                            if (choiceForPlay == 4) {
                                                songId++;
                                                break;
                                            }
                                            if (choiceForPlay == 6)
                                                break;
                                        }
                                    } while (choiceForPlay == 1 || choiceForPlay == 4);
                                } catch (IndexOutOfBoundsException e)
                                {
                                    System.out.println("No previous or more songs in Playlist");
                                } catch (UnsupportedAudioFileException e) {
                                    System.out.println(e.getMessage());
                                } catch (LineUnavailableException e) {
                                    System.out.println(e.getMessage());
                                    ;
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }

                        break;
                            case 2:
                                scanner.nextLine();
                                System.out.println("Enter song name to play song ");
                                String nameOfSongForPlaying = scanner.nextLine();
                                Songs songIdStore = songsService.getASongBySongName(nameOfSongForPlaying, songlist);
                                playersServices.playSong(songIdStore.getSong_id());

                                while (true)
                                {
                                    System.out.println("1)PAUSE\n 2)RESUME\n 3)RESTART\n 4)STOP");
                                    int chooseOption = scanner.nextInt();
                                    playersServices.gotoChoice(chooseOption);
                                    if (chooseOption == 4)
                                        break;
                                }

                            case 3:
                                System.out.println("Returning to Main Menu");
                                repeat = 1;
                                break;
                            case 4:
                                System.out.println(" THANK YOU");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Please type correct Option");
                                break;
                        }
                            break;

                            case 4:

                                System.out.println("-------------------*** Thank you ***---------------------");
                                System.exit(0);
                                break;

                        }
                        if (repeat == 0) {
                            System.out.println(" do you want more service the type 1 or for exit type 0");
                            repeat = scanner.nextInt();
                        }


            }
                while (repeat == 1) ;

            }catch(jukeBoxException e){
                System.err.println(e.getMessage());
            } catch (SQLIntegrityConstraintViolationException e){
                System.out.println("Song Already present in Playlist");
//            } catch(SQLException e){
//                System.out.println("Already present");
            }catch (InputMismatchException e)
        {
            System.out.println("Enter the proper input");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
    }
