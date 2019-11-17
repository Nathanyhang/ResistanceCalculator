
public class Resistor {

	protected double resistance;
	protected int resistorID;
	
	public void setResistorID(int index) {
		resistorID=index;
	}
	public void setResistance(double value) {
		resistance=value;
	}
	public double getResistance() {
		return resistance;
	}
	public int getResistorID() {
		return resistorID;
	}
}
