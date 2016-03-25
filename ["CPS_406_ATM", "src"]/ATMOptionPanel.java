import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-21.
 */
public class ATMOptionPanel extends JPanel {

    protected JButton enter, correction, cancel;

    public ATMOptionPanel(ATM_GUI.ATMListener listener){
        super(new GridLayout(4, 1, 0, 10));
        setBackground(ATM_GUI.background);

        int width = ATM_GUI.NUM_PAD_DIMENSION;
        int height = (ATM_GUI.NUM_PAD_DIMENSION - 30) / 4;
        enter = new JButton("ENTER");
        enter.setSize(width, height);
        enter.setFont(new Font(enter.getFont().getName(),
                enter.getFont().getStyle(),
                enter.getHeight()/2));
        enter.setBackground(Color.green);
        enter.addActionListener(listener);
        add(enter);

        correction = new JButton("CORRECTION");
        correction.setSize(width, height);
        correction.setFont(new Font(correction.getFont().getName(),
                correction.getFont().getStyle(),
                correction.getHeight()/2));
        correction.setBackground(Color.yellow);
        correction.addActionListener(listener);
        add(correction);

        cancel = new JButton("CANCEL");
        cancel.setSize(width, height);
        cancel.setFont(new Font(cancel.getFont().getName(),
                cancel.getFont().getStyle(),
                cancel.getHeight()/2));
        cancel.setBackground(Color.red);
        cancel.addActionListener(listener);
        add(cancel);

        setSize(width, width);
        setLocation(ATM_GUI.numPad.getX() + ATM_GUI.numPad.getWidth() +
                width / 8, ATM_GUI.numPad.getY());
    }
}
