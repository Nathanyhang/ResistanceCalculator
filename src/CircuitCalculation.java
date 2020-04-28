
public class CircuitCalculation {

	private Circuit circuitFragment;
	private double equivalentValue;
	private boolean inverseFlag;
	private boolean containsResistor;
	private boolean containsCapacitor;
	private boolean containsInductor;
	private boolean uniqueLoadList;

	public CircuitCalculation() {
		equivalentValue = 0;
		uniqueLoadList=true;
	}

	private double sumAsInverse(double input) {

		return (inverseFlag) ? 1 / input : input;
	}

	private void calculationMethod() {
		for (Load load : circuitFragment.getLoad()) {
			// Load types contained in circuit
			containsResistor = (load instanceof Resistor) || containsResistor;
			containsCapacitor = (load instanceof Capacitor) || containsCapacitor;
			containsInductor = (load instanceof Inductor) || containsInductor;
		}

		// For a single load type, determine whether the equivalent values are inversely calculated
		uniqueLoadList = (containsResistor ^ containsCapacitor ^ containsInductor);

		inverseFlag = ((containsCapacitor) && (circuitFragment instanceof Series) && (uniqueLoadList))||
				((!containsCapacitor) && (circuitFragment instanceof Parallel) && (uniqueLoadList));
	}
	public boolean isUnique() {
		return uniqueLoadList;
	}

	public double calculateEquivalenceValue(Circuit circuitFragment) {

		this.circuitFragment = circuitFragment;
		calculationMethod();

		if (!uniqueLoadList && (circuitFragment == null)) {
			return 0;
		}

		// Loop all resistors in the circuit fragment, calculate equivalent resistance
		for (Load load : circuitFragment.getLoad()) {
			equivalentValue += sumAsInverse(load.getValue());
		}
		equivalentValue = sumAsInverse(equivalentValue);
		return equivalentValue;

	}

	// TODO: Future implementation (various mode is currently disabled)
	public double calculateImpedance() {
		return circuitFragment == null?0:1;
	}
}
