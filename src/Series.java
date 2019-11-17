import java.util.ArrayList;
import java.util.List;

public class Series extends Circuit {

	private ArrayList<Resistor> seriesResistance =new ArrayList<Resistor>();


	// new subroutines
	public void addSeriesCircuit(Resistor r){
		seriesResistance.add(r);
	}//add the circuit object

	//Parent Class subroutines
	public void drawConnection(List<Resistor>resistorList) {
		String circuitFragment="";

		for(Resistor r: resistorList) {
			circuitFragment+="R"+ r.getResistorID();
			if (resistorList.get(resistorList.size()-1) !=r) {
				circuitFragment+=" + ";
			}
		}//labels how the resistors are connected in the circuit
		connectionList="(" + circuitFragment + ")";
	}

	public double calculateTotalResistance() {
		for(Resistor r : seriesResistance) { // loop through all resistors in the circuit fragment, calculate equivalent resistance
			equivalentResistance+=r.getResistance();
		}
		return equivalentResistance;
	}

}
