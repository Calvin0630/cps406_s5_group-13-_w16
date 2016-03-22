import java.awt.* ;
import javax.swing.JComponent;

public class Phone extends JComponent{
	boolean isNFC;
	int xPos;
	int yPos;
	int height = ATM_GUI.NUM_PAD_DIMENSION * 3/2 * 2/3;
	int width = ATM_GUI.NUM_PAD_DIMENSION * 2/3;
	String yesNFC = "NFC";
	String noNFC = "Non-NFC";
	int PIN, accountNumber;
	Rectangle body;

	public Phone (boolean NFC, int x, int y, int newPIN, int accntNmbr){
		isNFC = NFC; 
		xPos = x;
		yPos = y;
		PIN = newPIN;
		accountNumber = accntNmbr;
	}

	public boolean getNFC (){
		return isNFC;
	}

	public int getX(){
		return xPos;
	}

	public void setX(int x){
		xPos = x;
	}

	public int getY(){
		return yPos;
	}

	public void setY(int y){
		yPos = y;
	}

	public Rectangle getBounds (){
		return body.getBounds();
	}

	public int getWidth(){
		return width;
	}

	public int getHeight (){
		return height;
	}

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		body = new Rectangle ( xPos,yPos,width, height);
		g2.draw(body);
		g2.drawOval((int)(body.getX()+body.getWidth()/2-ATM_GUI.NUM_PAD_DIMENSION*1/20),(int)(body.getY()+body.getHeight() * 9/10), 
				(int) ATM_GUI.NUM_PAD_DIMENSION*1/10,(int) ATM_GUI.NUM_PAD_DIMENSION*1/10);
		String display = noNFC;
		if (isNFC) display = yesNFC;
		g2.drawString(display, (int) body.getX()+10, (int) (body.getY()+body.getHeight()/2));
	}

}
