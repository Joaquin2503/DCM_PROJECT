package parsers;

import java.util.ArrayList;

import download.WebService;

public class MyParser {

	public static void callGetSongByAlbumId(StringBuilder albumId, StringBuilder songId, StringBuilder songTitle)
			throws Exception {
		WebService ws = WebServiceDescription.loadDescription("mb_getSongTitleByAlbumId");
		String fileWithCallResult = ws.getCallResult(albumId.toString());
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);

		for (String[] tuple : listOfTupleResult) { // Do the thing here
			System.out.println(tuple[0] + "|" + tuple[2]);// tuple[0] = songId,
															// tuple[2] =
			// title
		}

	}

	public static void callGetAlbumByArtistId(StringBuilder artistId) throws Exception {
		WebService ws = WebServiceDescription.loadDescription("mb_getAlbumByArtistId");
		String fileWithCallResult = ws.getCallResult(artistId.toString());
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);
		for (String[] tuple : listOfTupleResult) {
			StringBuilder s = new StringBuilder();
			s.append(tuple[1]);
			System.out.println(tuple[1] + "|" + tuple[2]);
		}
	}

	public static void callGetArtistInfoByName(StringBuilder artist) throws Exception {
		WebService ws = WebServiceDescription.loadDescription("mb_getArtistInfoByName");
		String fileWithCallResult = ws.getCallResult(artist.toString());
		// System.out.println("The call is **" + fileWithCallResult + "**");
		// System.out.println(fileWithCallResult);
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);
		boolean found = false;
		// System.out.println("The tuple results are ");
		for (String[] tuple : listOfTupleResult) {
			// if (tuple[0].equals(artist.toString())) {
			System.out.println(" " + tuple[0] + "|" + tuple[1]);
			StringBuilder s = new StringBuilder();
			s.append(tuple[1]);
			// callGetAlbumByArtistId(s);
		}
	}

	

}
