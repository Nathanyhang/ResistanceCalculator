import java.util.List;

public class Series extends Circuit {

	// new subroutines
	public void addSeriesCircuit(Resistor r){
		loadList.add(r);
	}//add the circuit object

	//Parent Class subroutines
	public void setConnection(List<Resistor>resistorList) {
		String circuitFragment="";

		for(Resistor r: resistorList) {
			circuitFragment+="R"+ r.getResistorID();
			if (resistorList.get(resistorList.size()-1) !=r) {
				circuitFragment+=" + ";
			}
		}//labels how the resistors are connected in the circuit
		connection="(" + circuitFragment + ")";
	}

	public double calculateTotalResistance() {
		for(Resistor r : loadList) { // loop through all resistors in the circuit fragment, calculate equivalent resistance
			equivalentResistance+=r.getResistance();
		}
		return equivalentResistance;
	}

}
