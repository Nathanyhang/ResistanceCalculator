import java.util.ArrayList;
import java.util.List;

public abstract class Circuit {

	protected List<Load> loadList = new ArrayList<Load>(); // either circuit fragments or independent resistors
	protected Load equivalentLoad;
	protected String connection;
	protected String symbol;
	protected int circuitID;

	public void addLoad(Load r) {
		loadList.add(r);
	}// add the load object

	public List<Load> getLoad() {
		return loadList;
	}// return list of loads

	public void setID(int circuitID) {
		this.circuitID = circuitID;
	}

	public void setConnection(List<Load> loadList) {
		String circuitFragment = "";

		for (Load load : loadList) {
			circuitFragment += load;// invokes the toString method
			// if this is not the last load element, proceed to add the symbol
			if (loadList.get(loadList.size() - 1) != load) {
				circuitFragment += symbol;
			}
		} // labels how the resistors are connected in the circuit
		connection = "(" + circuitFragment + ")";
	}
	public void setConnection(Circuit c) {
		
		
	}

	public int getID() {
		return circuitID;
	}

	public String getConnection() {
		return connection;
	}

	public void setEquivalentLoad(Load load) {
		this.equivalentLoad = load;
	}

	public Load getEquivalentLoad() {
		return equivalentLoad;
	}

}
