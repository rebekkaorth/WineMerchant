/* This program takes a customer name and an initial balance from two dialog boxes of a wine merchant. 
 * The GUI enables the user to perform sale and return operation on different orders of the wine merchant. 
 * The amount of transaction as well as the account balance is updated according the performed operation. 
*/
import javax.swing.JOptionPane;

public class AssEx1 {
	public static void main (String [] args) { 		
		
		//initialising used variables for JOptionPanes
		String customerName = "";
		double currentBalance = 0; 
		
		//first dialog box - asking for customer name
		customerName =	JOptionPane.showInputDialog("Enter customer name", ""); //input name of customer
			if (customerName == null|| customerName.isEmpty()) {
				System.exit(0);
			}
				
		//second dialog box asking for initial/ current balance 
			while (true) {
				try {
					currentBalance = Double.parseDouble(JOptionPane.showInputDialog("Enter initial balance", ""));
					break; 
				}				
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "please enter a number or correct formatting", "Wrong input", JOptionPane.ERROR_MESSAGE);
				}	
				catch (NullPointerException e) {
					System.exit(0);
				}
			}	
			
			//creating CustomerAccount object with user input as parameters
			CustomerAccount  newCustomer = new CustomerAccount(customerName, currentBalance);
			
			//create LWMGUI object with CustomerAccount object above
			LWMGUI gui = new LWMGUI (newCustomer); 
			//make GUI visible to user
			gui.setVisible(true); 
		} 	
}
