import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-18.
 */
public class ATMScreen extends JPanel{
    private JLabel title, instruction, input;
    private JLabel leftOne, leftTwo, leftThree;
    private JLabel rightOne, rightTwo, rightThree;
    boolean acceptInput;

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
        title.setText("Poop");
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
    }

    public void welcome(){
        acceptInput = false;
        title.setText("Welcome to the ATM");
        instruction.setText("Select Option");
        input.setVisible(false);
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
