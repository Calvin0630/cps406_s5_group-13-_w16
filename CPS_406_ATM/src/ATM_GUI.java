import com.sun.prism.*;
import javafx.scene.shape.*;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Created by Lionel on 2016-03-10.
 */
public class ATM_GUI extends JFrame {
    protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int FRAME_WIDTH = screenSize.width;
    public final int FRAME_HEIGHT = screenSize.height;
    protected final int NUM_PAD_DIMENSION = FRAME_HEIGHT / 4;
    private JPanel numPad;
    private  JPanel screen;

    public ATM_GUI(){
        setSize(screenSize);
        numPad = createNumPad();
        screen = createScreen();
        add(numPad);
        add(screen);
        add(createScreenLeftButtons(screen));
        add(createScreenRightButtons(screen));
        ATMFields fields = new ATMFields();
        add(fields);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JPanel createNumPad(){
        JPanel numPad = new JPanel(new GridLayout(4, 3, 10, 10));
        JButton button;
        for(int i = 1; i < 10; i++){
            button = new JButton();
            button.setText(Integer.toString(i));
            numPad.add(button);
        }
        button = new JButton("0");
        numPad.add(button);
        numPad.setSize(NUM_PAD_DIMENSION, NUM_PAD_DIMENSION);
        JPanel optionPanel = new JPanel(new GridLayout(3, 1,0, 10));
        optionPanel.setSize(2 * (numPad.getWidth() / 3), numPad.getHeight());
        button = new JButton("ENTER");
        optionPanel.add(button);
        button = new JButton("CORRECTION");
        optionPanel.add(button);
        button = new JButton("CANCEL");
        optionPanel.add(button);
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.LINE_AXIS));
        inputPanel.setLocation(FRAME_WIDTH / 8, 5 * (FRAME_HEIGHT / 8));
        inputPanel.add(numPad);
        inputPanel.add(optionPanel);
        inputPanel.setSize(numPad.getWidth() + optionPanel.getWidth() + 10,
                numPad.getHeight());
        return inputPanel;
    }

    public JPanel createScreen(){
        JPanel screen = new JPanel();
        screen.setBackground(Color.GRAY);
        screen.setSize(FRAME_WIDTH / 3, 2 * (FRAME_HEIGHT / 5));
        JTextArea output = new JTextArea("Test");
        screen.add(output);
        screen.setLocation(FRAME_WIDTH / 10, FRAME_HEIGHT / 10);
        output.setSize(screen.getSize());
        output.setBackground(screen.getBackground());
        output.setLocation(screen.getLocation());
        return screen;
    }

    public JPanel createScreenLeftButtons(JPanel screen){
        JPanel leftPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 5));
        JButton button;
        for (int i = 1; i <= 3; i++) {
            button = new JButton("Option " + Integer.toString(i));
            leftPanel.add(button);
        }
        leftPanel.setSize(screen.getWidth() / 5, screen.getHeight());
        leftPanel.setLocation(screen.getX() - leftPanel.getWidth(), screen.getY());
        return leftPanel;
    }

    public JPanel createScreenRightButtons(JPanel screen){
        JPanel rightPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 5));
        JButton button;
        for (int i = 1; i <= 3; i++) {
            button = new JButton("Option " + Integer.toString(i));
            rightPanel.add(button);
        }
        rightPanel.setLocation(screen.getX() + screen.getWidth(),
                screen.getY());
        rightPanel.setSize(screen.getWidth() / 5, screen.getHeight());
        return rightPanel;
    }
}

class ATMFields extends JComponent{
    Rectangle cardSlot = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            100, 300, 100);
    Rectangle cashDispensor = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            300, 300, 100);
    Rectangle printer = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            500,  300, 100);
    Rectangle nfc = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            700,  300, 100);

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.draw(cardSlot);
        g2.draw(cashDispensor);
        g2.draw(printer);
        g2.draw(nfc);
    }
}
