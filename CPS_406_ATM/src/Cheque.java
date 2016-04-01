import javax.swing.JComponent;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Group 13
 * Cheque class provides visual components as well as values held by the phone component.
 */
public class Cheque extends JComponent implements ATMMovableFields{

	int width = ATM_GUI.NUM_PAD_DIMENSION*3/2, height = width/2;
	int xPos, yPos;
	double value;

	NumberFormat form= NumberFormat.getCurrencyInstance();
	DateFormat df = new SimpleDateFormat("dd/MM/yy");
	Date dateobj = new Date();
	int chequeNum = 0;

	/**
	 * Constructor to initialize variables.
	 * @param val double variable to indicate amount of cheque.
	 * @param x int to set the x position.
	 * @param y int to set the y position
	 */
	public Cheque (double val, int x, int y){
		value = val;
		xPos = x;
		yPos = y;
		chequeNum++;
	}

	/**
	 *  Returns xPos int of x position.
	 */
	public int getX(){
		return xPos;
	}

	/**
	 * Sets x position
	 * @param x int to set xPos
	 */
	public void setX(int x){
		xPos = x;
	}

	/**
	 *  Returns yPos int of y position.
	 */
	public int getY(){
		return yPos;
	}

	/**
	 * Sets y position.
	 * @param y int to set yPos
	 */
	public void setY(int y){
		yPos = y;
	}

	/**
	 * Returns the indicated value of the cheque.
	 * @return int value
	 */
	public double getValue (){
		return value;
	}

	/**
	 *  Returns a rectangle representing the bounds of the cheque.
	 */
	public Rectangle getBounds (){
		return new Rectangle (xPos, yPos, width, height);
	}

	/**
	 *  Returns the width of the cheque.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 *  Returns the height of the cheque.
	 */
	public int getHeight (){
		return height;
	}

	@Override
	/**
	 * Using MouseEvent's positon, adjusts position of the cheque.
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

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int fontSize = height/10;
		Font f = new Font("Times New Roman", Font.BOLD, fontSize);
		g2.setFont(f);
		Rectangle body = new Rectangle ( xPos,yPos,width, height);
		g2.draw(body);
		g2.setColor(Color.pink);
		g2.fill(body);
		g2.setColor(Color.black);
		g2.drawString("Client name here.", xPos+width/20, yPos+height*1/10);
		g2.drawString(form.format(getValue()), xPos+width*3/4, (int)body.getCenterY());
		g2.drawLine(xPos+width/10, (int)body.getCenterY(), xPos+width*2/3, (int)body.getCenterY());
		g2.drawString(df.format(dateobj), xPos+width*3/4, yPos + height/4);
		g2.drawString(String.format("%011d", ATM_GUI.accountDatabase.getAccountNumber()) + ":"
				+ String.format("%03d", chequeNum), 
				xPos+width/20, yPos+height-width/20);
		g2.drawLine(xPos+width/10, yPos+height*3/4, xPos+width*4/10, yPos+height*3/4);
		g2.drawLine(xPos+width/2, yPos+height*3/4, xPos+width*9/10, yPos+height*3/4);
	}
}
