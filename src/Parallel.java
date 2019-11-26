import java.util.List;

public class Parallel extends Circuit {

	// new subroutines
	public void addParallelCircuit(Resistor r){
		loadList.add(r);
	}//add the circuit object

	//Parent Class subroutines
	public void setConnection(List<Resistor>resistorList) {
		String circuitFragment="";

		for(Resistor r: resistorList) {
			circuitFragment+="R"+ r.getResistorID();
			if (resistorList.get(resistorList.size()-1) !=r) {
				circuitFragment+="//";
			}
		}//labels how the resistors are connected in the circuit
		connection="(" + circuitFragment + ")";
	}
	public double calculateTotalResistance() {
		double tempResistance=0;
		
		for(Resistor r : loadList) { // loop through all resistors in the circuit fragment, calculate equivalent resistance
			tempResistance+= 1/r.getResistance();
		}
		tempResistance=1/tempResistance;
		
		equivalentResistance+=tempResistance;
		return equivalentResistance;
	}
}
