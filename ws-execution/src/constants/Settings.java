package constants;

public class Settings {

	public static final String rootProject="C:\\Users\\joaqu\\Documents\\WS-Evaluation\\";
	///cal/homes/jmelgarejo/Téléchargements/WWW
	public static final String dirWithDef=rootProject+"ws-definitions/";
	
	
	public static final String getDirForCallResults(String ws){
		return rootProject+ws+"/call_results/";
	}
	
	public static final String getDirForTransformationResults(String ws){
		return rootProject+ws+"/transf_results/";
	}
	
	
	
}
