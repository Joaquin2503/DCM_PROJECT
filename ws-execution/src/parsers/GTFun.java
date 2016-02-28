package parsers;

import java.util.ArrayList;

import download.WebService;

public class GTFun {



	private static void callGetSongByAlbumId(StringBuilder albumId) throws Exception {
		WebService ws = WebServiceDescription.loadDescription("mb_getSongTitleByAlbumId");
		String fileWithCallResult = ws.getCallResult(albumId.toString());
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);

		for (String[] tuple : listOfTupleResult) { // Do the thing here
			System.out.println("\t" + tuple[2]);// tuple[0] = songId, tuple[2] =
												// title
		}

	}

	private static void callGetAlbumByArtistId(StringBuilder artistId) throws Exception {
		WebService ws = WebServiceDescription.loadDescription("mb_getAlbumByArtistId");
		String fileWithCallResult = ws.getCallResult(artistId.toString());
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);
		for (String[] tuple : listOfTupleResult) { // Do the thing here
													// /tuple[1] = albumId
			StringBuilder s = new StringBuilder();
			s.append(tuple[1]);
			System.out.println(tuple[2]);
			callGetSongByAlbumId(s);
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
		for (String[] tuple : listOfTupleResult) { // Do the thing here
			if (tuple[0].equals(artist.toString())) {
				System.out.println("aaaaaaaaaaa " + tuple[1]);
				StringBuilder s = new StringBuilder();
				s.append(tuple[1]);
				callGetAlbumByArtistId(s);
				found = true;
			}
			// System.out.print("( ");
			// for (String t : tuple) {
			// System.out.print(t + ", ");
			// }
			// System.out.print(") ");
			// System.out.println();
		}
		if (!found) {
			System.out.println("Artist not found, check the spelling");
		}
	}

}
