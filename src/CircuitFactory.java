import java.util.List;

public class CircuitFactory {
	private Circuit circuitFragment;

	private void connectLoad(List<Load> loadList) {
		for (Load r : loadList) {
			circuitFragment.addLoad(r);
		}
		circuitFragment.setConnection(loadList);
		
	}// helper method, to setup loads in circuits
	
	private void setEquivalence(Circuit circuit, LoadType loadType, int circuitID) {
		
		CircuitCalculation circuitCalculator = new CircuitCalculation();
		double equivalentValue = circuitCalculator.calculateEquivalenceValue(circuit);
		
		LoadFactory loadFactory = new LoadFactory();
		Load equivalentLoad = loadFactory.createLoad(loadType, circuitID, equivalentValue);
		equivalentLoad.setDisplayAsCustomTag(true);
		
		equivalentLoad.setCustomTag(circuit.getConnection());
		circuit.setEquivalentLoad(equivalentLoad);
		
	}//helper method, calculate equivalence value store in circuit

	public Circuit createCircuit(CircuitType circuitType, LoadType loadType, List<Load> loadList,int circuitID) {
		if (loadList == null) {
			return null;
		}
		switch (circuitType) {
		case SERIES:
			circuitFragment = new Series();
			break;
		case PARALLEL:
			circuitFragment = new Parallel();
			break;
		default:
			return null;
		}// End of Switch Case

		circuitFragment.setID(circuitID);
		connectLoad(loadList);
		setEquivalence(circuitFragment, loadType,circuitID + 100);
		return circuitFragment;
	}// End of createCircuit
}// End of CircuitFactory
