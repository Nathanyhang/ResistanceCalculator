public class Inductor extends Load {

	public Inductor() {
		super();
		units = getUnits();
	}

	public Inductor(int inductorID, double inductance) {
		loadID = inductorID;
		loadValue = inductance;
		units = getUnits();
	}

	public String getUnits() {
		return (loadValue == 1) ? LoadType.getUnits(LoadType.INDUCTOR)
				: LoadType.getUnits(LoadType.INDUCTOR).replace("y", "ies");
	}

}