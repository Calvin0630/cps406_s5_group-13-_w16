import javax.swing.*;
import java.awt.*;

class ATMFields extends JComponent {
	public Rectangle printer,cardSlot,cashDispensor;

	public void drawPrinter(Graphics2D g2){
		printer = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + ATM_GUI.NUM_PAD_DIMENSION/10,
				(int)(ATM_GUI.screenSize.getHeight()*1/10), 
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()/10));
		g2.draw(printer);
		g2.fillRect((int)(printer.getX()+printer.getWidth()/4), 
				(int)(printer.getY()+printer.getHeight()*2/3),
				(int)printer.getWidth()/2, (int)printer.getHeight()/4);
		g2.drawString("Printer", (int)(printer.getX()+printer.getWidth()/4), 
				(int)(printer.getY()+printer.getHeight()*2/3) - 10);
	}
	public void drawCardSlot(Graphics2D g2){
		cardSlot = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + ATM_GUI.NUM_PAD_DIMENSION/10,
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()*3/10), 
				(int)(ATM_GUI.screenSize.getHeight()/10));
		g2.draw(cardSlot);
		g2.fillRect((int)(cardSlot.getX()+cardSlot.getWidth()/4), 
				(int)(cardSlot.getY()+cardSlot.getHeight()*2/3),
				(int)cardSlot.getWidth()/2, (int)cardSlot.getHeight()/4);
		g2.drawString("Insert Card", (int)(cardSlot.getX()+cardSlot.getWidth()/4), 
				(int)(cardSlot.getY()+cardSlot.getHeight()*2/3) - 10);
	}
	public void drawCashDispensor(Graphics2D g2){
		cashDispensor = new Rectangle((int) (ATM_GUI.screenSize.getWidth() / 2 + ATM_GUI.NUM_PAD_DIMENSION/10 -ATM_GUI.screenSize.getHeight()/10),
				ATM_GUI.numPad.getY() + ATM_GUI.NUM_PAD_DIMENSION/4 + 10,
				(int)(ATM_GUI.screenSize.getHeight()*4/10), 
				(ATM_GUI.NUM_PAD_DIMENSION)/2-5);
		g2.draw(cashDispensor);
		g2.fillRect((int)(cashDispensor.getX()+cashDispensor.getWidth()/8), 
				(int)(cashDispensor.getY()+cashDispensor.getHeight()*2/3),
				(int)cashDispensor.getWidth()*3/4, (int)cashDispensor.getHeight()/4);
		g2.drawString("Cash Dispensor", (int)(cashDispensor.getX()+cashDispensor.getWidth()/4), 
				(int)(cashDispensor.getY()+cashDispensor.getHeight()*2/3) - 10);
	}
	public void drawNFC(Graphics2D g2){
		Rectangle nfc = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
				700,  300, 100);
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(ATM_GUI.background);
		g2.fillRect(0,0,(int)ATM_GUI.screenSize.getWidth(),(int)ATM_GUI.screenSize.getHeight());
		g2.setColor(Color.black);
		drawPrinter(g2);
		drawCardSlot(g2);
		drawCashDispensor(g2);
	}
}

