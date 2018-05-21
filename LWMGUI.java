import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/* 
 * This class contains the view as well as the controller of user interface 
 */
public class LWMGUI extends JFrame implements ActionListener {  // WARNING ??? - What does that mean?
	//user interface components set as instance variables of the class
	private JPanel middlePanel, bottomPanel; 
	private JButton saleButton, returnButton;
	private JLabel nameOfWineLabel, quantityLabel, pricePerBottleLabel,nameOfWineSelectedIntroductionLabel, nameOfWineSelectedLabel, amountOfTransactionLabel, currentBalanceLabel; 
	private JTextField nameOfWineTextField, quantityTextField, pricePerBottleTextField, amountOfTransactionTextField, currentBalanceTextField;
	
	private CustomerAccount customer; 
	//constructor of the LWMGUI class to add components to the frame
	public LWMGUI(CustomerAccount customer) {
		this.customer = customer; 
		setDefaultCloseOperation(EXIT_ON_CLOSE); //program closes when user hits x
		setTitle("Lilybank Wine Merchants: " + customer.getName());  //title of GUI window
		setSize(1200, 200); //size of the GUI window 		
		//adding layout components 
		layoutComponents();
	}	
	private void layoutComponents () {
		//add middle and bottom layout components
		layoutMiddlePanel ();
		layoutBottomPanel ();
	}	
	private void layoutMiddlePanel () {
		//create panel for middle section for user input (name of wine, quantity, price per bottle) + buttons for purchase = return
		middlePanel = new JPanel (); 		

		JPanel middlePanelNorth = new JPanel();
		//add label and text field for input of the name of the wine
		nameOfWineLabel = new JLabel ("Name of wine:");
		middlePanelNorth.add(nameOfWineLabel); 
		nameOfWineTextField = new JTextField (20); 
		nameOfWineTextField.addActionListener(this);
		middlePanelNorth.add(nameOfWineTextField); 
		//add label and text field for input of quantity to be purchased or returned 
		quantityLabel = new JLabel ("Quantity:"); 
		middlePanelNorth.add(quantityLabel); 
		quantityTextField = new  JTextField (20);
		quantityTextField.addActionListener(this);
		middlePanelNorth.add(quantityTextField); 
		// add label and text field for input of price per bottle 
		pricePerBottleLabel = new JLabel ("Price per bottle: £");
		middlePanelNorth.add(pricePerBottleLabel);
		pricePerBottleTextField = new JTextField (20); 
		pricePerBottleTextField.addActionListener(this);
		middlePanelNorth.add(pricePerBottleTextField); 
		
		JPanel middlePanelCenter = new JPanel ();
		// add buttons for purchase and return 
		saleButton = new JButton("Process Sale");
		saleButton.addActionListener(this);
		middlePanelCenter.add(saleButton);
		returnButton = new JButton("Process Return"); 
		returnButton.addActionListener(this);
		middlePanelCenter.add(returnButton); 		
		
		//create new panel to show name of wine purchased/ returned after buttons above were pressed
		JPanel middlePanelSouth = new JPanel (); 
		nameOfWineSelectedIntroductionLabel = new JLabel (""); //"purchased/ return depending which button was pressed
		middlePanelSouth.add(nameOfWineSelectedIntroductionLabel);
		nameOfWineSelectedLabel = new JLabel (""); //later replaced with nameOfWine input 
		middlePanelSouth.add(nameOfWineSelectedLabel); 		
		//add middlePanelCenter and middlePanelSoutch to middlePanel
		middlePanel.add(middlePanelNorth, BorderLayout.NORTH);
		middlePanel.add(middlePanelCenter, BorderLayout.CENTER); 
		middlePanel.add(middlePanelSouth, BorderLayout.SOUTH);		
		//add middlePanel to layoutComponents 
		add(middlePanel, BorderLayout.CENTER);		
	}
	
