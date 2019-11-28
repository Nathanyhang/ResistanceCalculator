import java.util.List;

public class BuildCircuit {
	private Parallel parallelCircuit = new Parallel();
	private Series seriesCircuit= new Series();
	private String connectionDiagram="";
	
	
	//* BuildCircuit will combine all circuit fragments together
	public void createParallelCircuit(List<Resistor> resistorList) {
		for (Resistor r:resistorList) {
			parallelCircuit.addLoad(r);
		}
		parallelCircuit.setConnection(resistorList);
	}
	public void createSeriesCircuit(List<Resistor> resistorList) {
		for (Resistor r:resistorList) {
			seriesCircuit.addLoad(r);
		}
		seriesCircuit.setConnection(resistorList);
	}
	public double getParallelResistance() {
		return parallelCircuit.calculateTotalResistance();
	}
	public double getSeriesResistance() {
		return seriesCircuit.calculateTotalResistance();
	}
	public String getCircuitConnection(String circuitType) {
		connectionDiagram=circuitType=="Parallel"?parallelCircuit.getConnection()
				:seriesCircuit.getConnection();
		return connectionDiagram;
	}
	
}