import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by Lionel on 2016-03-10.
 * Daniel hopping on 2016-03-17
 */

protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FRAME_WIDTH = screenSize.width;
	public static final int FRAME_HEIGHT = screenSize.height;
	protected static final int NUM_PAD_DIMENSION = FRAME_HEIGHT / 4;
	public final static Color background = 	Color.lightGray;
	public final static Color buttonsBackColor = Color.white;

	private ATMListener listener;
	protected static ATMScreen screen;
	protected static ATMNumPad numPad;
	private static ATMOptionPanel optionPanel;

	public ATM_GUI(){
		setSize(screenSize);
		listener = new ATMListener();
		screen = new ATMScreen(FRAME_WIDTH / 10, FRAME_HEIGHT / 10,FRAME_WIDTH / 3, 2 * (FRAME_HEIGHT / 5));
		numPad = new ATMNumPad(listener);
		optionPanel = new ATMOptionPanel(listener);
		screenLeftButtons = new ATMLeftScreenButtons(screen, listener);
		screenRightButtons = new ATMRightScreenButtons(screen, listener);
		add(screen);
		add(numPad);
		add(optionPanel);
		add(screenLeftButtons);
		add(screenRightButtons);
		ATMFields fields = new ATMFields();
		add(fields);
		
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JPanel createScreenLeftButtons(JPanel screen){
		JPanel leftPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 10));
		leftPanel.setBackground(background);
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new BasicArrowButton(BasicArrowButton.EAST);
			button.setBackground(buttonsBackColor);
			leftPanel.add(button);
		}
		leftPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
		leftPanel.setLocation(screen.getX() - leftPanel.getWidth() - 10, screen.getY()+screen.getHeight() * 3/10);
		return leftPanel;
	}

	public JPanel createScreenRightButtons(JPanel screen){
		JPanel rightPanel = new JPanel(new GridLayout(3, 1, 0, screen.getHeight() / 10));
		rightPanel.setBackground(background);
		JButton button;
		for (int i = 1; i <= 3; i++) {
			button = new BasicArrowButton(BasicArrowButton.WEST);
			button.setBackground(buttonsBackColor);
			rightPanel.add(button);
		}
		rightPanel.setSize(screen.getX() / 2, screen.getY() + screen.getHeight() * 3 / 7);
		rightPanel.setLocation(screen.getX() + screen.getWidth() + 10 , screen.getY()+screen.getHeight() * 3/10);
		return rightPanel;
	}
	class ATMListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource().equals(numPad.zero)){
				screen.storeInput("0");
			}
			if(event.getSource().equals(numPad.one)){
				screen.storeInput("1");
			}
			if(event.getSource().equals(numPad.two)){
				screen.storeInput("2");
			}
			if(event.getSource().equals(numPad.three)){
				screen.storeInput("3");
			}
			if(event.getSource().equals(numPad.four)){
				screen.storeInput("4");
			}
			if(event.getSource().equals(numPad.five)){
				screen.storeInput("5");
			}
			if(event.getSource().equals(numPad.six)){
				screen.storeInput("6");
			}
			if(event.getSource().equals(numPad.seven)){
				screen.storeInput("7");
			}
			if(event.getSource().equals(numPad.eight)){
				screen.storeInput("8");
			}
			if(event.getSource().equals(numPad.nine)){
				screen.storeInput("9");
			}
			if(event.getSource().equals(optionPanel.cancel)){
				screen.cancel();
			}
			if(event.getSource().equals(optionPanel.correction)){
				screen.correction();
			}
		}
	}
}
