import javax.swing.*;
import java.awt.*;

public class ATMScreen extends JPanel{
	private JLabel title, instruction, input;
	private JLabel leftOne, leftTwo, leftThree;
	private JLabel rightOne, rightTwo, rightThree;
	private String inputString = "";

	protected static int leftOneFunc, leftTwoFunc, leftThreeFunc;
	protected static int rightOneFunc, rightTwoFunc, rightThreeFunc;

	private boolean acceptInput;
	protected int currentScreen;

	protected static final int WELCOME = 0;
	protected static final int PIN_INPUT = 1;
	protected static final int MAIN_MENU = 2;

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
		ATMinstructions.setSize(wth, hth / 10);
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
		currentScreen = PIN_INPUT;
		acceptInput = true;
		title.setText("PIN INPUT");
		instruction.setText("Enter PIN via number pad or tap phone on NFC");
		input.setText("");
		input.setFont(new Font("Calibri",Font.PLAIN,ATM_GUI.FRAME_HEIGHT/10));

		title.setVisible(true);
		instruction.setVisible(true);
		input.setVisible(true);
	}


	public void mainMenu(){
		resetValues();
		currentScreen = MAIN_MENU;
		acceptInput = false;
		title.setText("Main Menu");
		instruction.setText("Select Option");

		leftOne.setVisible(true);
		leftTwo.setVisible(true);
		leftThree.setVisible(true);
		rightOne.setVisible(true);
		rightTwo.setVisible(true);
		rightThree.setVisible(true);
	}

	public void resetValues(){
		leftOneFunc = leftTwoFunc = leftThreeFunc = 0;
		rightOneFunc = rightTwoFunc = rightThreeFunc = 0;
		input.setText("");
		leftOne.setVisible(false);
		leftTwo.setVisible(false);
		leftThree.setVisible(false);
		rightOne.setVisible(false);
		rightTwo.setVisible(false);
		rightThree.setVisible(false);
	}

	public void storeInput(String inp){
		if(acceptInput) {
			if (currentScreen == PIN_INPUT){
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
		if (currentScreen != PIN_INPUT && currentScreen != WELCOME)
			setCurrentScreen(MAIN_MENU);
	}

}
