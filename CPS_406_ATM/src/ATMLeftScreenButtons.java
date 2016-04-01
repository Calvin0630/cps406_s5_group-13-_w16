import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * @author Group 13
 * JPanel of buttons on left side of the screen.
 */
public class ATMLeftScreenButtons extends JPanel{

    protected BasicArrowButton leftOne, leftTwo, leftThree;

    /**
     * Constructor to initialize, detail, and set listeners for left side buttons.
     * @param screen ATMScreen of the system.
     * @param listener contained in ATM_GUI.ATMListener
     */
    public ATMLeftScreenButtons(ATMScreen screen, ATM_GUI.ATMListener listener){
        super(new GridLayout(3, 1, 0, screen.getHeight() / 10));
        setBackground(ATM_GUI.background);

        leftOne = new BasicArrowButton(BasicArrowButton.EAST);
        leftOne.setBackground(ATM_GUI.buttonsBackColor);
        leftOne.addActionListener(listener);
        add(leftOne);

        leftTwo = new BasicArrowButton(BasicArrowButton.EAST);
        leftTwo.setBackground(ATM_GUI.buttonsBackColor);
        leftTwo.addActionListener(listener);
        add(leftTwo);

        leftThree = new BasicArrowButton(BasicArrowButton.EAST);
        leftThree.setBackground(ATM_GUI.buttonsBackColor);
        leftThree.addActionListener(listener);
        add(leftThree);

        setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
        setLocation(screen.getX() - getWidth() - 10, screen.getY()+screen.getHeight() * 3/10);
    }
}
