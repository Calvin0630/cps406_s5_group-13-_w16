import javax.swing.JComponent;
import java.awt.* ;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Cheque extends JComponent implements ATMMovableFields{
	int width = ATM_GUI.NUM_PAD_DIMENSION*3/2, height = width/2;

	int xPos, yPos;
	double value;

	NumberFormat form= NumberFormat.getCurrencyInstance();
	DateFormat df = new SimpleDateFormat("dd/MM/yy");
	Date dateobj = new Date();
	int chequeNum = 0;
	public Cheque (double val, int x, int y){
		value = val;
		xPos = x;
		yPos = y;
		chequeNum++;
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

	public double getValue (){

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

	@Override
	public void moveField(MouseEvent event) {
		if ((event.getPoint().getX() - width/2) > (ATM_GUI.FRAME_WIDTH/2)) {
			setX((int)event.getPoint().getX()-width/2);
			setY((int)event.getPoint().getY()-height/2);
		}
	}

	@Override
	public boolean collides(RectangularShape item) {
		return item.contains(getBounds());
	}

	@Override
	public boolean equals(ATMMovableFields other) {
		return false;
	}


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
