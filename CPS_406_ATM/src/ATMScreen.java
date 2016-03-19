import javax.swing.*;
import java.awt.*;

/**
 * Created by Lionel on 2016-03-18.
 */
public class ATMScreen extends JPanel{
    protected TextArea leftOne;
    protected TextArea leftTwo;
    protected TextArea leftThree;
    protected TextArea rightOne;
    protected TextArea rightTWo;
    protected TextArea rightThree;
    protected TextArea title;
    protected TextArea instruction;
    private int x, y, width, height;

    public ATMScreen(int xPos, int yPos, int wth, int hth){
        setSize(wth, hth);
        setLocation(xPos, yPos);
        x = xPos;
        y = yPos;
        width = wth;
        height = hth;
        title = new TextArea("Test",1,1);
        title.setLocation(x + wth / 2, yPos);

        instruction = new TextArea("Test",1,1);
        instruction.setLocation(title.getX(), yPos + hth / 5);

        leftOne = new TextArea("Test",1,1);
        leftOne.setLocation(xPos, yPos + hth / 5);

        leftTwo = new TextArea("Test",1,1);
        leftTwo.setLocation(xPos, yPos + (2 * (hth / 5)));
    }

}
