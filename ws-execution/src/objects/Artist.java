package objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joaquin on 20/02/2016.
 */
public class Artist {

    private String name;
    private String id;
    private String beginDate;
    private String endDate;

    private ArrayList<Album> albums;
    private HashMap<String, Song> songs;

    public Artist(String _name){
        this.name = _name;
        this.albums = new ArrayList<Album>();
        this.songs = new HashMap<String, Song>();
    }

    /**Interface functions**/
    public void setName(String s){this.name = s;}

    public void setId(String s){this.id = s;}

    public void setBeginDate(String s){this.beginDate = s;}

    public void setEndDate(String s){this.endDate = s;}

    public String getName(){ return this.name;}

    public String getId() {return this.id;}

    public String getBeginDate(){return this.beginDate;}

    public String getEndDate(){ return this.endDate;}

    /**HashMap funtions**/

    public HashMap<String, Song> getSongs(){ return this.songs;}

    public Album retrieveAlbum(String albumId){
        Album aux = null;
        for(Album a : this.albums){
            if(a.getAlbumId().equals(albumId)) aux = a;
        }
        return aux;
    }

    public ArrayList<Album> getAlbums() {return this.albums;}
}
