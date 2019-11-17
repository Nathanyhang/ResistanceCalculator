import java.util.ArrayList;
import java.util.List;
public abstract class Circuit {
	
	protected double equivalentResistance = 0;
	protected List<Resistor> load = new ArrayList<Resistor>(); // either circuit fragments or independent resistors
	protected String connectionList="";

	public abstract void drawConnection(List<Resistor>resistorList);
	public abstract double calculateTotalResistance();
	
	public double getTotalResistance() {
		return equivalentResistance;
	}
	public String getConnectionList() {
		return connectionList;
	}

}
