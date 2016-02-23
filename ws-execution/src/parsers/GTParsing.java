package parsers;

import java.util.ArrayList;

import download.WebService;

public class GTParsing {
	private static void callGetSongByAlbumId(String albumId) throws Exception{
		WebService ws = WebServiceDescription.loadDescription("mb_getSongTitleByAlbumId");
		String fileWithCallResult = ws.getCallResult(albumId);
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);

		for (String[] tuple : listOfTupleResult) { //Do the thing here
			System.out.println(tuple[2]);// tuple[0] = songId, tuple[2] = title
		}		

	}
	
	private static void callGetAlbumByArtistId(String artistId) throws Exception{
		WebService ws = WebServiceDescription.loadDescription("mb_getAlbumByArtistId");
		String fileWithCallResult = ws.getCallResult(artistId);
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);
		for (String[] tuple : listOfTupleResult) { //Do the thing here /tuple[1] = albumId
			System.out.println("*" + tuple[2]);
			callGetSongByAlbumId(tuple[1]);
		}		
	}
	
	
	public static void callGetArtistInfoByName(String artist) throws Exception{
		WebService ws = WebServiceDescription.loadDescription("mb_getArtistInfoByName");
		String fileWithCallResult = ws.getCallResult(artist);
//		System.out.println("The call is   **" + fileWithCallResult + "**");
//		System.out.println(fileWithCallResult);
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws);
		boolean found = false;
//		System.out.println("The tuple results are ");
		for (String[] tuple : listOfTupleResult) { //Do the thing here
			if (tuple[0].equals(artist))
			{
				callGetAlbumByArtistId(tuple[1]);
				found = true;
			}
			//			System.out.print("( ");
//			for (String t : tuple) {
//				System.out.print(t + ", ");
//			}
//			System.out.print(") ");
//			System.out.println();
		}
		if (!found)
		{
			System.out.println("Artist not found, check the spelling");
		}
	}
	
}
