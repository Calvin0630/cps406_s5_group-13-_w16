import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * Created by Lionel on 2016-03-10.
 * Daniel hopping on 2016-03-17
 */
@SuppressWarnings("serial")
public class ATM_GUI extends JFrame {
	protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final int FRAME_WIDTH = screenSize.width;
	public final int FRAME_HEIGHT = screenSize.height;
	protected final int NUM_PAD_DIMENSION = FRAME_HEIGHT / 4;
	private JPanel numPad;
	private  JPanel screen;

	public ATM_GUI(){
		setSize(screenSize);
		screen = createScreen();
		numPad = createNumPad();
		add(screen);
		add(numPad);
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
			inputPanel.setSize(numPad.getWidth()*4/3+buffer.getWidth(), numPad.getHeight());
			inputPanel.setLocation(screen.getX()+screen.getWidth()/2- inputPanel.getWidth()/2,
					(FRAME_HEIGHT-screen.getY()+screen.getHeight())/2);
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
		screen.setLocation(FRAME_WIDTH / 10, FRAME_HEIGHT / 10);
		output.setSize(screen.getSize());
		output.setBackground(screen.getBackground());
		output.setLocation(screen.getLocation());
		return screen;
	}

	public JPanel createScreenLeftButtons(JPanel screen){
		JPanel leftPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 10));
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new BasicArrowButton(BasicArrowButton.EAST);
			leftPanel.add(button);
		}
		leftPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
		leftPanel.setLocation(screen.getX() - leftPanel.getWidth() - 10, screen.getY()+screen.getHeight() * 3/10);
		return leftPanel;
	}

	public JPanel createScreenRightButtons(JPanel screen){
		JPanel rightPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 10));
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new BasicArrowButton(BasicArrowButton.WEST);
			rightPanel.add(button);
		}
		rightPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
		rightPanel.setLocation(screen.getX() + screen.getWidth() + 10 , screen.getY()+screen.getHeight() * 3/10);
		return rightPanel;
	}
}
@SuppressWarnings("serial")
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
