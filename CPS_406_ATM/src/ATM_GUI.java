import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-10.
 */
public class ATM_GUI extends JFrame {
    protected static final int FRAME_WIDTH = 2000;
    protected static final int FRAME_HEIGHT = 1000;
    protected static final int NUM_PAD_DIMENSION = 300;
    protected static final Point NUM_PAD_POINT = new Point(FRAME_WIDTH / 5,3 * (FRAME_HEIGHT / 5));

    public ATM_GUI(){
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.getContentPane().setLayout(null);;
        add(createNumPad());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel createNumPad(){
        JPanel numPad = new JPanel(new GridLayout(4, 3, 10, 10));
        numPad.setLocation(NUM_PAD_POINT);
        JButton button;
        for(int i = 1; i < 13; i++){
            button = new JButton();
            button.setText(Integer.toString(i));
            if( i < 10) button.setText(Integer.toString(i));
            if( i == 10) button.setText("CLEAR");
            if( i == 11) button.setText("0");
            if( i == 12) button.setText("CANCEL");
            numPad.add(button);
        }
        numPad.setSize(NUM_PAD_DIMENSION, NUM_PAD_DIMENSION);
        return numPad;
    }
}
