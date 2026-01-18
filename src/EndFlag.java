import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * subclass of CollisionEntity
 * use to create end flag object
 */
public class EndFlag extends CollisionEntity {

    /**
     * constructor of end flag
     *
     * @param endFlagImage :image of end flag
     * @param position     :end flag position
     * @param speed        :move speed of end flag
     * @param radius       :end flag radius
     */
    public EndFlag(Image endFlagImage, Point position, double speed, double radius) {
        super(endFlagImage, position, speed, radius);
    }

    /**
     * method to override update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        super.update(input);
    }


}
