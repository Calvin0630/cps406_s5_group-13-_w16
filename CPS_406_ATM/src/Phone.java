import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JComponent;

/**
 * 
 * @author Group 13
 * Phone class provides visual components as well as values held by the phone component.
 */
public class Phone extends JComponent implements ATMMovableFields{
	boolean isNFC;
	int xPos;
	int yPos;
	int height = ATM_GUI.NUM_PAD_DIMENSION * 3/2 * 2/3;
	int width = ATM_GUI.NUM_PAD_DIMENSION * 2/3;
	String yesNFC = "NFC";
	String noNFC = "Non-NFC";
	int PIN, accountNumber;

	/**
	 * Constructor initializing variables.
	 * @param NFC boolean determining if phone is NFC enabled or not.
	 * @param x value determining xPos value.
	 * @param y value determining yPos value.
	 * @param newPIN sets the PIN set on the phone.
	 * @param accntNmbr sets the account number the phone is associated with.
	 */
	public Phone (boolean NFC, int x, int y, int newPIN, int accntNmbr){
		isNFC = NFC; 
		xPos = x;
		yPos = y;
		PIN = newPIN;
		accountNumber = accntNmbr;
	}

	/**
	 * Returns NFC boolean.
	 * @return NFC variable.
	 */
	public boolean getNFC (){
		return isNFC;
	}

	/**
	 * Returns xPos variable.
	 */
	public int getX(){
		return xPos;
	}

	/**
	 * Sets the xPos variable.
	 * @param x value to set xPos
	 */
	public void setX(int x){
		xPos = x;
	}

	/**
	 * Returns yPos variable.
	 */
	public int getY(){
		return yPos;
	}

	/**
	 * Sets the yPos variable.
	 * @param y value to set xPos
	 */
	public void setY(int y){
		yPos = y;
	}

	/**
	 * @return PIN variable of phone.
	 */
	public int getPIN(){
		return PIN;
	}

	/**
	 * @return accountNumber of phone.
	 */
	public int getAccountNumber(){
		return  accountNumber;
	}

	/**
	 *  Returns the rectangle with the bounds of the phone.
	 */
	public Rectangle getBounds (){
		return new Rectangle (xPos, yPos, width, height);
	}

	/**
	 * Returns the width of the phone.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 *  Returns the height of the phone.
	 */
	public int getHeight (){
		return height;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
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
	/**
	 *  Given a MouseEvent, the phone is moved accordingly.
	 */
	public void moveField(MouseEvent event) {
		if ((event.getPoint().getX() - width/2) > (ATM_GUI.FRAME_WIDTH/2)) {
			setX((int)event.getPoint().getX()-width/2);
			setY((int)event.getPoint().getY()-height/2);
		}
	}

	@Override
	/**
	 *  Given a rectangle of the bounds of another item, determines if the bounds overlap.
	 */
	public boolean collides(RectangularShape item) {
		return item.contains(getBounds());
	}

	@Override
	/**
	 * Given another object, determines if they containing the same information.
	 */
	public boolean equals(ATMMovableFields other) {
		if(other.getClass() == getClass()){
			Phone otherPhone = (Phone) other;
			return otherPhone.getPIN() == getPIN() && otherPhone.getAccountNumber() == getAccountNumber();
		}
		return false;
	}
}
