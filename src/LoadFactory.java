import java.util.ArrayList;
import java.util.List;

public class LoadFactory {

	public List<Load> createLoad(LoadType loadType, List<Double> valueList) {// Return a new list of Loads

		if (valueList == null) {
			return null;
		}
		List<Load> loadList = new ArrayList<Load>();
		int totalLoad = 0;

		for (double i : valueList) {
			loadList.add(createLoad(loadType, ++totalLoad, i));// ID,value
		}

		return loadList;

	}

	public Load createLoad(LoadType loadType, int ID, double value) {// return a single Load

		Load load;

		switch (loadType) {
		case RESISTOR:
			load = new Resistor(ID, value);
			break;
		case CAPACITOR:
			load = new Capacitor(ID, value);
			break;
		case INDUCTOR:
			load = new Inductor(ID, value);
			break;
		default:
			return null;
		}
		return load;

	}

}
