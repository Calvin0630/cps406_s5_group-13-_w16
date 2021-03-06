import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Group 13
 * ATM_GUI is the class containing the essentials of the ATM simulator.
 * From this class, the other classes are called and utilised to simulate an ATM.
 *
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
	public static Receipt receipt;
	public static AccountDatabase accountDatabase;
	public ATMFieldsMouseListener fieldsListener;
	public static ATMFields fields;
	protected static String selected;

	/**
	 * ATM_GUI constructor creating the frame and its components.
	 */
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
		fields = new ATMFields();
		add(fields);
		fieldsListener = new ATMFieldsMouseListener();
		addMouseListener(fieldsListener);
		addMouseMotionListener(fieldsListener);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Opens a JOptionPane dialog to get essential account information prior to simulation drawing to screen,
	 */
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
					if(Double.parseDouble(creditField.getText()) < 0){
						throw new Exception("Invalid credit amount");
					}
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
	/**
	 * 
	 * @author Group 13
	 * ATMListener class is a listener dedicated to the interactive buttons 
	 * for the user to click, and how to respond to them.
	 *
	 */
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
			if(event.getSource().equals(optionPanel.enter)){
				screen.enter();
			}
			if(event.getSource().equals(screenLeftButtons.leftOne)){
				if(ATMScreen.leftOneFunc != -1){
					screen.setCurrentScreen(ATMScreen.leftOneFunc);
				}
			}
			if(event.getSource().equals(screenLeftButtons.leftTwo)){
				if(ATMScreen.leftTwoFunc != -1){
					screen.setCurrentScreen(ATMScreen.leftTwoFunc);
				}
			}
			if(event.getSource().equals(screenLeftButtons.leftThree)){
				if(ATMScreen.leftThreeFunc != -1){
					screen.setCurrentScreen(ATMScreen.leftThreeFunc);
				}
			}
			if(event.getSource().equals(screenRightButtons.rightOne)){
				if(ATMScreen.rightOneFunc != -1){
					screen.setCurrentScreen(ATMScreen.rightOneFunc);
				}
			}
			if(event.getSource().equals(screenRightButtons.rightTwo)){
				if(ATMScreen.rightTwoFunc != -1){
					screen.setCurrentScreen(ATMScreen.rightTwoFunc);
				}
			}
			if(event.getSource().equals(screenRightButtons.rightThree)){
				if(ATMScreen.rightThreeFunc != -1){
					screen.setCurrentScreen(ATMScreen.rightThreeFunc);
				}
			}
			repaint();
		}
	}
	/**
	 * 
	 * @author Group 13
	 * ATMFieldsMouseListener is a MouseAdapter that reacts to actions such as dragging
	 * and releasing, and how it interprets these actions in accordance to the components.
	 *
	 */
	class ATMFieldsMouseListener extends MouseAdapter {
		/**
		 * mouseDragged contains information to react to a mouse click being held down
		 * and the mouse removes, and how to treat the components in this case.
		 * @param e is a MouseEvent containing the point of a mouse during a drag,
		 */
		public void mouseDragged(MouseEvent e){
			if(ATMFields.NFCPhone.getBounds().contains(e.getPoint())) {
				if (selected != "nonNFCPhone" && selected != "debitCard" && 
						selected != "cheque" && selected != "twentyBill")
				{
					ATMFields.NFCPhone.moveField(e);
					selected="NFCPhone";
				}
			}
			if(ATMFields.nonNFCPhone.getBounds().contains(e.getPoint())){
				if (selected != "NFCPhone" && selected != "debitCard" && 
						selected != "cheque" && selected != "twentyBill")
				{ATMFields.nonNFCPhone.moveField(e);
				selected="nonNFCPhone";
				}
			}
			if(ATMFields.debitCard.getBounds().contains(e.getPoint())){
				if (selected != "nonNFCPhone" && selected != "NFCPhone" && 
						selected != "cheque" && selected != "twentyBill")
				{
					ATMFields.debitCard.moveField(e);
					selected="debitCard";
				}
			}
			if(ATMFields.cheque.getBounds().contains(e.getPoint())){
				if (selected != "nonNFCPhone" && selected != "debitCard" && 
						selected != "NFCPhone" && selected != "twentyBill")
				{
					ATMFields.cheque.moveField(e);
					selected="cheque";
				}
			}
			if(ATMFields.twentyBill.getBounds().contains(e.getPoint())){
				if (selected != "nonNFCPhone" && selected != "debitCard" && 
						selected != "cheque" && selected != "NFCPhone")
				{
					ATMFields.twentyBill.moveField(e);
					selected="twentyBill";
				}
			}

			/*
			 * Use Case #1 User accessing with phone.
			 */
			try{
				if (screen.getCurrentScreen() == ATMScreen.PIN_INPUT && selected.equals("NFCPhone")){
					if (ATMFields.NFCPhone.getBounds().intersects(ATMFields.NFC.getBounds())){
						if (ATMFields.NFCPhone.getPIN() == accountDatabase.getPIN()){
							screen.setCurrentScreen(ATMScreen.MAIN_MENU);
						}
					}
				}
			}catch(Exception phone){}
			repaint();
		}
		/**
		 * mouseReleased contains information to react to a mouse click being released
		 *  and how to treat the components in this case.
		 * @param e is a MouseEvent containing the point of a mouse during the release of a click.
		 */
		public void mouseReleased(MouseEvent e) {
			if(selected != null) {
				/*
				 * Use Case #3. User accessing with card
				 */
				if (screen.getCurrentScreen() == ATMScreen.WELCOME && selected.equals("debitCard"))
				{
					/*
					 * Daniel Jack
					 * Use Case 4.Card Read Error
					 */
					try{
						if (ATMFields.debitCard.getBounds().intersects(ATMFields.cardSlot.getBounds())){
							ATMFields.debitCard.setVisible(false);
							screen.setCurrentScreen(ATMScreen.PIN_INPUT);
							ATMFields.debitCard.setX(ATMFields.debitCard.getX()+1000);
							receipt = new Receipt();
						}
					}catch(Exception cardError){
						JOptionPane.showMessageDialog(null, "Card insert error!");
					}
				}
				if (screen.getCurrentScreen() == ATMScreen.DEPOSIT && selected.equals("twentyBill"))
				{
					
					if (ATMFields.twentyBill.getBounds().intersects(ATMFields.cashDispensor.getBounds())){
						ATMFields.twentyBill.setVisible(false);
						screen.item = ATMFields.twentyBill;
						screen.setCurrentScreen(ATMScreen.DEPOSIT_CASH);
						ATMFields.twentyBill.setX(ATMFields.twentyBill.getX()+1000);
					}
				}
				if (screen.getCurrentScreen() == ATMScreen.DEPOSIT && selected.equals("cheque"))
				{
					
					if (ATMFields.cheque.getBounds().intersects(ATMFields.cashDispensor.getBounds())){
						ATMFields.cheque.setVisible(false);
						screen.item = ATMFields.cheque;
						screen.setCurrentScreen(ATMScreen.DEPOSIT_CHEQUE);
						ATMFields.cheque.setX(ATMFields.cheque.getX()+1000);
					}
				}
				selected = null;
			}
			repaint();
		}
	}

}
