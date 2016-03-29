import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-21.
 */
public class ATMLeftScreenButtons extends JPanel{

    protected BasicArrowButton leftOne, leftTwo, leftThree;

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
