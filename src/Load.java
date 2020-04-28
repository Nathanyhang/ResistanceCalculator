
public abstract class Load {
	protected char loadTag;
	protected double loadValue;
	protected int loadID;
	protected static String units;  //Units cannot change
	protected boolean displayAsCustomTag;
	protected String customTag;
	
	public abstract String getUnits();
	
	public void setID(int loadID) {
		this.loadID=loadID;
	}
	public void setValue(double loadValue) {
		this.loadValue=loadValue;
	}

	public void setDisplayAsCustomTag (boolean displayAsCustomTag) {
		this.displayAsCustomTag=displayAsCustomTag;
	}
	public void setCustomTag(String customTag) {
		this.customTag = customTag;
	}
	public int getID() {
		return loadID;
	}
	public double getValue() {
		return loadValue;
	}
	public boolean getDisplayMethod() {
		return displayAsCustomTag;
	}

	public String toString() {
		return displayAsCustomTag? customTag: this.getClass().getName().charAt(0) + Integer.toString(loadID);
	}
}
