public class Parallel extends Circuit {

	public Parallel() {
		symbol="//";
	}

	public String toString() {
		return CircuitType.getString(CircuitType.PARALLEL).charAt(0) + Integer.toString(circuitID);
	}
	
}
