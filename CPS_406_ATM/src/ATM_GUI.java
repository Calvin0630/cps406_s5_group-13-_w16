import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-10.
 * Daniel hopping on 2016-03-17
 */
public class ATM_GUI extends JFrame {
    protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected static final int FRAME_WIDTH = screenSize.width;
    protected static final int FRAME_HEIGHT = screenSize.height;
    protected static final int NUM_PAD_DIMENSION = FRAME_HEIGHT / 4;

    public ATM_GUI(){
        setSize(screenSize);
        this.getContentPane().setLayout(null);
        JPanel numpad = createNumPad();
        JPanel screen = createScreen();
        add(numpad);
        add(screen);
        add(createScreenLeftButtons(screen));
        add(createScreenRightButtons(screen));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

	public JPanel createNumPad(){

		JPanel numPad = new JPanel(new GridLayout(4, 3, 10, 10));
		JButton button;
		{
			for(int i = 1; i <= 9; i++){
				button = new JButton();
				button.setText(Integer.toString(i));
				numPad.add(button);
			}
			JButton blankButton = new JButton();
			blankButton.setVisible(false);
			numPad.add(blankButton);
			button = new JButton("0");
			numPad.add(button);
			numPad.setSize(NUM_PAD_DIMENSION, NUM_PAD_DIMENSION);
		}
		JPanel buffer = new JPanel();
		buffer.setSize(numPad.getWidth()/2,0);
		JPanel optionPanel = new JPanel(new GridLayout(4, 1, 0, 10));
		{
			button = new JButton("ENTER");
			button.setBackground(Color.green);
			optionPanel.add(button);
			button = new JButton("CORRECTION");
			button.setBackground(Color.yellow);
			optionPanel.add(button);
			button = new JButton("CANCEL");
			button.setBackground(Color.red);
			optionPanel.add(button);
		}
		JPanel inputPanel = new JPanel();
		{
			inputPanel.setLayout(new BoxLayout(inputPanel,BoxLayout.LINE_AXIS));
			inputPanel.setLocation(FRAME_WIDTH / 5, 5 * (FRAME_HEIGHT / 8));
			inputPanel.setSize(numPad.getWidth()*4/3+buffer.getWidth(), numPad.getHeight());
			inputPanel.add(numPad);
			inputPanel.add(buffer);
			inputPanel.add(optionPanel);
		}
		return inputPanel;
	}

    public JPanel createScreen(){
        JPanel screen = new JPanel();
        screen.setBackground(Color.GRAY);
        screen.setSize(FRAME_WIDTH / 3, 2 * (FRAME_HEIGHT / 5));
        JTextArea output = new JTextArea("Test");
        output.setEditable(false);
        screen.add(output);
        screen.setLocation(FRAME_WIDTH / 6, FRAME_HEIGHT / 10);
        output.setSize(screen.getSize());
        output.setBackground(screen.getBackground());
        output.setLocation(screen.getLocation());
        return screen;
    }

	public JPanel createScreenLeftButtons(JPanel screen){
		JPanel leftPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 5));
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new JButton("Left Option " + Integer.toString(i));
			leftPanel.add(button);
		}
		leftPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 4 / 7);
		leftPanel.setLocation(screen.getX() - leftPanel.getWidth() - leftPanel.getWidth()/5, screen.getY()* 9 / 7);
		return leftPanel;
	}

	public JPanel createScreenRightButtons(JPanel screen){
		JPanel rightPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 5));
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new JButton("Right Option " + Integer.toString(i));
			rightPanel.add(button);
		}
		rightPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 4 / 7);
		rightPanel.setLocation(screen.getX() + screen.getWidth() + rightPanel.getWidth()/5 , screen.getY()* 9 / 7);
		return rightPanel;
	}
}
