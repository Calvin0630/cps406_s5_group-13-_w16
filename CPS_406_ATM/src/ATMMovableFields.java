import java.awt.event.MouseEvent;
import java.awt.geom.RectangularShape;

/**
 * @author Group 13
 * Interface ATMMoveableFields with method prototypes.
 */
public interface ATMMovableFields {
    void moveField(MouseEvent event);
    boolean collides(RectangularShape item);
    boolean equals(ATMMovableFields other);
}
