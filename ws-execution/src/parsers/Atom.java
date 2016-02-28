package parsers;

import java.util.ArrayList;

public class Atom {
	private String wsCalled;
	private ArrayList<String> params = new ArrayList<String>();
	
	public Atom(String nameWS, String[] params2) {
		for (String param : params2) {
			params.add(param);
		}
		wsCalled = nameWS;
	}

	public String toString(){
		String ret = wsCalled;
		ret += "(";
		for (String p : params) {
			ret += p + ", ";
		}
		ret = ret.substring(0, ret.length()-2);
		ret += ")";
		return ret;
	}
	public Atom() {
		// TODO Auto-generated constructor stub
	}

	public Atom(String string) {
		String parts[] = string.split("\\(");
		String nameWS = "";
		nameWS = parts[0].split("bf+")[0];
		String[] params = parts[1].split("\\)")[0].split(", ?");
		this.setParams(params);
		this.setWsCalled(nameWS);
	}

	private void setParams(String[] params2) {
		params.clear();
		for (String param : params2) {
			params.add(param);
		}
	}

	public String getWsCalled() {
		return wsCalled;
	}
	public void setWsCalled(String wsCalled) {
		this.wsCalled = wsCalled;
	}
	public ArrayList<String> getParams() {
		return params;
	}
	public void setParams(ArrayList<String> params) {
		this.params = params;
	}
}
