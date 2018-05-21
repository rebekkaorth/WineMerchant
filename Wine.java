
public class Wine {
	private String nameOfWine; 
	private double pricePerBottle; 
	private int quantity; 
	
	//constructor of the Wine class - getting parameters values from the user input
	public Wine(String nameOfWine, double pricePerBottle, int quantity) {
		this.nameOfWine = nameOfWine;
		this.pricePerBottle = pricePerBottle; 
		this.quantity = quantity; 
	}
	//getter of Wine class
	public String getNameOfWine() {  
		return nameOfWine;
	}
	public double getPricePerBottle() {
		return pricePerBottle;
	}
	public int getQuantity() {
		return quantity;
	}
	
}
