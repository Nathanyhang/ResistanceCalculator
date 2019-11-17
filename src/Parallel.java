import java.util.List;
import java.util.ArrayList;

public class Parallel extends Circuit {

	private List<Resistor> parallelResistance =new ArrayList<Resistor>();

	// new subroutines
	public void addParallelCircuit(Resistor r){
		parallelResistance.add(r);
	}//add the circuit object

	//Parent Class subroutines
	public void drawConnection(List<Resistor>resistorList) {
		String circuitFragment="";

		for(Resistor r: resistorList) {
			circuitFragment+="R"+ r.getResistorID();
			if (resistorList.get(resistorList.size()-1) !=r) {
				circuitFragment+="//";
			}
		}//labels how the resistors are connected in the circuit
		connectionList="(" + circuitFragment + ")";
	}
	public double calculateTotalResistance() {
		double tempResistance=0;
		
		for(Resistor r : parallelResistance) { // loop through all resistors in the circuit fragment, calculate equivalent resistance
			tempResistance+= 1/r.getResistance();
		}
		tempResistance=1/tempResistance;
		
		equivalentResistance+=tempResistance;
		return equivalentResistance;
	}
}
