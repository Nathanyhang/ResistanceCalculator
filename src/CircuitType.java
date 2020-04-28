public enum CircuitType {
	SERIES, PARALLEL;

	public static CircuitType getValue(String circuitType) {
		if (circuitType.equalsIgnoreCase("Series") || circuitType.equalsIgnoreCase("S")) {
			return SERIES;
		} else if (circuitType.equalsIgnoreCase("Parallel") || circuitType.equalsIgnoreCase("P")) {
			return PARALLEL;
		} else {
			return null;
		}
	}

	public static String getString(CircuitType circuitType) {
		if (circuitType.equals(CircuitType.PARALLEL)) {
			return "PARALLEL";
		} else if (circuitType.equals(CircuitType.SERIES)) {
			return "SERIES";
		} else {
			return null;
		}
	}

}
