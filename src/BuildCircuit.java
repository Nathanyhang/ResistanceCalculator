import java.util.List;

public class BuildCircuit {
	protected Parallel parallelCircuit = new Parallel();
	protected Series seriesCircuit= new Series();
	protected String connectionDiagram="";
	
	
	//* BuildCircuit will combine all circuit fragments together
	public void createParallelCircuit(List<Resistor> resistorList) {
		for (Resistor r:resistorList) {
			parallelCircuit.addParallelCircuit(r);
		}
		parallelCircuit.drawConnection(resistorList);
	}
	public void createSeriesCircuit(List<Resistor> resistorList) {
		for (Resistor r:resistorList) {
			seriesCircuit.addSeriesCircuit(r);
		}
		seriesCircuit.drawConnection(resistorList);
	}
	public double getParallelResistance() {
		return parallelCircuit.calculateTotalResistance();
	}
	public double getSeriesResistance() {
		return seriesCircuit.calculateTotalResistance();
	}
	public String getCircuitConnection(String circuitType) {
		connectionDiagram=circuitType=="Parallel"?parallelCircuit.getConnectionList()
				:seriesCircuit.getConnectionList();
		return connectionDiagram;
	}
	
}