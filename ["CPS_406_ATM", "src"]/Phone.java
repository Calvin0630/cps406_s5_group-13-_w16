import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

public class Phone extends JComponent implements ATMMovableFields{
	boolean isNFC;
	int xPos;
	int yPos;
	int height = ATM_GUI.NUM_PAD_DIMENSION * 3/2 * 2/3;
	int width = ATM_GUI.NUM_PAD_DIMENSION * 2/3;
	String yesNFC = "NFC";
	String noNFC = "Non-NFC";
	int PIN, accountNumber;


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

	public int getPIN(){return PIN;}

	public int getAccountNumber(){return  accountNumber;}


	public Rectangle getBounds (){
		return new Rectangle (xPos, yPos, width, height);
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
		g2.setColor(Color.DARK_GRAY);
		RoundRectangle2D body = new RoundRectangle2D.Float (xPos, yPos, width, height, width/5, width/5);
		g2.fill(body);
		g2.setColor(Color.white);
		g2.fillRect(xPos+width/10, yPos+height/10, width*4/5, height*3/4);
		g2.setColor(Color.black);
		String display = noNFC;
		if (isNFC) display = yesNFC;
		g2.drawString(display, (int) body.getX()+width/4, (int) (body.getY()+body.getHeight()/2));
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillOval((int)(body.getX()+body.getWidth()/2-ATM_GUI.NUM_PAD_DIMENSION*1/20),
				(int)(body.getY()+body.getHeight() *87/100), 
				(int) ATM_GUI.NUM_PAD_DIMENSION*1/10,(int) ATM_GUI.NUM_PAD_DIMENSION*1/10);
	}

	@Override
	public void moveField(MouseEvent event) {
		setX((int)event.getPoint().getX()-width/2);
		setY((int)event.getPoint().getY()-height/2);
	}

	@Override
	public boolean collides(RectangularShape item) {
		return item.contains(getBounds());
	}

	@Override
	public boolean equals(ATMMovableFields other) {
		if(other.getClass() == getClass()){
			Phone otherPhone = (Phone) other;
			return otherPhone.getPIN() == getPIN() && otherPhone.getAccountNumber() == getAccountNumber();
		}
		return false;
	}
}
