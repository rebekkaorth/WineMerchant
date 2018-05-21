
public class CustomerAccount {
	private String name;
	private int currentBalance;
	private final double serviceCharge = 0.2; 
	private double totalAmountOfTransaction; 
	
	//constructor of CustomerAccount
	public CustomerAccount(String name, double currentBalance) {
		this.name = name;
		this.currentBalance = (int) Math.round(currentBalance*100); 
	}
	
	// getter for CustomerAccount
	public String getName() {
		return name;
	}
	public double getCurrentBalance() {   //reasoning why balance is returned as a double - and not changed in the controller - or returned as a string -> in REPORT
		return ((double) currentBalance/100);
	}
	
	//calculating transaction amount after sale 
	public double calculateTotalAmountOfTransactionAfterSale (Wine newTransaction) {
		totalAmountOfTransaction = newTransaction.getPricePerBottle() * newTransaction.getQuantity();
		currentBalance += (int) Math.round(totalAmountOfTransaction*100);  //changing currentBalance accordingly to transaction amount
		 return totalAmountOfTransaction;
	}
	
	//calculating transaction amount after return 
	public double calculateTotalAmountOfTransactionAfterReturn (Wine newTransaction) { 
		totalAmountOfTransaction = (newTransaction.getPricePerBottle() * newTransaction.getQuantity()) * (1-serviceCharge);
		currentBalance -= (int) Math.round(totalAmountOfTransaction*100); //changing currentBalance accordingly to transaction amount
		return totalAmountOfTransaction; 
	}
	
}
