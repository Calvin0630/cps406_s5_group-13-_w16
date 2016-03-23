import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

/**
 * Created by Lionel on 2016-03-18.
 */
public class ATMScreen extends JPanel{
    private JLabel title, instruction, input;
    private JLabel leftOne, leftTwo, leftThree;
    private JLabel rightOne, rightTwo, rightThree;

    protected int leftOneFunc, leftTwoFunc, leftThreeFunc;
    protected int rightOneFunc, rightTwoFunc, rightThreeFunc;

    private boolean acceptInput;
    protected int currentScreen;

    protected static final int WELCOME = 0;
    protected static final int CARD_INPUT = 1;
    protected static final int NFC_INPUT = 2;
    protected static final int MAIN_MENU = 3;

    public ATMScreen(int xPos, int yPos, int wth, int hth){
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Font font = new Font("SanSerif", Font.BOLD, 20);
        setSize(wth, hth);
        setLocation(xPos, yPos);
        setBackground(Color.white);

        JPanel ATMinstructions = new JPanel(new GridLayout(3,1));
        title = new JLabel("TEST", JLabel.CENTER);
        ATMinstructions.add(title);
        instruction = new JLabel("DO BANKING THINGS", JLabel.CENTER);
        ATMinstructions.add(instruction);
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
        System.out.println(screen);
        if(screen== WELCOME){
            welcome();
        }
        else if(screen == NFC_INPUT){
            nfcInput();
        }
    }

    public void welcome(){
        currentScreen = WELCOME;
        acceptInput = false;
        title.setText("Welcome to the ATM");
        instruction.setText("Select Option");
        leftOne.setText("Phone / NFC");
        rightOne.setText("Debit Card");

        leftOneFunc = NFC_INPUT;
        rightOneFunc = CARD_INPUT;

        title.setVisible(true);
        instruction.setVisible(true);
        leftOne.setVisible(true);
        rightOne.setVisible(true);

        input.setVisible(false);
        leftTwo.setVisible(false);
        leftThree.setVisible(false);
        rightTwo.setVisible(false);
        rightThree.setVisible(false);
    }

    public void nfcInput(){
        currentScreen = NFC_INPUT;
        acceptInput = false;
        title.setText("Phone / NFC");
        instruction.setText("Tap phone on NFC thingy");

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

    public void mainMenu(){
        currentScreen = MAIN_MENU;
        acceptInput = false;
        title.setText("Main Menu");
        instruction.setText("Select Option");
    }



    public void storeInput(String inp){
        if(acceptInput) {
            input.setText(input.getText() + inp);
            System.out.println(input.getText());
        }
    }

    public void correction(){
        if (input.getText().length() > 0){
            input.setText(input.getText().substring(0,
                    input.getText().length() - 1));
        }
    }

    public void cancel(){
        input.setText("");
    }

}
