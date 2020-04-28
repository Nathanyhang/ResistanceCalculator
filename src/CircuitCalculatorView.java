import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class CircuitCalculatorView {

	private InputHandler input;
	private PropertiesHandler displayProperties = new PropertiesHandler("SystemMessages.properties");
	private Properties p = displayProperties.setProperties();

	private String loadType;
	private Map<String, Integer> loadInventory;
	private List<Double> valueList;

	private LoadFactory loadFactory = new LoadFactory();
	private CircuitFactory circuitFactory = new CircuitFactory();

	private List<Load> loadList = new ArrayList<Load>();
	private List<Circuit> circuitList = new ArrayList<Circuit>();

	private CircuitCalculation circuitCalculation = new CircuitCalculation();

	private DecimalFormat decimalPlace = new DecimalFormat("#.####");// 4 dp. format

	public CircuitCalculatorView(String inputMethod) {
		input = (inputMethod.equalsIgnoreCase("Console")) ? new ConsoleInputHandler() : new FileInputHandler();
	}

	// create objects from factories, available to user
	public void buildLoadObjects() {
		loadType = input.getLoadType();
		loadInventory = input.getLoadInventory(this.loadType);
		valueList = input.getLoadValues(this.loadInventory.get(loadType), loadType);
		loadList = loadFactory.createLoad(LoadType.getValue(loadType), valueList);

		displayAllLoad();
	}

	public void buildCircuit() {
		String newConnection = input.getConnection(loadList, circuitList);

		// Re-obtain a valid connection due to connection id string
		if (newConnection == null || newConnection.equals("ERROR Not Connectable")) {
			System.err.println(p.getProperty("ConnectionResponseError"));
			newConnection = "ERROR";
		}
		// Re-obtain a valid connection due to connection type
		else if (newConnection.equals("ERROR Connection Type")) {
			System.err.println(p.getProperty("ConnectionTypeError"));
			newConnection = "ERROR";
		}

		// Recursively ask user to add circuit objects until they respond with "next"
		if (!newConnection.equals("END")) {

			// If connection is previously invalid, skip connecting item
			if (!newConnection.equals("ERROR")) {
				String[] responseList = null;
				List<Load> connectLoadList = new ArrayList<Load>();
				String circuitType = null;

				// Take user response, get requested loads and circuits, connect
				responseList = newConnection.split(" ");
				circuitType = responseList[responseList.length - 1];
				for (String s : responseList) {
					if (!s.equals(circuitType)) {
						// add the corresponding load from loadList
						for (Load l : loadList) {
							if (l.toString().equals(s)) {
								connectLoadList.add(l);
							}
						}
						// add the equivalent load from circuitList
						for (Circuit c : circuitList) {
							if (c.toString().equals(s)) {
								connectLoadList.add(c.getEquivalentLoad());
							}
						}
					}
				}

				// Create the new circuit object
				circuitList.add(circuitFactory.createCircuit(CircuitType.getValue(circuitType),
						LoadType.getValue(loadType), connectLoadList, circuitList.size()));

				displayAllCircuit();
			}
			// Continue to get more connections
			buildCircuit();
		}
	}

	// default display error
	private void displayError() {
		System.out.println(p.getProperty("NoDisplayError"));
	}

	// display items, available to user
	public void displayLoadType() {
		if (loadType == null) {
			displayError();
			return;
		}
		System.out.println(loadType);

	}

	public void displayLoadInventory() {
		if (loadInventory != null) {
			displayError();
			return;
		}
		for (Entry<String, Integer> kv : loadInventory.entrySet()) {
			System.out.println(kv);
		}
	}

	public void displayLoadValues() {
		if (valueList != null) {
			displayError();
			return;
		}
		for (Double i : valueList) {
			System.out.println(i);
		}

	}

	// display load based on id
	public void displayLoad(String loadID) {
		for (Load l : loadList) {
			if (l.toString().equals(loadID)) {
				System.out.println(l + " = " + Double.toString(l.getValue()) + " " + l.getUnits());
			}
		}
	}

	public void displayAllLoad() {
		// TODO: add property title
		if (loadList == null) {
			displayError();
			return;
		}
		System.out.println(p.getProperty("ListLoad"));
		for (Load l : loadList) {
			System.out.println(l + " = " + Double.toString(l.getValue()) + " " + l.getUnits());
		}
	}

	public void displayCircuit(String circuitID) {
		for (Circuit c : circuitList) {
			if (c.toString().equals(circuitID)) {
				System.out.println(c + " = " + c.getConnection() + " = " + Double.toString(c.getEquivalentLoad().getValue())
						+ " " + c.getEquivalentLoad().getUnits());
			}
		}
	}

	public void displayAllCircuit() {
		// TODO: add property title
		if (circuitList == null) {
			displayError();
			return;
		}
		System.out.println(p.getProperty("ListCircuit"));
		for (Circuit c : circuitList) {
			System.out.println(c + " = " + c.getConnection() + " = " + decimalPlace.format(c.getEquivalentLoad().getValue())
					+ " " + c.getEquivalentLoad().getUnits());
		}

	}

	public void displayEquivalence() {
		Circuit equivalentCircuit = circuitList.get(circuitList.size() - 1);
		System.out.println(loadType.charAt(0) + "_eq = " + equivalentCircuit.getConnection() + " = " + decimalPlace.format(equivalentCircuit.getEquivalentLoad().getValue())
		+ " " + equivalentCircuit.getEquivalentLoad().getUnits());
	}

	public void displaySelectedResults(boolean displayAllLoad, boolean displayAllCircuit, boolean displayEquivalence) {
		System.out.println(p.getProperty("SectionBreak") + p.getProperty("LoadCalculationResults"));
		if (displayAllLoad) {
			displayAllLoad();
		}
		if (displayAllCircuit) {
			displayAllCircuit();
		}
		if (displayEquivalence) {
			displayEquivalence();
		}
	}

}
