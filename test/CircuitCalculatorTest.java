import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CircuitCalculatorTest {

	@Test
	@Ignore
	public void testEnum() {
		String input = "PARALLEL";
		CircuitType circuitType = CircuitType.PARALLEL;

		Assert.assertEquals(circuitType, CircuitType.valueOf(CircuitType.class, input));
	}

	@Test
//	@Ignore
	public void testCircuitClass() {
		Circuit c = new Parallel();
		Circuit d = new Series();
		List<Circuit> cList = new ArrayList<Circuit>();
		cList.add(c);
		cList.add(d);
		System.out.println(c instanceof Parallel);
		System.out.println(d instanceof Series);
		System.out.println(cList.get(cList.size()-1));
	}

	@Test
	@Ignore
	public void testEnumConversion() {

		Assert.assertEquals(CircuitType.PARALLEL, CircuitType.getValue("P"));
		Assert.assertEquals(CircuitType.SERIES, CircuitType.getValue("S"));
		Assert.assertEquals(CircuitType.SERIES, CircuitType.getValue("Series"));
		Assert.assertEquals(CircuitType.PARALLEL, CircuitType.getValue("Parallel"));
		Assert.assertNotEquals(CircuitType.SERIES, CircuitType.getValue("error"));
		Assert.assertEquals(CircuitType.PARALLEL, CircuitType.getValue("pArAlLeL"));
	}

	@Test 
	@Ignore
	public void testCircuitCalculationResistor() {
		CircuitCalculation resistorCalculator = new CircuitCalculation();
		CircuitFactory fetchCircuit = new CircuitFactory();
		Circuit circuitFragment;
		List<Load> loadList = new ArrayList<Load>();
		String userResponse = "P";
		int totalResistors = 2;

		for (int i = 1; i <= totalResistors; i++) {
			loadList.add(new Resistor(i, i * 3 + i));
		} // Add resistors to a list (4 ohms and 8 ohms)

		// Assuming user response is a "Parallel" Circuit, create a new parallel circuit
		circuitFragment = fetchCircuit.createCircuit(CircuitType.getValue(userResponse), LoadType.RESISTOR,loadList,1);

		// Calculate parallel circuit resistance
		Assert.assertTrue(circuitFragment instanceof Parallel);
		Assert.assertEquals(resistorCalculator.calculateEquivalenceValue(circuitFragment), 2.67, 0.5);
		Assert.assertEquals(loadList.get(0).getValue(), 4.0, 0.0);
		Assert.assertEquals(loadList.get(1).getValue(), 8.0, 0.0);

	}
	@Test 
	@Ignore
	public void testCircuitCalculationCapacitor() {
		CircuitCalculation capacitorCalculator = new CircuitCalculation();
		CircuitFactory fetchCircuit = new CircuitFactory();
		Circuit circuitFragment;
		List<Load> loadList = new ArrayList<Load>();
		String userResponse = "S";
		int totalCapacitors = 2;

		List<Circuit> circuitList= new ArrayList<Circuit>();
		
		for (int i = 1; i <= totalCapacitors; i++) {
			loadList.add(new Capacitor(i, i * 3 + i));
		} // Add resistors to a list (4 Farad and 8 Farad)

		// Assuming user response is a "Parallel" Circuit, create a new parallel circuit
		circuitFragment = fetchCircuit.createCircuit(CircuitType.getValue(userResponse),LoadType.CAPACITOR, loadList,2);
		
		//testing toString method
		circuitList.add(circuitFragment);
		System.out.println(circuitFragment);
		System.out.println(circuitList.get(0).getConnection());
		System.out.println(circuitList.get(0));
		System.out.println(circuitList.get(0).toString().equals("S2"));
		
		// Calculate parallel circuit resistance
		Assert.assertTrue(circuitFragment instanceof Series);
		Assert.assertEquals(capacitorCalculator.calculateEquivalenceValue(circuitFragment), 2.67, 0.5);
		//Assert.assertEquals(capacitorCalculator.calculateEquivalenceValue(circuitFragment), 12, 0.5);
		Assert.assertEquals(loadList.get(0).getValue(), 4.0, 0.0);
		Assert.assertEquals(loadList.get(1).getValue(), 8.0, 0.0);

	}

	@Test 
	@Ignore
	public void testLoadFactory() {
		List<Double> valueList = new ArrayList<>(List.of(1.0, 5.0, 3.0, 7.0, 5.0, 60.0, 7.0, 899.0, 9.0, 100.0));
		LoadFactory fetchResistor = new LoadFactory();
		List<Load> resistorList = new ArrayList<Load>();
		String userResponse="R";
		resistorList = fetchResistor.createLoad(LoadType.getValue(userResponse),valueList);
		
		for (Load r : resistorList) {
			System.out.println(r instanceof Resistor);
			System.out.println(r instanceof Inductor);
			System.out.println(r instanceof Capacitor);
			System.out.println("ID: " + r.getID() + ", value in ohms: " + r.getValue());
		}

	}
	@Test
	@Ignore
	public void testConsoleInputHandler() {
		ConsoleInputHandler consoleInput=new ConsoleInputHandler();
		String loadType = consoleInput.getLoadType();
		Map<String,Integer> loadInventory= consoleInput.getLoadInventory(loadType);
		List<Double> valueList = consoleInput.getLoadValues(loadInventory.get(loadType), loadType);

		
		for (Double i : valueList) {
			System.out.println(i);
		}
	}
	@Test
	public void testArray() {
		int[] a= {1,2,3};
		int b = a[a.length-1];
		Assert.assertEquals(3, b);
	}
}
