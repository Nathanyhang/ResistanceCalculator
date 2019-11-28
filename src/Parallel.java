
public class Parallel extends Circuit {

	public Parallel() {
		symbol="//";
	}

	protected double getCircuitValue(double input) {
		return 1/input;
	}
}
