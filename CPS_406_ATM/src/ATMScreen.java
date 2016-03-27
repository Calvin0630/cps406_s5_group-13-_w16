import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ATMScreen extends JLayeredPane{
	private JLabel title, instruction, input;
	protected JLabel leftOne, leftTwo, leftThree;
	protected JLabel rightOne, rightTwo, rightThree;
	private String inputString = "";
	NumberFormat nf = NumberFormat.getCurrencyInstance();

	protected static int leftOneFunc, leftTwoFunc, leftThreeFunc;
	protected static int rightOneFunc, rightTwoFunc, rightThreeFunc;

	private boolean acceptInput;
	protected int currentScreen;
	private int PINattempts = 0;
	private int currentAccount = 0;
	private int withdrawTotal = 0;
	private boolean internalWithdrawal = false;

	protected static final int WELCOME = 0;
	protected static final int PIN_INPUT = 1;
	protected static final int MAIN_MENU = 2;
	protected static final int CHECK_BALANCE = 3;
	protected static final int CHECK_BALANCE_SAVINGS = 4;
	protected static final int CHECK_BALANCE_CHEQUING = 5;
	protected static final int CHANGE_PIN = 6;
	protected static final int SELECT_ACCOUNT = 7;
	protected static final int SELECT_ACCOUNT_CHEQUING = 71;
	protected static final int SELECT_ACCOUNT_SAVINGS = 72;
	protected static final int WITHDRAW = 8;
	protected static final int CHANGE_DISPLAY_LANGUAGE = 9;
	protected static final int TRANSFERS = 10;
	protected static final int ACCOUNT_TRANSFERS = 101; 
	protected static final int PAY_BILL = 102;
	protected static final int EXIT_SYSTEM = 999;
	Timer timer;

	public ATMScreen(int xPos, int yPos, int wth, int hth){
		super();
		leftOneFunc = leftTwoFunc = leftThreeFunc = 0;
		rightOneFunc = rightTwoFunc = rightThreeFunc = 0;

		setSize(wth, hth);
		setLocation(xPos, yPos);
		setBackground(Color.white);
		setOpaque(true);
		JPanel ATMinstructions = new JPanel(new GridLayout(3,1));
		title = new JLabel("TEST", JLabel.CENTER);
		title.setFont(new Font("Times New Roman", Font.PLAIN,ATM_GUI.FRAME_HEIGHT/25));
		ATMinstructions.add(title);
		instruction = new JLabel("DO BANKING THINGS", JLabel.CENTER);
		ATMinstructions.add(instruction);
		instruction.setFont(new Font("Times New Roman", Font.PLAIN,ATM_GUI.FRAME_HEIGHT/50));
		input = new JLabel("Sample input", JLabel.CENTER);
		input.setBackground(getBackground());
		ATMinstructions.add(input);
		ATMinstructions.setSize(wth, hth);
		ATMinstructions.setOpaque(false);
		add(ATMinstructions, Integer.valueOf(2));

		JPanel OptionText = new JPanel(new GridLayout(3, 2));
		OptionText.setBounds(wth/75, getHeight()/4, wth-wth/50, hth*4/5);
		OptionText.setOpaque(false);
		leftOne = new JLabel("LEFT ONE");
		OptionText.add(leftOne);
		rightOne = new JLabel("RIGHT ONE", JLabel.RIGHT);
		OptionText.add(rightOne);

		leftTwo = new JLabel("LEFT TWO");
		OptionText.add(leftTwo);
		rightTwo = new JLabel("RIGHT TWO", JLabel.RIGHT);
		OptionText.add(rightTwo);

		leftThree = new JLabel("LEFT THREE");
		OptionText.add(leftThree);
		rightThree = new JLabel("RIGHT THREE", JLabel.RIGHT);
		OptionText.add(rightThree);
		add(OptionText, Integer.valueOf(1));
		acceptInput = true;
	}

	protected int getCurrentScreen(){
		return currentScreen;
	}

	protected void setCurrentScreen(int screen){
		resetValues();

		if(screen== WELCOME){
			welcome();
		}
		else if (screen == PIN_INPUT){
			inputPIN();
		}
		else if (screen == MAIN_MENU){
			mainMenu();
		}
		else if(screen == CHECK_BALANCE){
			checkBalance();
		}
		else if(screen == CHECK_BALANCE_SAVINGS){
			checkBalanceSavings();
		}
		else if(screen == CHECK_BALANCE_CHEQUING){
			checkBalanceChequing();
		}
		else if (screen == CHANGE_PIN){
			changePIN();
		}
		else if(screen == SELECT_ACCOUNT){
			selectAccount();
		}
		else if(screen == SELECT_ACCOUNT_CHEQUING){
			withdraw(SELECT_ACCOUNT_CHEQUING);
		}
		else if(screen == SELECT_ACCOUNT_SAVINGS){
			withdraw(SELECT_ACCOUNT_SAVINGS);
		}
		else if (screen == CHANGE_DISPLAY_LANGUAGE){
			changeDisplayLanguage();
		}
		else if (screen == TRANSFERS){
			transfers();
		}
		else if (screen == ACCOUNT_TRANSFERS){
			accountTransfers();
		}
		else if (screen == PAY_BILL){
			payBill();
		}
		else if (screen == EXIT_SYSTEM){
			exitSystem();
		}
	}


	protected void welcome(){
		currentScreen = WELCOME;
		acceptInput = false;
		title.setText("Welcome to the ATM");
		instruction.setText("Insert Card to Continue");

		title.setVisible(true);
		instruction.setVisible(true);

		leftOne.setVisible(false);
		rightOne.setVisible(false);
		input.setVisible(false);
		leftTwo.setVisible(false);
		leftThree.setVisible(false);
		rightTwo.setVisible(false);
		rightThree.setVisible(false);
	}

	private void inputPIN(){
		inputString = "";
		currentScreen = PIN_INPUT;
		acceptInput = true;
		title.setText("PIN INPUT");
		instruction.setText("Enter PIN via number pad or tap phone on NFC");
		input.setText(" ");
		input.setFont(new Font("Calibri",Font.PLAIN,ATM_GUI.FRAME_HEIGHT/10));

		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);

		/*
		 * Daniel Jack
		 * Use Case 2: Inactivity timeout for NFC/PIN
		 */
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (input.getText() == " " && PINattempts == 0)
					exitSystem();
			}
		};
		timer = new Timer(30*1000,taskPerformer);
		timer.start();
	}


	private void mainMenu(){
		acceptInput = false;
		resetValues();
		currentScreen = MAIN_MENU;
		title.setText("Main Menu");
		instruction.setText("<html>Select Option<br>(Cancel to exit.)</html>");
		leftOne.setText("Deposit Money");
		leftTwo.setText("Change PIN");
		leftThree.setText("Change Language");
		rightOne.setText("Withdraw Money");
		rightTwo.setText("Check Balance");
		rightThree.setText("Transfer Funds / Pay Debt");

		leftOneFunc = -1;
		leftTwoFunc = CHANGE_PIN;
		leftThreeFunc = CHANGE_DISPLAY_LANGUAGE;

		rightOneFunc = SELECT_ACCOUNT;
		rightTwoFunc = CHECK_BALANCE;
		rightThreeFunc = TRANSFERS;

		leftOne.setVisible(true);
		leftTwo.setVisible(true);
		leftThree.setVisible(true);
		rightOne.setVisible(true);
		rightTwo.setVisible(true);
		rightThree.setVisible(true);
	}

	private void checkBalance(){
		acceptInput = false;
		resetValues();
		currentScreen = CHECK_BALANCE;
		title.setText("Check Balance");
		instruction.setText("Select account");
		leftTwo.setText("Savings");
		rightTwo.setText("Chequing");
		leftThree.setText("Main Menu");
		leftTwoFunc = CHECK_BALANCE_SAVINGS;
		rightTwoFunc = CHECK_BALANCE_CHEQUING;
		leftThreeFunc = MAIN_MENU;
		leftTwo.setVisible(true);
		rightTwo.setVisible(true);
		leftThree.setVisible(true);	
	}

	/*
	 * Use Case 11: Check Balance
	 */
	private void checkBalanceSavings(){
		resetValues();
		currentScreen = CHECK_BALANCE_SAVINGS;
		title.setText("Balance of Savings Account");
		instruction.setText("Account Number: " + String.format("%011d",ATM_GUI.accountDatabase.getAccountNumber()));
		input.setText(nf.format(ATM_GUI.accountDatabase.getSavingsBalance()));
		leftThree.setText("Main Menu");
		rightOne.setText("Print Reciept");
		rightThree.setText("<html>Exit<br>Output Bills</html>");
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftThree.setVisible(true);
		rightOne.setVisible(true);
		rightThree.setVisible(true);
		leftThreeFunc = MAIN_MENU;
		rightThreeFunc = EXIT_SYSTEM;
		rightOneFunc = -1;
	}

	/*
	 * Use Case 11: Check Balance
	 */
	private void checkBalanceChequing(){
		resetValues();
		currentScreen = CHECK_BALANCE_CHEQUING;
		title.setText("Balance of Chequing Account");
		instruction.setText("Account Number: " + String.format("%011d",ATM_GUI.accountDatabase.getAccountNumber()));
		input.setText(nf.format(ATM_GUI.accountDatabase.getChequingBalance()));
		leftThree.setText("Main Menu");
		rightOne.setText("Print Reciept");
		rightThree.setText("<html>Exit<br>Output Bills</html>");
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftThree.setVisible(true);
		rightOne.setVisible(true);
		rightThree.setVisible(true);
		leftThreeFunc = MAIN_MENU;
		rightThreeFunc = EXIT_SYSTEM;
		rightOneFunc = -1;
	}

	private void changePIN(){
		acceptInput = true;
		resetValues();
		currentScreen = CHANGE_PIN;
		title.setText("Change PIN");
		instruction.setText("Enter new PIN via num pad.");
		inputString = "";
		input.setText(" ");
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftThree.setText("Cancel");
		leftThree.setVisible(true);
		leftThreeFunc = MAIN_MENU;
	}

	private void selectAccount(){
		resetValues();
		internalWithdrawal = false;	
		currentScreen = SELECT_ACCOUNT;
		title.setText("Select Account");
		instruction.setText("Select either Chequing or Savings account.");
		rightTwo.setText("Chequing");
		leftTwo.setText("Savings");
		leftThree.setText("Cancel");
		rightTwoFunc = SELECT_ACCOUNT_CHEQUING;
		leftTwoFunc = SELECT_ACCOUNT_SAVINGS;
		leftThreeFunc = MAIN_MENU;
		leftTwo.setVisible(true);
		rightTwo.setVisible(true);
		leftThree.setVisible(true);
	}

	private void withdraw(int accountType){
		currentScreen = WITHDRAW;
		currentAccount = accountType;
		title.setText("Withdraw");
		acceptInput = true;
		instruction.setText("<html>Enter withdrawal amount via num pad.<br>Will output on exit.</html>");
		input.setVisible(true);
		leftThree.setText("Cancel");
		leftThree.setVisible(true);
		leftThreeFunc = MAIN_MENU;
	}

	private void error(String msg){
		resetValues();
		title.setText("Insufficient Funds");
		instruction.setText(msg);

		rightThree.setText("Return to Main Menu");
		rightThreeFunc = MAIN_MENU;
		rightThree.setVisible(true);
	}
	/*
	 * Daniel Jack
	 * Use Case 16. Change Display Language
	 */
	private void changeDisplayLanguage(){
		resetValues();
		currentScreen = CHANGE_DISPLAY_LANGUAGE;
		title.setText("Select Display Language");
		instruction.setVisible(false);
		title.setVisible(true);
		rightOne.setText("English (UK)");
		rightTwo.setText("English (Canada)");
		rightThree.setText("English (US)");
		leftOne.setText("Cancel");
		rightOne.setVisible(true);
		rightTwo.setVisible(true);
		rightThree.setVisible(true);
		leftOne.setVisible(true);
		rightOneFunc = MAIN_MENU;
		rightTwoFunc = MAIN_MENU;
		rightThreeFunc = MAIN_MENU;
		leftOneFunc = MAIN_MENU;
	}

	private void resetValues(){
		if (acceptInput)
			input.setVisible(true);
		else
			input.setVisible(false);
		leftOneFunc = leftTwoFunc = leftThreeFunc = -1;
		rightOneFunc = rightTwoFunc = rightThreeFunc = -1;
		input.setText("");
		inputString = "";
		leftOne.setVisible(false);
		leftTwo.setVisible(false);
		leftThree.setVisible(false);
		rightOne.setVisible(false);
		rightTwo.setVisible(false);
		rightThree.setVisible(false);
	}

	private void transfers(){
		acceptInput = false;
		resetValues();
		leftTwo.setText("Account Transfers");
		rightTwo.setText("Pay Bills");
		leftThree.setText("Cancel");
		leftTwo.setVisible(true);
		rightTwo.setVisible(true);
		leftThree.setVisible(true);
		leftTwoFunc = ACCOUNT_TRANSFERS;
		rightTwoFunc = PAY_BILL;
		leftThreeFunc = MAIN_MENU;
	}
	private void accountTransfers (){
		resetValues();
	}
	/*
	 * Use Case 17: Bill Payment
	 */
	private void payBill (){
		resetValues();
		internalWithdrawal = true;
		title.setText("Pay Bills");
		instruction.setText("Credit balance:");
		input.setText(nf.format(ATM_GUI.accountDatabase.getCreditDebt()));
		input.setVisible(true);
		leftTwo.setText("Pay from Savings Account");
		rightTwo.setText("Pay from Chequing Account");
		leftThree.setText("Main Menu");
		leftTwo.setVisible(true);
		rightTwo.setVisible(true);
		leftThree.setVisible(true);
		leftThreeFunc = MAIN_MENU;
		leftTwoFunc = SELECT_ACCOUNT_SAVINGS;
		rightTwoFunc = SELECT_ACCOUNT_CHEQUING;
	}


	protected void storeInput(String inp){
		if(acceptInput) {
			/*
			 *  Daniel Jack
			 * 	Use Case 5: User enters PIN
			 *  Michael D'Anna
			 *	Use Case 16: User changes PIN
			 */
			if (currentScreen == PIN_INPUT || currentScreen == CHANGE_PIN){
				if (inputString.length() < 4){
					input.setText(input.getText() + "*");
					inputString = inputString + inp;
				}
			}
			else
				input.setText(input.getText() + inp);
		}
	}

	protected void correction(){
		if (input.getText().length() > 0){
			input.setText(input.getText().substring(0,
					input.getText().length() - 1));
			if (inputString.length() != 0)
				inputString = inputString.substring(0,
						inputString.length() - 1);
		}
	}

	protected void cancel(){
		input.setText("");
		if (currentScreen == PIN_INPUT || currentScreen == MAIN_MENU)
			exitSystem();
		else if (currentScreen != PIN_INPUT && currentScreen != WELCOME)
			setCurrentScreen(MAIN_MENU);
	}

	public void enter(){
		if(currentScreen == PIN_INPUT){
			if (Integer.parseInt(inputString) == ATM_GUI.accountDatabase.getPIN()){
				setCurrentScreen(MAIN_MENU);
				inputString = "";
				timer.stop();
			}
			else {
				/*
				 * Daniel Jack
				 * Use Case 6: Incorrect PIN
				 */
				inputString="";
				input.setText("");
				PINattempts++;
				instruction.setText("Incorrect PIN, " + (5-PINattempts) + " attempts remaining.");
				if (PINattempts == 5){
					PINattempts=0;
					exitSystem();
				}
			}
		}
		/*
		 *  Michael D'Anna
		 * 	Use Case 10: User changes PIN
		 */
		if(currentScreen == CHANGE_PIN){
			if(inputString.length() != 4){
				instruction.setText("PIN must be 4 characters long.");
			}
			else{
				ATM_GUI.accountDatabase.setPIN(Integer.parseInt(inputString));
				setCurrentScreen(MAIN_MENU);
			}
		}
		/*
			Lionel Pereira
			Use Case 7: User withdraws money
		 */
		if(currentScreen == WITHDRAW){
			if (internalWithdrawal){
				if(currentAccount == SELECT_ACCOUNT_CHEQUING ){
					validChequingWithdraw(input.getText());
				}
				if(currentAccount == SELECT_ACCOUNT_SAVINGS ){
					validSavingsWithdraw(input.getText());
				}
			}
			else{
				if (Double.parseDouble(input.getText()) % 20 == 0 && Double.parseDouble(input.getText()) >= 20.00){
					if(currentAccount == SELECT_ACCOUNT_CHEQUING ){
						validChequingWithdraw(input.getText());
					}
					if(currentAccount == SELECT_ACCOUNT_SAVINGS ){
						validSavingsWithdraw(input.getText());
					}
				}
				else {
					instruction.setText("Amount must be in multiple of $20.");
				}
			}
		}
	}
	/*
	 * Use Case 8: Invalid amount
	 */
	private void validChequingWithdraw(String value){
		if(ATM_GUI.accountDatabase.getChequingBalance() > Double.parseDouble(input.getText())
				&& input.getText().length() > 0){
			ATM_GUI.accountDatabase.setChequingBalance(ATM_GUI.accountDatabase.getChequingBalance() -
					Double.parseDouble(input.getText()));
			if (internalWithdrawal)
				ATM_GUI.accountDatabase.setCreditDebt(ATM_GUI.accountDatabase.getCreditDebt()-Double.parseDouble(input.getText()));
			addWithdrawTotal(input.getText());
			checkBalanceChequing();
		}
		else{
			error(nf.format(Double.parseDouble(input.getText())) + " is greater than current Chequing balance.");
		}
	}
	/*
	 * Use Case 8: Invalid amount
	 */
	private void validSavingsWithdraw(String value){
		if(ATM_GUI.accountDatabase.getSavingsBalance() > Double.parseDouble(value)
				&& value.length() > 0){
			ATM_GUI.accountDatabase.setSavingsBalance(ATM_GUI.accountDatabase.getSavingsBalance() -
					Double.parseDouble(value));
			if (internalWithdrawal)
				ATM_GUI.accountDatabase.setCreditDebt(ATM_GUI.accountDatabase.getCreditDebt()-Double.parseDouble(input.getText()));
			addWithdrawTotal(value);
			checkBalanceSavings();
		}
		else{
			error(nf.format(Double.parseDouble(value)) + " is greater than current Savings balance.");
		}
	}

	private void addWithdrawTotal (String valueString)
	{
		withdrawTotal += Integer.parseInt(valueString);
	}


	private void exitSystem()
	{
		resetValues();
		setCurrentScreen(WELCOME);
		ATMFields.debitCard.setX(ATMFields.debitCard.getX()-1000);
		if (withdrawTotal > 0)
			ATMFields.displayWithdraw(withdrawTotal);
		ATM_GUI.fields.repaint();
	}
}
