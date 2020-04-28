import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
//@Controller: methods to get user input, and sends input to various classes

	private Scanner scan = new Scanner(System.in);
	private PropertiesHandler displayProperties = new PropertiesHandler("SystemMessages.properties");
	private Properties p = displayProperties.setProperties();

	private int getInteger(String helpMessage, String errorMessage, boolean suppressHelpMessage) {
		if (!suppressHelpMessage) {
			System.out.println(helpMessage);
		}
		int integer;

		try {
			integer = scan.nextInt();
		} catch (InputMismatchException | NumberFormatException e) {
			System.err.println(errorMessage);
			scan.nextLine();// clear buffer
			return getInteger(helpMessage, errorMessage, suppressHelpMessage);
		}
		return integer;
	}

	private String getString(String helpMessage, boolean suppressHelpMessage, boolean isLine) {
		if (!suppressHelpMessage) {
			System.out.println(helpMessage);
		}
		String string;
		if (isLine) { // get line
			string = scan.nextLine();
			string=string.toUpperCase();
		} else { // get word
			string = scan.next().toUpperCase();
			scan.nextLine();// clear buffer
		}
		return string;
	}

	public String getLoadType() {// Capacitor, Inductor, Resistor, or mixed?
		String loadType = getString(p.getProperty("GetLoadType"), false,false);

		if (LoadType.getValue(loadType) != null) {// If user selects unique load
			loadType = LoadType.getString(LoadType.getValue(loadType));
			return loadType;
		}

		switch (loadType) { // If user wants to mix between the various load types
		case "MIXED":
		case "M":
		case "VARIOUS":
		case "V":
			loadType = "VARIOUS";

			// TODO: Current implementation: Various load types are disabled.
			System.err.println(p.getProperty("LoadTypeError") + p.getProperty("VariousTypeNotSupported"));
			return getLoadType();
		// return loadType;
		default: // get user input again if user did not specify the possible options
			System.err.println(p.getProperty("LoadTypeError"));
			return getLoadType();
		}
	}

	// Get total load of single type (no inventory map)
	public int getTotalLoad(String loadType) {

		displayProperties.editProperty(p, "GetTotalLoad", "LOAD", loadType);
		int totalLoad = getInteger(p.getProperty("GetTotalLoad"), p.getProperty("ValueError"), false);

		// revert property changes
		p = displayProperties.setProperties();
		return totalLoad;
	}

	// Create a map to hold an inventory count of all loads
	public Map<String, Integer> getLoadInventory(String loadType) {

		Map<String, Integer> loadInventory = new HashMap<>();
		int numberOfLoadItem;

		// Get single load total
		if ((LoadType.getValue(loadType) != null)) {
			numberOfLoadItem = getTotalLoad(loadType.toLowerCase());
			loadInventory.put(loadType, numberOfLoadItem);

			// TODO: Get multiple loads total, disabled for this implementation release
		} else if (loadType == "VARIOUS") {
			// Get resistor total
			numberOfLoadItem = getTotalLoad(LoadType.getString(LoadType.RESISTOR).toLowerCase());
			loadInventory.put(LoadType.getString(LoadType.RESISTOR), numberOfLoadItem);
			// Get capacitor total
			numberOfLoadItem = getTotalLoad(LoadType.getString(LoadType.CAPACITOR).toLowerCase());
			loadInventory.put(LoadType.getString(LoadType.CAPACITOR), numberOfLoadItem);
			// Get inductor total
			numberOfLoadItem = getTotalLoad(LoadType.getString(LoadType.INDUCTOR).toLowerCase());
			loadInventory.put(LoadType.getString(LoadType.INDUCTOR), numberOfLoadItem);

			loadInventory.put("TOTAL",
					loadInventory.get(LoadType.getString(LoadType.RESISTOR))
							+ loadInventory.get(LoadType.getString(LoadType.CAPACITOR))
							+ loadInventory.get(LoadType.getString(LoadType.INDUCTOR)));
		}

		return loadInventory;
	}

	// Get a list of values for specific type of load if not mapped
	public List<Double> getLoadValues(int totalLoad, String loadType) {

		List<Double> loadValueList = new ArrayList<Double>();
		double addValue;

		// resistance, inductance, capacitance
		displayProperties.editProperty(p, "GetLoadValue", "LOADVALUE", loadType.toLowerCase());
		displayProperties.editProperty(p, "GetLoadValue", "tor", "tance");
		// total # of loads
		displayProperties.editProperty(p, "GetLoadValue", "TOTALLOAD", Integer.toString(totalLoad));
		// replace load with proper load object
		displayProperties.editProperty(p, "GetLoadValue", "LOAD", loadType.toLowerCase());
		// replace L with R/I/C
		displayProperties.editProperty(p, "GetLoadValue", "L", Character.toString(loadType.charAt(0)));
		// replace with specified load units
		displayProperties.editProperty(p, "GetLoadValue", "UNITS",
				loadType != LoadType.getString(LoadType.INDUCTOR) ? (LoadType.getUnits(loadType) + "s")
						: (LoadType.getUnits(loadType).replace("y", "ies")));

		for (double i = 0; i < totalLoad; i++) {
			addValue = getInteger(p.getProperty("GetLoadValue"), p.getProperty("ValueError"), i != 0);
			loadValueList.add(addValue);
		}

		// revert property changes
		p = displayProperties.setProperties();

		return loadValueList;
	}

	public String getConnection(List<Load> loadList, List<Circuit> circuitList) {
		scan = new Scanner(System.in); //clear buffer
		String connectionList = null;
		String circuitType;
		String[] checkItemsArray = null;
		boolean isConnectable = true;
		List<String> loadIDList = new ArrayList<String>();
		List<String> circuitIDList = new ArrayList<String>();
		List<String> combinedIDList = new ArrayList<String>();

		// Store current IDs of all loads and circuits
		for (Load l : loadList) {
			loadIDList.add(l.toString());
		}
		for (Circuit c : circuitList) {
			circuitIDList.add(c.toString());
		}
		combinedIDList.addAll(loadIDList);
		combinedIDList.addAll(circuitIDList);

		// Get connection method and determine if the specified loads/circuits exists
		connectionList = getString(p.getProperty("GetCircuitConnection"), !circuitIDList.isEmpty(),true);
		
		// If connectionList is empty
		if (connectionList.isBlank()) {
			return null;
		}

		// If no more connections are needed by the user
		if (connectionList.equalsIgnoreCase("END")) {
			return connectionList;
		}

		checkItemsArray = connectionList.split(" ");
				
		
		// If the specified circuit type is invalid (not parallel or series)
		if (CircuitType.getValue(checkItemsArray[checkItemsArray.length - 1]) == null) {
			return "ERROR Connection Type";
		}
		// If there are no load items/valid items to check (needs at least two items and circuitType)
		if(checkItemsArray.length<3) {
			isConnectable=false;
		}

		// Store circuit type
		circuitType = checkItemsArray[checkItemsArray.length - 1];
		// Check for valid Load item
		for (String ID : checkItemsArray) {

			if(!ID.equalsIgnoreCase(circuitType)) {
				isConnectable = (combinedIDList.contains(ID)) && isConnectable;
			}
		}
		return isConnectable ? connectionList : "ERROR Not Connectable";

	}

}
