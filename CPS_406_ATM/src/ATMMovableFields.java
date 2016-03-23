import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;

/**
 * Created by Lionel on 2016-03-22.
 */
public interface ATMMovableFields {
    void moveField(MouseEvent event);
    boolean collides(RectangularShape item);
}
