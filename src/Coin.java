import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * subclass of CollisionEntity
 * class to create coin object
 */
public class Coin extends CollisionEntity {
    private final double VALUE;
    private static final double DISAPPEAR_SPEED = -10.0;
    private boolean available = true;

    /**
     * constructor of coin object
     *
     * @param coinImage :image of coin
     * @param position  :position of coin
     * @param speed     :move speed of coin
     * @param radius    :radius of coin
     * @param value     :earn value of coin
     */
    public Coin(Image coinImage, Point position, double speed, double radius, double value) {
        super(coinImage, position, speed, radius);
        this.VALUE = value;
    }

    /**
     * override method of update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        if (available) {
            super.update(input);
        } else {
            setPosition(new Point(getPosition().x, getPosition().y + DISAPPEAR_SPEED));
            super.update(input);
        }
    }

    /**
     * method to set coin unavailable
     */
    public void notAvailable() {
        available = false;
    }

    /**
     * method to show coin availability
     *
     * @return boolean available or not
     */
    public boolean showAvailable() {
        return available;
    }

    /**
     * get the coin value
     *
     * @return double coin value
     */
    public double getVALUE() {
        return VALUE;
    }

    /**
     * method to reset the coin status
     */
    @Override
    public void reset() {
        super.reset();
        available = true;
    }
}
