import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	public static AccountDatabase accountDatabase;
	public ATMFieldsMouseListener fieldsListener;

	protected static String selected;

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
		screen.welcome();
		ATMFields fields = new ATMFields();
		add(fields);
		fieldsListener = new ATMFieldsMouseListener();
		addMouseListener(fieldsListener);
		addMouseMotionListener(fieldsListener);

		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void getAccountInfo(){
		boolean error = false;
		do{
			try{
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
					accountDatabase.setAccountNumber(0001);
					error = false;
				} else {
					System.exit(1);
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Invalid input fields.","Error!",JOptionPane.ERROR_MESSAGE);
				error = true;
			}
		}while (error) ;  
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
			if(event.getSource().equals(screenLeftButtons.leftOne)){

			}
			repaint();
		}
	}
	class ATMFieldsMouseListener extends MouseAdapter {
		public void mouseDragged(MouseEvent e){
			if(ATMFields.NFCPhone.getBounds().contains(e.getPoint())) {
				ATMFields.NFCPhone.moveField(e);
				selected="NFCPhone";
			}
			else if(ATMFields.nonNFCPhone.getBounds().contains(e.getPoint())){
				ATMFields.nonNFCPhone.moveField(e);
				selected="nonNFCPhone";
			}
			else if(ATMFields.debitCard.getBounds().contains(e.getPoint())){
				ATMFields.debitCard.moveField(e);
				selected="debitCard";
			}
			else if(ATMFields.cheque.getBounds().contains(e.getPoint())){
				ATMFields.cheque.moveField(e);
				selected="cheque";
			}
			else if(ATMFields.twentyBill.getBounds().contains(e.getPoint())){
				ATMFields.twentyBill.moveField(e);
				selected="twentyBill";
			}

			/*
			 * Use Case #1 User accessing with phone.
			 */
			if (screen.getCurrentScreen() == ATMScreen.PIN_INPUT && selected.equals("NFCPhone")){
				if (ATMFields.NFCPhone.getBounds().intersects(ATMFields.NFC.getBounds())){
					if (ATMFields.NFCPhone.getPIN() == accountDatabase.getPIN()){
						screen.setCurrentScreen(ATMScreen.MAIN_MENU);
					}
				}
			}

			repaint();
		}

		public void mouseReleased(MouseEvent e) {
			if(selected != null) {

				/*
				 * Use Case #3. User accessing with card
				 */
				if (screen.getCurrentScreen() == ATMScreen.WELCOME && selected.equals("debitCard"))
				{
					if (ATMFields.debitCard.getBounds().intersects(ATMFields.cardSlot.getBounds())){
						ATMFields.debitCard.setVisible(false);
						screen.setCurrentScreen(ATMScreen.PIN_INPUT);
						ATMFields.debitCard.setX(ATMFields.debitCard.getX()+1000);
					}
				}

				selected = null;
			}
			repaint();
		}
	}
}
