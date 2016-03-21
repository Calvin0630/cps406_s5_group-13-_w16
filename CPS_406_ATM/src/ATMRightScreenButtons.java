import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-21.
 */
public class ATMRightScreenButtons extends JPanel{

    protected BasicArrowButton rightOne, rightTwo, rightThree;

    public ATMRightScreenButtons(ATMScreen screen, ATM_GUI.ATMListener listener){
        super(new GridLayout(3, 1, 0, screen.getHeight() / 10));
        setBackground(ATM_GUI.background);

        rightOne = new BasicArrowButton(BasicArrowButton.WEST);
        rightOne.setBackground(ATM_GUI.buttonsBackColor);
        rightOne.addActionListener(listener);
        add(rightOne);

        rightTwo = new BasicArrowButton(BasicArrowButton.WEST);
        rightTwo.setBackground(ATM_GUI.buttonsBackColor);
        rightTwo.addActionListener(listener);
        add(rightTwo);

        rightThree = new BasicArrowButton(BasicArrowButton.WEST);
        rightThree.setBackground(ATM_GUI.buttonsBackColor);
        rightThree.addActionListener(listener);
        add(rightThree);

        setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
        setLocation(screen.getX() + screen.getWidth() + 10 , screen.getY()+screen.getHeight() * 3/10);
    }
}
