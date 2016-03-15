import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-10.
 */
public class ATM_GUI extends JFrame {
    protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int FRAME_WIDTH = screenSize.width;
    protected static final int FRAME_HEIGHT = screenSize.height;
    protected static final int NUM_PAD_DIMENSION = 300;
    protected static final Point NUM_PAD_POINT = new Point(FRAME_WIDTH / 5,3 * (FRAME_HEIGHT / 5));
    protected static final int SCREEN_WIDTH = 400;
    protected static final int SCREEN_HEIGHT = 300;

    public ATM_GUI(){
        setSize(screenSize);
        this.getContentPane().setLayout(null);;
        add(createNumPad());
        add(createScreen());
        add(createScreenButtonsLeft());
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

    public JPanel createScreen(){
        JPanel screen = new JPanel();
        screen.setBackground(Color.GRAY);
        JTextArea output = new JTextArea("Test");
        output.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        screen.add(output);
        screen.setVisible(true);
        screen.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        screen.setLocation(200,100);
        output.setBackground(screen.getBackground());
        return screen;
    }

    public JPanel createScreenButtonsLeft(){
        JPanel leftPanel = new JPanel();
        leftPanel.setSize(100, 300);
        leftPanel.setLocation(100,100);
        leftPanel.setLayout(new GridLayout(3,1,10,30));
        JButton button;
        for (int i = 0; i < 3; i++) {
            button = new JButton(Integer.toString(i));
            leftPanel.add(button);
        }
        leftPanel.setVisible(true);
        return leftPanel;
    }
}
