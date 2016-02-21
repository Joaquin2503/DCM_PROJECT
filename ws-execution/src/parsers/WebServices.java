package parsers;

import download.WebService;
import objects.Album;
import objects.Artist;
import objects.Song;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joaquin on 20/02/2016.
 */
public class WebServices {

    public HashMap<String, Artist> artists = new HashMap<String, Artist>();

    public void getArtistByNameWS(String s){
        String[] args = s.split(","); //
        WebService ws = WebServiceDescription
                .loadDescription("mb_getArtistInfoByName");
        String fileWithCallResult = ws.getCallResult(args[0]);
        System.out.println("The call is   **" + fileWithCallResult + "**");
        String fileWithTransfResults = null;
        ArrayList<String[]> listOfTupleResult = null;
        try {
            fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
            listOfTupleResult = ParseResultsForWS.showResults(
                    fileWithTransfResults, ws);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The tuple results are ");

        for (String[] tuple : listOfTupleResult) {
            if(!artists.containsKey(tuple[1])){
                Artist a = new Artist(tuple[0]);
                a.setId(tuple[1]);
                a.setBeginDate(tuple[2]);
                a.setEndDate(tuple[3]);
                artists.put(a.getId(), a);
            }
        }

    }

    public void getAlbumByArtistIdWS(String s){
        String[] args = s.split(","); //
        WebService ws = WebServiceDescription
                .loadDescription("mb_getAlbumByArtistId");
        String fileWithCallResult = ws.getCallResult(args[0]);
        System.out.println("The call is   **" + fileWithCallResult + "**");
        String fileWithTransfResults = null;
        ArrayList<String[]> listOfTupleResult = null;
        try {
            fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
            listOfTupleResult = ParseResultsForWS.showResults(
                    fileWithTransfResults, ws);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The tuple results are ");

        for (String[] tuple : listOfTupleResult) {
            Artist aux = artists.get(tuple[0]);
            if(aux!=null){
                if(!aux.getAlbums().contains(tuple[1])) aux.getAlbums().add(new Album(tuple[1]));
                artists.put(aux.getId(), aux);
            }
        }

    }

    public void getSongTitleByAlbumIdWS(String s){
        String[] args = s.split(","); //
        WebService ws = WebServiceDescription
                .loadDescription("mb_getSonTitleByAlbumId");
        String fileWithCallResult = ws.getCallResult(args[0]);
        System.out.println("The call is   **" + fileWithCallResult + "**");
        String fileWithTransfResults = null;
        ArrayList<String[]> listOfTupleResult = null;
        try {
            fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
            listOfTupleResult = ParseResultsForWS.showResults(
                    fileWithTransfResults, ws);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The tuple results are ");

        for (String[] tuple : listOfTupleResult) {
            Artist aux = artists.get(tuple[2]);
            if(aux!=null){
                Song song = new Song(tuple[1]);
                song.setAlbumId(tuple[0]);
                song.setArtistId(tuple[2]);
                if(song.getArtistId().equals(aux.getId())){
                    aux.getSongs().put(song.getSongName(),song );
                    Album album = aux.retrieveAlbum(song.getAlbumId());
                    if(album!=null){
                        album.getSongs().add(song);
                        aux.getSongs().put(song.getSongName(), song);
                        artists.put(aux.getId(), aux);
                    }else{
                       // Here this should be discarded
                    }
                }
            }
        }

    }
}
