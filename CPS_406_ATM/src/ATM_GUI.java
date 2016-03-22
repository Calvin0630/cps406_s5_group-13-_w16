import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lionel on 2016-03-10.
 * Daniel hopping on 2016-03-17
 */

public class ATM_GUI extends JFrame {
	protected static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int FRAME_WIDTH = screenSize.width;
	public static final int FRAME_HEIGHT = screenSize.height;
	protected static final int NUM_PAD_DIMENSION = FRAME_HEIGHT / 4;
	public final static Color background = 	Color.lightGray;
	public final static Color buttonsBackColor = Color.white;

	private ATMListener listener;
	protected static ATMScreen screen;
	protected static ATMNumPad numPad;
	protected static ATMOptionPanel optionPanel;
	protected static ATMLeftScreenButtons screenLeftButtons;
	protected static ATMRightScreenButtons screenRightButtons;
	AccountDatabase accountDatabase;
	
	public ATM_GUI(){
		setSize(screenSize);
		accountDatabase = new AccountDatabase();
		getAccountInfo();
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
	
	public void getAccountInfo(){
		        JTextField pinField = new JTextField("1234");
		        JTextField saveField = new JTextField("9876.54");
		        JTextField chequeField = new JTextField("321.01");
		        JTextField creditField = new JTextField("23.45");
		        JPanel panel = new JPanel(new GridLayout(0, 1));
		        panel.add(new JLabel("PIN:"));
		        panel.add(pinField);
		        panel.add(new JLabel("Savings Balance:"));
		        panel.add(saveField);
		        panel.add(new JLabel("Chequing Balance:"));
		        panel.add(chequeField);
		        panel.add(new JLabel("Credit debt:"));
		        panel.add(creditField);
		        int result = JOptionPane.showConfirmDialog(null, panel, "Welcome!",
		            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (result == JOptionPane.OK_OPTION) {
		            accountDatabase.setPIN(Integer.parseInt(pinField.getText()));
		            accountDatabase.setSavingsBalance((Double.parseDouble(saveField.getText())));
		            accountDatabase.setChequingBalance((Double.parseDouble(chequeField.getText())));
		            accountDatabase.setCreditDebt(Double.parseDouble(creditField.getText()));
		        } else {
		            System.exit(1);
		        }
		    
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
