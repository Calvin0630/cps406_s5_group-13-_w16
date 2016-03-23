import java.awt.* ;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class Card extends JComponent implements ATMMovableFields{
	int width = ATM_GUI.NUM_PAD_DIMENSION; 
	int height = width * 2/3;
	int xPos = -(width), yPos = -(height), accountNumber;
	Rectangle body;


	public Card (int accntNmbr, int x, int y)
	{
		xPos = x;
		yPos = y;
		accountNumber = accntNmbr;
		body = new Rectangle ( xPos,yPos,width, height);
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

	public int getAccountNumber (){
		return accountNumber;
	}

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
		g2.setColor(Color.black);
		g2.draw(body);
		g2.drawString("Debit Card", xPos+5, yPos+height/10);
		g2.drawString("0000 0000 000"+getAccountNumber(),(int)(xPos+body.getWidth()/4),(int)(body.getCenterY()));
		g2.drawString("Client's Name Here",xPos+5,yPos+height-5);
	}

	@Override
	public void moveField(MouseEvent event) {
		xPos = event.getX();
		yPos = event.getY();
		body.setLocation(event.getPoint());
	}
}
