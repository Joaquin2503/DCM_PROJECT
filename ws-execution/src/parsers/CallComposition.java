package parsers;

import java.util.ArrayList;

import download.WebService;

public class CallComposition {
	private Atom leftHand = new Atom();
	private ArrayList<Atom> rightHand = new ArrayList<Atom>();

	public CallComposition() {

	}

	public void execute() throws Exception {
		ArrayList<ArrayList<String[]>> outputs = new ArrayList<ArrayList<String[]>>();
		for (Atom atom : rightHand) {
			String ws = atom.getWsCalled();
			ArrayList<String> params = atom.getParams();
			String mainVal = params.remove(0);
			System.out.println(mainVal);
			outputs.add(genericCall("mb_" + ws, mainVal, params));
		}
	}

	private static ArrayList<String[]> genericCall(String desc, String mainVal, ArrayList<String> params)
			throws Exception {
		WebService ws = WebServiceDescription.loadDescription(desc);
		String fileWithCallResult = ws.getCallResult(mainVal);
		String fileWithTransfResults = ws.getTransformationResult(fileWithCallResult);
		ArrayList<String[]> listOfTupleResult = ParseResultsForWS.showResults(fileWithTransfResults, ws, params);
		for (String[] tuple : listOfTupleResult) {
			for (String t : tuple) {
				System.out.print(t + ", ");
			}
			System.out.println();
		}
		return listOfTupleResult;
	}

	public CallComposition(Atom lh, ArrayList<Atom> rh) {
		leftHand = lh;
		rightHand.addAll(rh);
	}

	public CallComposition(String str) {
		String parts[] = str.split(" ?<- ?");
		Atom lh = new Atom(parts[0]);
		ArrayList<Atom> atoms = new ArrayList<Atom>();
		// System.out.println(parts[1]);
		for (String atom : parts[1].split("\\) ?,")) {
			Atom a = new Atom(atom.trim());
			atoms.add(a);
		}
		this.setLeftHand(lh);
		this.setRightHand(atoms);
	}

	public String toString() {
		String str = leftHand.toString() + " <- ";
		for (Atom atom : rightHand) {
			str += atom.toString() + ", ";
		}
		str = str.substring(0, str.length() - 2);
		return str;
	}

	public Atom getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(Atom leftHand) {
		this.leftHand = leftHand;
	}

	public ArrayList<Atom> getRightHand() {
		return rightHand;
	}

	public void setRightHand(ArrayList<Atom> rightHand) {
		this.rightHand.clear();
		this.rightHand.addAll(rightHand);
	}

}
