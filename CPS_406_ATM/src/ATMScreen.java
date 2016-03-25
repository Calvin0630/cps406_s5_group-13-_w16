import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMScreen extends JPanel{
	private JLabel title, instruction, input;
	protected JLabel leftOne, leftTwo, leftThree;
	protected JLabel rightOne, rightTwo, rightThree;
	private String inputString = "";

	protected static int leftOneFunc, leftTwoFunc, leftThreeFunc;
	protected static int rightOneFunc, rightTwoFunc, rightThreeFunc;

	private boolean acceptInput;
	protected int currentScreen;
	int PINattempts = 0;

	protected static final int WELCOME = 0;
	protected static final int PIN_INPUT = 1;
	protected static final int MAIN_MENU = 2;
	protected static final int CHECK_BALANCE = 3;
	protected static final int CHECK_BALANCE_SAVINGS = 4;
	protected static final int CHECK_BALANCE_CHEQUING = 5;
	protected static final int CHANGE_PIN = 6;
	Timer timer;

	public ATMScreen(int xPos, int yPos, int wth, int hth){
		super();
		leftOneFunc = leftTwoFunc = leftThreeFunc = 0;
		rightOneFunc = rightTwoFunc = rightThreeFunc = 0;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setSize(wth, hth);
		setLocation(xPos, yPos);	
		setBackground(Color.white);
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
		ATMinstructions.setSize(wth, hth / 11);
		add(ATMinstructions);

		JPanel OptionText = new JPanel(new GridLayout(3, 2, wth / 3, hth / 6));
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
		add(OptionText);
		acceptInput = true;

	}

	public int getCurrentScreen(){
		return currentScreen;
	}

	public void setCurrentScreen(int screen){
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
	}

	public void leftOneButton(){
		leftOneFunc = 1 - leftOneFunc;
	}

	public void welcome(){
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

	public void inputPIN(){
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


	public void mainMenu(){
		resetValues();
		currentScreen = MAIN_MENU;
		acceptInput = false;
		title.setText("Main Menu");
		instruction.setText("Select Option");
		leftOne.setText("Withdraw Money");
		leftTwo.setText("Check Balance");
		leftThree.setText("Transfer funds");
		rightOne.setText("Deposit Money");
		rightTwo.setText("Change Pin");
		rightThree.setText("Pay Debt");

		leftTwoFunc = CHECK_BALANCE;
		
		rightTwoFunc = CHANGE_PIN;

		leftOne.setVisible(true);
		leftTwo.setVisible(true);
		leftThree.setVisible(true);
		rightOne.setVisible(true);
		rightTwo.setVisible(true);
		rightThree.setVisible(true);
	}

	public void checkBalance(){
		resetValues();
		currentScreen = CHECK_BALANCE;
		acceptInput = false;
		title.setText("Check Balance");
		instruction.setText("Select account");
		leftOne.setText("Savings");
		rightOne.setText("Chequing");
		leftOneFunc = CHECK_BALANCE_SAVINGS;
		rightOneFunc = CHECK_BALANCE_CHEQUING;
		leftOne.setVisible(true);
		rightOne.setVisible(true);
	}

	public void checkBalanceSavings(){
		resetValues();
		currentScreen = CHECK_BALANCE_SAVINGS;
		title.setText("Balance of Savings Account");
		instruction.setText("Account Number: " + ATM_GUI.accountDatabase.getAccountNumber());
		input.setText("$ " + ATM_GUI.accountDatabase.getSavingsBalance());
		leftOne.setText("Return to Main Menu");
		rightOne.setText("Print Reciept");
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftOne.setVisible(true);
		rightOne.setVisible(true);
		leftThreeFunc = MAIN_MENU;
	}

	public void checkBalanceChequing(){
		resetValues();
		currentScreen = CHECK_BALANCE_CHEQUING;
		title.setText("Balance of Chequing Account");
		instruction.setText("Account Number: " + ATM_GUI.accountDatabase.getAccountNumber());
		input.setText("$ " + ATM_GUI.accountDatabase.getChequingBalance());
		leftOne.setText("Return to Main Menu");
		rightOne.setText("Print Reciept");
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftOne.setVisible(true);
		rightOne.setVisible(true);
		leftThreeFunc = MAIN_MENU;
	}

	public void changePIN(){
		resetValues();
		currentScreen = CHANGE_PIN;
		title.setText("Change Pin");
		instruction.setText("Enter new pin via num pad");
		inputString = "";
		input.setText(" ");
		acceptInput = true;
		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
		leftOne.setText("Cancel");
		leftOne.setVisible(true);
		leftThreeFunc = MAIN_MENU;
	}

	public void resetValues(){
		leftOneFunc = leftTwoFunc = leftThreeFunc = -1;
		rightOneFunc = rightTwoFunc = rightThreeFunc = -1;
		input.setText("");
		acceptInput = false;
		leftOne.setVisible(false);
		leftTwo.setVisible(false);
		leftThree.setVisible(false);
		rightOne.setVisible(false);
		rightTwo.setVisible(false);
		rightThree.setVisible(false);
	}

	public void storeInput(String inp){
		if(acceptInput) {
			/*
			 *  Daniel Jack
			 * 	Use Case 5: User enters PIN
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

	public void correction(){
		if (input.getText().length() > 0){
			input.setText(input.getText().substring(0,
					input.getText().length() - 1));
			if (inputString.length() != 0)
				inputString = inputString.substring(0,
						inputString.length() - 1);
		}
	}

	public void cancel(){
		input.setText("");
		if (currentScreen == PIN_INPUT || currentScreen == WELCOME)
			setCurrentScreen(WELCOME);
		if (currentScreen != PIN_INPUT && currentScreen != WELCOME)
			setCurrentScreen(MAIN_MENU);
		if (currentScreen == MAIN_MENU){
			setCurrentScreen(WELCOME);
			ATMFields.debitCard.setX(ATMFields.debitCard.getX()-1000);
		}
	}

	public void enter(){
		if(currentScreen == PIN_INPUT){
			if (Integer.parseInt(inputString) == ATM_GUI.accountDatabase.getPIN()){
				setCurrentScreen(MAIN_MENU);
				timer.stop();
			}
			else {
				/*
				 * Daniel Jack
				 * Use Case 6: Incorrect PIN
				 */
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
				instruction.setText("PIN must be 4 characters long");
			}
			else{
				ATM_GUI.accountDatabase.setPIN(Integer.parseInt(inputString));
				setCurrentScreen(WELCOME);
				ATMFields.debitCard.setX(ATMFields.debitCard.getX()-1000);
				}
		}
		
	}

	public void exitSystem()
	{

		setCurrentScreen(WELCOME);
		ATMFields.debitCard.setX(ATMFields.debitCard.getX()-1000);
		ATM_GUI.fields.repaint();
	}
	public void wait(int delay){
		try {
			Thread.sleep(delay);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

	}
}
