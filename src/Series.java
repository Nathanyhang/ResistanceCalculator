public class Series extends Circuit {

	public Series() {
		symbol=" + ";
	}
	
	public String toString() {
		return CircuitType.getString(CircuitType.SERIES).charAt(0) + Integer.toString(circuitID);
	}
}
