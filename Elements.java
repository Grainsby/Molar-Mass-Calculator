
public class Elements {
	private String elementName;
	private double molarMass;
	public Elements(String name, double d) {
		elementName = name;
		molarMass = d;
	}
	
	public void setMolarMass(double mass) {
		this.molarMass = mass;
	}
	
	public double getMolarMass() {
		return molarMass;
		
	}
	
	public void setElementName(String name) {
		this.elementName = name;
	}
	
	public String getElementName() {
		return elementName;
	}
}
