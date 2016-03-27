import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class ATMFields extends JComponent {
	public static Rectangle printer,cardSlot,cashDispensor;
	public static Ellipse2D  NFC;
	public static Phone nonNFCPhone;
	public static Phone NFCPhone;
	public static Card debitCard;
	public static Cash twentyBill;
	public static Cheque cheque;
	public static Graphics2D g2;

	public ATMFields (){
		cashDispensor = new Rectangle((int) (ATM_GUI.FRAME_WIDTH/2),
				ATM_GUI.numPad.getY() + ATM_GUI.NUM_PAD_DIMENSION/4 + 10,
				(int)(ATM_GUI.screenSize.getHeight()*4/10), 
				(ATM_GUI.NUM_PAD_DIMENSION)/2-5);
		cardSlot = new Rectangle((int)(cashDispensor.getX()+cashDispensor.getWidth()-(ATM_GUI.screenSize.getHeight()*3/10)),
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()/10));
		printer = new Rectangle((int)(cashDispensor.getX()+cashDispensor.getWidth()-(ATM_GUI.screenSize.getHeight()*3/10)),
				(int)(ATM_GUI.screenSize.getHeight()*1/10), 
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()/10));

		nonNFCPhone = new Phone (false,ATM_GUI.FRAME_WIDTH-ATM_GUI.NUM_PAD_DIMENSION*3/2,
				10,0,0);
		NFCPhone = new Phone (true,ATM_GUI.FRAME_WIDTH-ATM_GUI.NUM_PAD_DIMENSION*3/4,
				10,ATM_GUI.accountDatabase.getPIN(),ATM_GUI.accountDatabase.getAccountNumber());
		debitCard = new Card (ATM_GUI.accountDatabase.getAccountNumber(),
				ATM_GUI.FRAME_WIDTH-ATM_GUI.NUM_PAD_DIMENSION*4/3,
				ATM_GUI.FRAME_HEIGHT/3);
		twentyBill = new Cash (20,ATM_GUI.FRAME_WIDTH-ATM_GUI.NUM_PAD_DIMENSION*5/3,
				ATM_GUI.FRAME_HEIGHT*2/4+ATM_GUI.NUM_PAD_DIMENSION*1/10);
		cheque = new Cheque(1906.55,ATM_GUI.FRAME_WIDTH-ATM_GUI.NUM_PAD_DIMENSION*5/3,
				ATM_GUI.FRAME_HEIGHT*3/4);
		setOpaque(false);
	}

	private void drawPrinter(Graphics2D g2){
		g2.draw(printer);
		g2.fillRect((int)(printer.getX()+printer.getWidth()/4), 
				(int)(printer.getY()+printer.getHeight()*2/3),
				(int)printer.getWidth()/2, (int)printer.getHeight()/4);
		g2.drawString("Printer", (int)(printer.getX()+printer.getWidth()/4), 
				(int)(printer.getY()+printer.getHeight()*2/3) - 10);
	}
	private void drawCashDispensor(Graphics2D g2){
		g2.draw(cashDispensor);
		g2.fillRect((int)(cashDispensor.getX()+cashDispensor.getWidth()/8), 
				(int)(cashDispensor.getY()+cashDispensor.getHeight()*2/3),
				(int)cashDispensor.getWidth()*3/4, (int)cashDispensor.getHeight()/4);
		g2.drawString("Cash Dispensor", (int)(cashDispensor.getX()+cashDispensor.getWidth()/4), 
				(int)(cashDispensor.getY()+cashDispensor.getHeight()*2/3) - 10);
	}
	private void drawCardSlot(Graphics2D g2){
		g2.draw(cardSlot);
		g2.fillRect((int)(cardSlot.getX()+cardSlot.getWidth()/4), 
				(int)(cardSlot.getY()+cardSlot.getHeight()*2/3),
				(int)cardSlot.getWidth()/2, (int)cardSlot.getHeight()/4);
		g2.drawString("Insert Card", (int)(cardSlot.getX()+cardSlot.getWidth()/4), 
				(int)(cardSlot.getY()+cardSlot.getHeight()*2/3) - 10);
	}
	private void drawNFC(Graphics2D g2){
		double r = cardSlot.getWidth()*2/3;
		NFC = new Ellipse2D.Double(cardSlot.getX() + cardSlot.getWidth()/6,
				cardSlot.getMaxY()+r/10,
				r,r);
		g2.draw(NFC);
		g2.drawString("Tap NFC Phone", (int)(NFC.getX()+NFC.getWidth()/4), (int)NFC.getCenterY());
	}


	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setColor(ATM_GUI.background);
		g2.fillRect(0,0,(int)ATM_GUI.screenSize.getWidth(),(int)ATM_GUI.screenSize.getHeight());
		g2.setColor(Color.black);
		drawPrinter(g2);
		drawCardSlot(g2);
		drawCashDispensor(g2);
		drawNFC(g2);
		nonNFCPhone.paint(g2);
		NFCPhone.paint(g2);
		debitCard.paint(g2);
		twentyBill.paint(g2);
		cheque.paint(g2);
	}
}

