package objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaquin on 20/02/2016.
 */
public class Album {

    private String albumId;
    private String artistId;

    private ArrayList<Song> songs;

    public Album(String albumName){
        this.albumId = albumName;
        songs = new ArrayList<Song>();
    }

    /**Interface functions**/

    public void  setAlbumId(String s) {this.albumId = s;}

    public void setArtistId(String s) {this.artistId = s;}

    public String getAlbumId() { return this.albumId;}

    public String getArtistId() {return this.artistId;}

    /**List functions**/

    public ArrayList<Song> getSongs() {return this.songs;}

}
