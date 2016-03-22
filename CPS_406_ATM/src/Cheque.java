import javax.swing.JComponent;
import java.awt.* ;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Cheque extends JComponent{
	int width = ATM_GUI.NUM_PAD_DIMENSION*3/2, height = width/2;
	int xPos, yPos;
	double value;
	NumberFormat form= NumberFormat.getCurrencyInstance();
	DateFormat df = new SimpleDateFormat("dd/MM/yy");
    Date dateobj = new Date();
	
	
	public Cheque (double val, int x, int y){
		value = val;
		xPos = x;
		yPos = y;
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

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		int fontSize = height/10;
		Font f = new Font("Times New Roman", Font.BOLD, fontSize);
		g2.setFont(f);
		Rectangle body = new Rectangle ( xPos,yPos,width, height);
		g2.draw(body);
		g2.drawString("Client name here.", xPos+width/10, yPos+height/3);
		g2.drawString(form.format(getValue()), xPos+width*3/4, (int)body.getCenterY());
		g2.drawString(df.format(dateobj), xPos+width*3/4, yPos + height/4);
	}
}
