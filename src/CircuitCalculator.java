import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CircuitCalculator {

	public static void main(String[] args) {
	
		CircuitCalculatorView calcView= new CircuitCalculatorView("Console");

		calcView.buildLoadObjects();
		calcView.buildCircuit();
		calcView.displaySelectedResults(true,true,true);
	}

}
