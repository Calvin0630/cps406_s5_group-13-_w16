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
	public static final int TRANSFER = 3;
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
		JLabel item = new JLabel();
		String print = "<html>" + ds.format(dateobj) + "<br>";
		if (action == WITHDRAW)
			print = print.concat("Withdraw:\t" + form.format(amount) + "<br>");
		if (action == DEPOSIT)
			print = print.concat("Deposit:\t" + form.format(amount) + "<br>");
		if (action == TRANSFER)
			print = print.concat("Transfer:\t" + form.format(amount) + "<br>");
		if (account == SAVINGS)
			print = print.concat("Savings Balance:\t" + 
					form.format(ATM_GUI.accountDatabase.getSavingsBalance()));
		if (account == CHEQUING)
			print = print.concat("Chequing Balance:\t" + 
					form.format(ATM_GUI.accountDatabase.getChequingBalance()));
		print = print.concat("</html>");
		item.setText(print);
		messagePane.add(item);
		item.setAlignmentX(Component.CENTER_ALIGNMENT);
		messagePane.add(Box.createRigidArea(new Dimension(width,10)));
		pack();
		repaint();
	}//		
}
