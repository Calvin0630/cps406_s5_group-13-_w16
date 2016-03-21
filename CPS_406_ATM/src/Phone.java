import java.awt.* ;
/**
 * 
 * @author Daniel
 *
 */
public class Phone {
	boolean isNFC;
	int xPos;
	int yPos;
	int height;
	int width;
	String yesNFC = "NFC";
	String noNFC = "Non-NFC";
	Rectangle body;
	
	public Phone (boolean NFC, int size, int x, int y){
		isNFC = NFC;
		width = size*2/3;
		height = size * 3/2 * 2/3;
		xPos = x;
		yPos = y;
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
	
	public Shape getBounds (){
		return body.getBounds();
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight (){
		return height;
	}
	
	public void draw(Graphics2D g2)
    {
		body = new Rectangle ( xPos,yPos,width, height);
		g2.draw(body);
		g2.drawOval((int)(body.getX()+body.getWidth()/2-ATM_GUI.NUM_PAD_DIMENSION*1/20),(int)(body.getY()+body.getHeight() * 9/10), 
				(int) ATM_GUI.NUM_PAD_DIMENSION*1/10,(int) ATM_GUI.NUM_PAD_DIMENSION*1/10);
		String display = noNFC;
		if (isNFC) display = yesNFC;
		g2.drawString(display, (int) body.getX()+10, (int) (body.getY()+body.getHeight()/2));
    }

}
