import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import javax.swing.JComponent;

/**
 * @author Group 13
 * Card class provides visual components as well as values held by the card component.
 */
public class Card extends JComponent implements ATMMovableFields{
	
	int width = ATM_GUI.NUM_PAD_DIMENSION; 
	int height = width * 2/3;
	int xPos = -(width), yPos = -(height), accountNumber;
	Rectangle body;

	/**
	 * Constructor to initialize variables.
	 * @param accntNmbr int to indicate accountNumber associated with card.
	 * @param x int indicating x position.
	 * @param y int indicating y position.
	 */
	public Card (int accntNmbr, int x, int y)
	{
		xPos = x;
		yPos = y;
		accountNumber = accntNmbr;
	}

	/**
	 *  Returns x position of card.
	 *  @return xPos int of x position.
	 */
	public int getX(){
		return xPos;
	}

	/**
	 * Sets the x position of card given parameter input.
	 * @param x int to set xPos.
	 */
	public void setX(int x){
		xPos = x;
	}

	/**
	 * Returns y position of card.
	 * @return yPos int of y position.
	 */
	public int getY(){
		return yPos;
	}

	/**
	 * Sets the y position of card given parameter input.
	 * @param y int to set yPos
	 */
	public void setY(int y){
		yPos = y;
	}

	/**
	 * Returns account number associated with the card.
	 * @return accountNumber int associated with the card.
	 */
	public int getAccountNumber (){
		return accountNumber;
	}

	/**
	 * Returns a rectangle representing bounds of the card.
	 * @return a rectangle object.
	 */
	public Rectangle getBounds (){
		return new Rectangle (xPos, yPos, width, height);
	}

	/**
	 * Returns width of the card.
	 * @return width int of card.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * Returns height of the card.
	 * @return height int of card.
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
		Rectangle body = new Rectangle (xPos,yPos,width, height);
		g2.draw(body);
		g2.setColor(Color.WHITE);
		g2.fill(body);
		g2.setColor(Color.black);
		g2.drawString("Debit Card", xPos+5, yPos+height/10);
		g2.drawString("0000 0000 000"+getAccountNumber(),(int)(xPos+body.getWidth()/4),(int)(body.getCenterY()));
		g2.drawString("Client's Name Here",xPos+5,yPos+height-5);
	}

	@Override
	/**
	 * Using MouseEvent's positon, adjusts position of the card.
	 * @param event is a MouseEvent containing point of mouse.
	 */
	public void moveField(MouseEvent event) {
		if ((event.getPoint().getX() - width/2) > (ATM_GUI.FRAME_WIDTH/2)) {
			setX((int)event.getPoint().getX()-width/2);
			setY((int)event.getPoint().getY()-height/2);
		}
	}

	@Override
	/**
	 * Given the Rectangular shape of an item, determines if the bounds overlap.
	 * @param item is a RectangularShape object.
	 */
	public boolean collides(RectangularShape item) {
		return item.contains(getBounds());
	}

	@Override
	/**
	 * Determines if object is equal to another object, which is never true.
	 * @param other another ATMMoveableFields object.
	 * @return false
	 */
	public boolean equals(ATMMovableFields other) {
		return false;
	}
}
