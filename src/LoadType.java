public enum LoadType {
	RESISTOR, CAPACITOR, INDUCTOR;

	public static LoadType getValue(String loadType) {
		if (loadType.equalsIgnoreCase("Capacitor") || loadType.equalsIgnoreCase("C")) {
			return CAPACITOR;
		} else if (loadType.equalsIgnoreCase("Inductor") || loadType.equalsIgnoreCase("I")) {
			return INDUCTOR;
		} else if (loadType.equalsIgnoreCase("Resistor") || loadType.equalsIgnoreCase("R")) {
			return RESISTOR;
		} else {
			return null;
		}
	}

	public static LoadType getLoadClass(Load load) {
		if (load instanceof Resistor) {
			return RESISTOR;
		} else if (load instanceof Capacitor) {
			return CAPACITOR;
		} else if (load instanceof Inductor) {
			return INDUCTOR;
		} else {
			return null;
		}
	}

	public static String getString(LoadType loadType) {
		if (loadType.equals(LoadType.RESISTOR)) {
			return "RESISTOR";
		} else if (loadType.equals(LoadType.CAPACITOR)) {
			return "CAPACITOR";
		} else if (loadType.equals(LoadType.INDUCTOR)) {
			return "INDUCTOR";
		} else {
			return null;
		}
	}

	public static String getUnits(LoadType loadType) {
		if (loadType.equals(LoadType.RESISTOR)) {
			return "Ohm";
		} else if (loadType.equals(LoadType.CAPACITOR)) {
			return "Farad";
		} else if (loadType.equals(LoadType.INDUCTOR)) {
			return "Henry";
		} else {
			return null;
		}
	}

	public static String getUnits(String loadType) {
		if (loadType.equals(getString(LoadType.RESISTOR))) {
			return "Ohm";
		} else if (loadType.equals(getString(LoadType.CAPACITOR))) {
			return "Farad";
		} else if (loadType.equals(getString(LoadType.INDUCTOR))) {
			return "Henry";
		} else {
			return null;
		}
	}

}