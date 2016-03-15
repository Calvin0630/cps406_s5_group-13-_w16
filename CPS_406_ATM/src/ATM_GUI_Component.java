import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

/**
 * Created by Lionel on 2016-03-10.
 */
public class ATM_GUI_Component extends  JComponent{
    private JButton[] numButtons;

    public ATM_GUI_Component(){
        this.
        numButtons =  new JButton[10];
        for (int i = 0; i < 10; i++){
            numButtons[i] = new JButton(Integer.toString(i));
            numButtons[i].setBackground(Color.blue);
            numButtons[i].setVisible(true);
            this.add(numButtons[i]);
        }
        this.setSize(300, 400);
    }

    class ATMMouseListener extends MouseAdapter{


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < 10 ; i++) {
            this.numButtons[i].paint(g);
        }
    }
}
