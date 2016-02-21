package objects;

/**
 * Created by Joaquin on 20/02/2016.
 */
public class Song {

    private String songName;
    private String artistId;
    private String albumId;

    public Song(String songname){ // CHECK IF BETTER ID
        this.songName = songname;
    }

    /**Interface functions**/

    public void setSongName(String s){this.songName = s;}

    public void setArtistId(String s) { this.artistId = s;}

    public void setAlbumId(String s) {this.albumId = s;}

    public String getSongName() {return this.songName;}

    public String getArtistId() { return this.artistId;}

    public String getAlbumId() { return this.albumId;}
}
