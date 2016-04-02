import javax.swing.*;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;

/**
 * @author Group 13
 * Cash class provides visual components as well as values held by the cash component.
 */
public class Cash extends JComponent implements ATMMovableFields{

	int width = ATM_GUI.NUM_PAD_DIMENSION*3/2, height = width/2;
	int xPos, yPos;
	int value;
	protected static int numBills = 1;

	/**
	 * Cash constructor to initialize variables.
	 * @param val int indicating amount of the bill.
	 * @param x int indicating x position.
	 * @param y int indicating y position
	 */
	public Cash (int val, int x, int y){
		value = val;
		xPos = x;
		yPos = y;
	}

	/**
	 *  Returns x position.
	 *  @return xPos int of x position.
	 */
	public int getX(){
		return xPos;
	}

	/**
	 * Sets x position.
	 * @param x int to set xPos
	 */
	public void setX(int x){
		xPos = x;
	}

	/**
	 * Returns y position.
	 * @return yPos int of y position.
	 */
	public int getY(){
		return yPos;
	}

	/**
	 * Sets y position.
	 * @param y int to set yPos.
	 */
	public void setY(int y){
		yPos = y;
	}

	/**
	 * Returns value of the bill.
	 * @return value int of the amount indicated on the bill.
	 */
	public int getValue (){
		return value;
	}

	/**
	 * Returns a rectangle representing bounds of the bill.
	 * @return a rectangle object.
	 */
	public Rectangle getBounds (){
		return new Rectangle (xPos, yPos, width, height);
	}

	/**
	 * Returns width of the bill.
	 * @return width int of bill.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * Returns height of the bill.
	 * @return height int of bill.
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
		g2.setColor(Color.black);
		int fontSize = height/3;
		Font f = new Font("Times New Roman", Font.BOLD, fontSize);
		g2.setFont(f);
		Rectangle body = new Rectangle ( xPos,yPos,width, height);
		g2.setColor(Color.GREEN);
		g2.fill(body);
		g2.setColor(Color.black);
		
		FontRenderContext context = g2.getFontRenderContext();
		String value = "$"+getValue();
		int textWidth = (int)(f.getStringBounds(value,context).getWidth());
		int textHeight = (int)(f.getStringBounds(value,context).getHeight());
		g2.drawString(value, xPos, yPos+textHeight*2/3);
		g2.drawString(value, xPos+width-textWidth, yPos+height);
		
		g2.drawString("x" + Integer.toString(numBills), xPos, yPos +height);
		int radius = height*2/3;
		g2.setColor(Color.white);
		Ellipse2D.Double circle = new Ellipse2D.Double(getBounds().getCenterX()-radius/2,
				getBounds().getCenterY()-radius/2,radius,radius);
		g2.draw(circle);
		g2.fill(circle);
	}

	@Override
	/**
	 * Using MouseEvent's positon, adjusts position of the bill.
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
