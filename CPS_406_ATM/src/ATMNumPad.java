import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-21.
 */
public class ATMNumPad extends JPanel{

    protected JButton zero, one, two, three, four, five, six;
    protected JButton seven, eight, nine;

    public ATMNumPad(ATM_GUI.ATMListener listener){
        super(new GridLayout(4, 3, 10, 10));
        setBackground(ATM_GUI.background);
        int width = ATM_GUI.NUM_PAD_DIMENSION-20 / 3;
        int height = (ATM_GUI.NUM_PAD_DIMENSION-30) /4;

        one = new JButton("1");
        one.setBackground(ATM_GUI.buttonsBackColor);
        one.setSize(width, height);
        one.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        one.addActionListener(listener);
        add(one);

        two = new JButton("2");
        two.setBackground(ATM_GUI.buttonsBackColor);
        two.setSize(width, height);
        two.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        two.addActionListener(listener);
        add(two);

        three = new JButton("3");
        three.setBackground(ATM_GUI.buttonsBackColor);
        three.setSize(width, height);
        three.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        three.addActionListener(listener);
        add(three);

        four = new JButton("4");
        four.setBackground(ATM_GUI.buttonsBackColor);
        four.setSize(width, height);
        four.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        four.addActionListener(listener);
        add(four);

        five = new JButton("5");
        five.setBackground(ATM_GUI.buttonsBackColor);
        five.setSize(width, height);
        five.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        five.addActionListener(listener);
        add(five);

        six = new JButton("6");
        six.setBackground(ATM_GUI.buttonsBackColor);
        six.setSize(width, height);
        six.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        six.addActionListener(listener);
        add(six);

        seven = new JButton("7");
        seven.setBackground(ATM_GUI.buttonsBackColor);
        seven.setSize(width, height);
        seven.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        seven.addActionListener(listener);
        add(seven);

        eight = new JButton("8");
        eight.setBackground(ATM_GUI.buttonsBackColor);
        eight.setSize(width, height);
        eight.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        eight.addActionListener(listener);
        add(eight);

        nine = new JButton("9");
        nine.setBackground(ATM_GUI.buttonsBackColor);
        nine.setSize(width, height);
        nine.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        nine.addActionListener(listener);
        add(nine);

        JButton blankButton = new JButton();
        blankButton.setVisible(false);
        add(blankButton);

        zero = new JButton("0");
        zero.setBackground(ATM_GUI.buttonsBackColor);
        zero.setSize(width, height);
        zero.setFont(new Font(one.getFont().getName(),
                one.getFont().getStyle(),
                one.getHeight()/2));
        zero.addActionListener(listener);
        add(zero);

        setSize(ATM_GUI.NUM_PAD_DIMENSION, ATM_GUI.NUM_PAD_DIMENSION);
        setLocation(ATM_GUI.screen.getX()+ ATM_GUI.screen.getWidth()/4 - getWidth()/2,
                ATM_GUI.screen.getY() + ATM_GUI.screen.getHeight() + ATM_GUI.NUM_PAD_DIMENSION/3 );
    }
}
