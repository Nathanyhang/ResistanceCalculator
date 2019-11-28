import java.util.ArrayList;
import java.util.List;

public abstract class Circuit {
	
	protected double equivalentResistance;
	protected List<Resistor> loadList = new ArrayList<Resistor>(); // either circuit fragments or independent resistors
	protected String connection;
	protected String symbol;
	
	protected abstract double getCircuitValue(double input);
	
	public void addLoad(Resistor r){
		loadList.add(r);
	}//add the circuit object
	
	public void setConnection(List<Resistor>resistorList) {
		String circuitFragment="";

		for(Resistor r: resistorList) {
			circuitFragment+="R"+ r.getResistorID();
			if (resistorList.get(resistorList.size()-1) !=r) {
				circuitFragment+=symbol;
			}
		}//labels how the resistors are connected in the circuit
		connection="(" + circuitFragment + ")";
	}
	
	
	public double getTotalResistance() {
		return equivalentResistance;
	}
	public String getConnection() {
		return connection;
	}
	
	public double calculateTotalResistance() {
		equivalentResistance=0;
		for(Resistor r : loadList) { // loop through all resistors in the circuit fragment, calculate equivalent resistance
			equivalentResistance+= getCircuitValue(r.getResistance());
		}
		equivalentResistance=getCircuitValue(equivalentResistance);
		return equivalentResistance;
	}
}
