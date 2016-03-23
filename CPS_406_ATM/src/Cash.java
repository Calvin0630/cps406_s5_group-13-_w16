import javax.swing.JComponent;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
public class Cash extends JComponent implements ATMMovableFields{
	int width = ATM_GUI.NUM_PAD_DIMENSION*3/2, height = width/2;
	int xPos, yPos;
	int value;
	Rectangle body;

	public Cash (int val, int x, int y){
		value = val;
		xPos = x;
		yPos = y;
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

	public int getValue (){
		return value;
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
		int fontSize = height/3;
		Font f = new Font("Times New Roman", Font.BOLD, fontSize);
		g2.setFont(f);
		g2.draw(body);
		g2.drawString("$"+getValue(), xPos, yPos+height/4);
		g2.drawString("$"+getValue(), xPos+width*3/4, yPos+height);
		int radius = height*2/3;
		Ellipse2D.Double circle = new Ellipse2D.Double(getBounds().getCenterX()-radius/2,
				getBounds().getCenterY()-radius/2,radius,radius);
		g2.draw(circle);

	}

	@Override
	public void moveField(MouseEvent event) {
		xPos = event.getX();
		yPos = event.getY();
		body.setLocation(event.getPoint());
	}

	@Override
	public boolean collides(JComponent item) {
		return item.contains(body.getLocation());
	}
}
