import javax.swing.*;
import java.awt.*;

class ATMFields extends JComponent {
    Rectangle cardSlot = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            100, 300, 100);
    Rectangle cashDispensor = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            300, 300, 100);
    Rectangle printer = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            500,  300, 100);
    Rectangle nfc = new Rectangle((int) ATM_GUI.screenSize.getWidth() / 2 + 100,
            700,  300, 100);

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(ATM_GUI.background);
        g2.fillRect(0,0,(int)ATM_GUI.screenSize.getWidth(),(int)ATM_GUI.screenSize.getHeight());
        g2.setColor(Color.black);
        g2.draw(cardSlot);
        g2.draw(cashDispensor);
        g2.draw(printer);
        g2.draw(nfc);
    }
}

