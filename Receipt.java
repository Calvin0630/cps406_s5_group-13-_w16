import java.awt.*;
import java.text.*;
import java.util.Date;
import javax.swing.*;


public class Receipt extends JFrame {
	/*
	 * Use Case 14. Print Receipt
	 */
	DateFormat df = new SimpleDateFormat("dd/MM/yy     hh:mm a");
	DateFormat ds = new SimpleDateFormat("hh:mm a");
	NumberFormat form= NumberFormat.getCurrencyInstance();
	Date dateobj;

	JLabel welcome = new JLabel ("Bank ATM System"),
			slogan = new JLabel ("Do Banking Things"),
			date;

	public static final int WITHDRAW = 1;
	public static final int DEPOSIT  = 2;
	public static final int SAVINGS  = 4;
	public static final int CHEQUING = 5;

	public JPanel messagePane;
	public Boolean visible = false;
	int width = (int)ATMFields.printer.getWidth();
	int xPos = (int)ATMFields.printer.getX();
	int yPos = (int)ATMFields.printer.getY();

	public Receipt (){
		setLocation(xPos,yPos);
		setVisible(visible);
		setResizable(false);
		setAlwaysOnTop(true);

		dateobj = new Date();
		date = new JLabel (df.format(dateobj));

		messagePane = new JPanel();
		messagePane.setBackground(Color.white);
		messagePane.setLayout(new BoxLayout(messagePane, BoxLayout.PAGE_AXIS));

		welcome.setFont(new Font("Times New Roman", Font.BOLD, 20));
		messagePane.add(welcome);
		welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
		slogan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		messagePane.add(slogan);
		slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
		messagePane.add(date);
		date.setAlignmentX(Component.CENTER_ALIGNMENT);

		messagePane.add(Box.createRigidArea(new Dimension(width,25)));

		JPanel oldBalance = new JPanel (new GridLayout(2,2));
		oldBalance.setSize(width,width);
		JLabel startCheq = new JLabel ("Chequing Balance:");
		JLabel cheqBalance = new JLabel(form.format(ATM_GUI.accountDatabase.getChequingBalance()));
		JLabel startSave = new JLabel ("Savings Balance:");
		JLabel saveBalance = new JLabel(form.format(ATM_GUI.accountDatabase.getSavingsBalance()));
		oldBalance.add(startCheq);
		oldBalance.add(cheqBalance);
		oldBalance.add(startSave);
		oldBalance.add(saveBalance);
		oldBalance.setOpaque(false);
		messagePane.add(oldBalance);

		messagePane.add(Box.createRigidArea(new Dimension(width,25)));

		getContentPane().add(messagePane); 
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		pack(); 
	}

	public void setVisibility (boolean state){
		visible = state;
		setVisible(visible);
		pack();
		repaint();
	}

	public void addItem(int action, double amount, int account){
		JPanel newBalance = new JPanel (new GridLayout(4,2));
		newBalance.setSize(width,width);
		JLabel time = new JLabel (ds.format(dateobj));
		newBalance.add(time);
		JLabel blank = new JLabel ("");
		newBalance.add(blank);
		JLabel withdrawLabel, depositLabel, value;
		if (action == WITHDRAW){
			withdrawLabel = new JLabel ("Withdraw:");
			newBalance.add(withdrawLabel);
		}
		if (action == DEPOSIT){
			depositLabel = new JLabel ("Deposit:");
			newBalance.add(depositLabel);
		}
		value = new JLabel (form.format(amount));
		newBalance.add(value);
		JLabel saveLabel, cheqLabel, balanceLabel;
		if (account == SAVINGS){
			saveLabel = new JLabel ("Savings Balance:");
			newBalance.add(saveLabel);
			balanceLabel = new JLabel (form.format(ATM_GUI.accountDatabase.getSavingsBalance()));
			newBalance.add(balanceLabel);
		}
		if (account == CHEQUING){
			cheqLabel = new JLabel ("Chequing Balance:");
			newBalance.add(cheqLabel);
			balanceLabel = new JLabel (form.format(ATM_GUI.accountDatabase.getChequingBalance()));
			newBalance.add(balanceLabel);
		}
		newBalance.setOpaque(false);
		messagePane.add(newBalance);
		messagePane.add(Box.createRigidArea(new Dimension(width,10)));
		pack();
		repaint();
	}
}
