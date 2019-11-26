import java.util.ArrayList;
import java.util.List;
public abstract class Circuit {
	
	protected double equivalentResistance = 0;
	protected List<Resistor> loadList = new ArrayList<Resistor>(); // either circuit fragments or independent resistors
	protected String connection="";

	public abstract void setConnection(List<Resistor>resistorList);
	public abstract double calculateTotalResistance();
	
	public double getTotalResistance() {
		return equivalentResistance;
	}
	public String getConnection() {
		return connection;
	}

}
