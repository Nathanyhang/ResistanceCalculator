
public class Resistor extends Load {

	public Resistor() {
		super();
		units = getUnits();
	}

	public Resistor(int resistorID, double resistance) {
		loadID = resistorID;
		loadValue = resistance;
		units = getUnits();
	}

	public String getUnits() {
		return (loadValue == 1) ? LoadType.getUnits(LoadType.RESISTOR) : LoadType.getUnits(LoadType.RESISTOR) + "s";
	}

}
