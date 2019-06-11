import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;


/**
 * @author Alyssa Wang and Nayaab Ali
 * @version 1
 * June 10, 2019
 * Time spent: 1 hour
 * Retrieves the end point of the stage
 *
 *  Variable        Type       Purpose
 *  endPoint        Polygon    marks the endPoint
 *  spinning            Boolean    returns true if spinning, false if not
 */
public class EndOfStagePoint {
    Polygon endPoint;
    boolean spinning;

    /**
     * Constructor.
     * @param componentsGroup (required) defines group to which EndOfStagePoint will be added
     * @param xCoord (required) defines x coordinate at which EndOfStagePoint will be placed
     * @param yCoord (required) defines y coordinate at which EndOfStagePoint will be placed
     */
    public EndOfStagePoint(Group componentsGroup, double xCoord, double yCoord) {
        endPoint = new Polygon();
        endPoint.getPoints().addAll(new Double[]{3.0, 0.0, 5.0, 5.0, 0.0, 2.0, 6.0, 2.0, 1.0, 5.0, 3.0, 0.0});
        endPoint.setScaleX(10);
        endPoint.setScaleY(10);
        endPoint.setTranslateX(xCoord);
        endPoint.setTranslateY(yCoord);
        endPoint.setFill(Color.YELLOW);
        componentsGroup.getChildren().add(endPoint);

        ScaleTransition st = new ScaleTransition(Duration.millis(1500), endPoint);
        st.setByX(3f);
        st.setByY(3f);
        st.setAutoReverse(true);
        st.setCycleCount(Animation.INDEFINITE);
        st.play();
    }

    /**
     * Cause EndOfStagePoint to begin spinning.
     */
    public void spin() {
        if(!spinning) {
            RotateTransition rt = new RotateTransition(Duration.millis(1000), endPoint);
            rt.setByAngle(360);
            rt.setAutoReverse(false);
            rt.setCycleCount(Animation.INDEFINITE);
            rt.play();
            spinning = true;
        }
    }

    /**
     * Gets the end point
     * @return endpoint.getBoundsInParent(); returns boundary (end point)
     */
    public Bounds getBounds() {
        return endPoint.getBoundsInParent();
    }
}