	private void layoutBottomPanel () {
		//create panel for bottom section displaying the current transaction amount and the current balance of the customer 
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.LIGHT_GRAY);
		//add label and text field for displaying current transaction account 
		amountOfTransactionLabel = new JLabel("Amount of transaction:"); 
		bottomPanel.add(amountOfTransactionLabel);
		amountOfTransactionTextField = new JTextField (20); 
		bottomPanel.add(amountOfTransactionTextField); 
		amountOfTransactionTextField.setEditable(false);
		//add label and text field for displaying current customer account balance 
		currentBalanceLabel = new JLabel("Current account balance:");
		bottomPanel.add(currentBalanceLabel);
		currentBalanceTextField = new JTextField(20); 
		setBalanceWithCheckOfCredit();
		bottomPanel.add(currentBalanceTextField); 
		currentBalanceTextField.setEditable(false);
		//add bottomPanel to layoutComponents 
		add(bottomPanel, BorderLayout.SOUTH); 		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saleButton) { 	
			amountOfTransactionTextField.setText("");  
			try {
				if (Integer.parseInt(quantityTextField.getText()) < 1 || Double.parseDouble(pricePerBottleTextField.getText()) < 0) { //the quantity cannot be smaller than 1 or price per bottle smaller than 0
					JOptionPane.showMessageDialog(null, "your quantity and/ or your price per bottle is too small", "input error", JOptionPane.ERROR_MESSAGE); //sending error message with warning of wrong input
				}
				else if (nameOfWineTextField.getText().equals("")){  //when user did not provide name of wine			
					JOptionPane.showMessageDialog(null, "please enter name of wine", "input error", JOptionPane.ERROR_MESSAGE); //sending error message with warning of wrong input					
				} 
				else {		
					amountOfTransactionTextField.setText(String.format("£ %.2f",customer.calculateTotalAmountOfTransactionAfterSale(this.transactionPlaced()))); //output transaction amount by calling Customer Account method to calculate transaction amount
					nameOfWineSelectedIntroductionLabel.setText("Wine purchased: " + nameOfWineTextField.getText());
					}
				}
					catch (NumberFormatException ex) {  //thrown when at least one string is empty 
							JOptionPane.showMessageDialog(null, "at least one input field is empty or has an incorrect input", "input error", JOptionPane.ERROR_MESSAGE);  //warning user about at least one empty string
							nameOfWineSelectedIntroductionLabel.setText("");
					}			
			setBalanceWithCheckOfCredit();  //setting the new balance 
			clearTransactionInputTextFields();
			
		} else if (e.getSource() == returnButton) {			
			amountOfTransactionTextField.setText("");
			try {
				if (Integer.parseInt(quantityTextField.getText()) < 1 || Double.parseDouble(pricePerBottleTextField.getText()) < 0) { //the quantity cannot be smaller than 1 or price per bottle smaller than 0
					JOptionPane.showMessageDialog(null, "your quantity and/ or your price per bottle is too small", "input error", JOptionPane.ERROR_MESSAGE);  //sending error message with warning of wrong input
					quantityTextField.setText("");
				}
				else if (nameOfWineTextField.getText().equals("")) { //when user did not provide name of wine
					JOptionPane.showMessageDialog(null, "please enter name of wine", "input error", JOptionPane.ERROR_MESSAGE); //sending error message with warning of wrong input
					pricePerBottleTextField.setText("");
				} else {		
					amountOfTransactionTextField.setText(String.format("£ %.2f",customer.calculateTotalAmountOfTransactionAfterReturn(this.transactionPlaced())));   //output transaction amount by calling Customer Account method to calculate transaction amount
					nameOfWineSelectedIntroductionLabel.setText("Wine returned: " + nameOfWineTextField.getText());
					}
				}
					catch (NumberFormatException ex) {  //thrown when at least one string is empty 
							JOptionPane.showMessageDialog(null, "at least one input field is empty or has an incorrect input", "input error", JOptionPane.ERROR_MESSAGE);  //sending error message with warning of no input
							nameOfWineSelectedIntroductionLabel.setText("");
							clearTransactionInputTextFields(); 
					}
			setBalanceWithCheckOfCredit(); //setting the new balance 
			clearTransactionInputTextFields();			
		}
	}
	
	//getting user input from text fields and create a new Wine object with this input as parameters 
	private Wine transactionPlaced () { 
		//creating variables that can be used as parameters for the Wine object 
		String nameOfWine = nameOfWineTextField.getText();
		double pricePerBottle = Double.parseDouble(pricePerBottleTextField.getText());
		int quantity = Integer.parseInt(quantityTextField.getText());
		//creating a Wine object parsing the needed parameters 
		Wine newTransaction = new Wine(nameOfWine, pricePerBottle, quantity); //new Wine Object created
		return newTransaction; //Wine object returned 
	}	
	//checking if customer balance is negative/ positive to add CR accordingly 
	private void setBalanceWithCheckOfCredit () {
		if (customer.getCurrentBalance() < 0) {
			currentBalanceTextField.setText(String.format("£ %.2f", (customer.getCurrentBalance()* (-1))) + " CR");
		} else {
			currentBalanceTextField.setText(String.format("£ %.2f", customer.getCurrentBalance()));
		}
	}
	//clear input TextFields - nameOfWine, quantity, pricePerBottle to an empty String
	private void clearTransactionInputTextFields () {
		nameOfWineTextField.setText("");
		quantityTextField.setText("");
		pricePerBottleTextField.setText("");
	}
	
	
}
