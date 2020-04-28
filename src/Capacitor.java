public class Capacitor extends Load {

	public Capacitor() {
		super();
		units = getUnits();
	}

	public Capacitor(int capacitorID, double capacitance) {
		loadID = capacitorID;
		loadValue = capacitance;
		units = getUnits();
	}

	public String getUnits() {
		return (loadValue == 1) ? LoadType.getUnits(LoadType.CAPACITOR) : LoadType.getUnits(LoadType.CAPACITOR) + "s";
	}

}
