import download.WebService;
import objects.Album;
import objects.Artist;
import objects.Song;
import parsers.ParseResultsForWS;
import parsers.WebServiceDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joaquin on 20/02/2016.
 */
public class WebServices {

    public static HashMap<String, Artist> artists = new HashMap<String, Artist>();

    public static void main(String[] args){
        //getArtistByName(?name,?artistId,?bDate,?eDate)
        // +getAlbumByArtistId(?artistId,?albumId)
        // +getSongTitleByAlbumId(?albumId,?songTitle,?artistId)
        boolean EXIT = true;
        if(args.length==0)System.out.println("Error: No input parameters.");
        else if(args.length>1)System.out.println("Error: please dont use any spaces, if needed, separate input parameters with \'-\'");
        else EXIT = false;
        if(EXIT) System.exit(-1);
        String[] ws = args[0].split("\\+");
        if(ws.length!=3) {System.out.println("Error: problem with n° of ws"); System.exit(-1);}
        String s;
        // WEBSERVICE 1
        if((s = extractBrackets(ws[0])) != null){
            getArtistByNameWS(s);
        }else {
            System.out.println("Error: problem with getArtistByName"); System.exit(-1);
        }

        // WEBSERVICE 2
        if((s = extractBrackets(ws[1])) != null){
            getAlbumByArtistIdWS(s);
        }else {
            System.out.println("Error: problem with getAlbumByArtistId"); System.exit(-1);
        }

        // WEBSERVICE 3
        if((s = extractBrackets(ws[0])) != null){
            getSongTitleByAlbumIdWS(s);
        }else {
            System.out.println("Error: problem with getSongByAlbumId"); System.exit(-1);
        }

    }

    private static String extractBrackets(String arg){
        String inside = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(inside);
        Matcher matcher = pattern.matcher(arg);
        String s = matcher.group(0);
        return s;
    }

    public static void getArtistByNameWS(String s){
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

    public static void getAlbumByArtistIdWS(String s){
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

    public static void getSongTitleByAlbumIdWS(String s){
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
