import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * abstract class of consume power-up
 */
public class PowerUP extends CollisionEntity {
    private double remainFrame;
    private boolean active = false;
    private static final int DISAPPEAR_SPEED = -10;
    private boolean doubleScore = false;
    private boolean invincible = false;

    /**
     * constructor
     *
     * @param image    :image of power-up
     * @param position :position of power-up
     * @param speed    :move speed of power-up
     * @param radius   :radius of power-up
     * @param type     :type of power-up
     */
    public PowerUP(Image image, Point position, double speed, double radius, String type, double remainingFrame) {
        super(image, position, speed, radius);
        if (type.equals("Invincible")) {
            invincible = true;
        } else if (type.equals("DoubleScore")) {
            doubleScore = true;
        }
        this.remainFrame = remainingFrame;
    }

    /**
     * method to set active
     */
    public void setActive() {
        active = true;
    }

    /**
     * method to set un-active
     */
    public void unAvailable() {
        active = false;
    }

    /**
     * method to get active status
     *
     * @return boolean active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * method to count time
     */
    public void reduceDuration() {
        this.remainFrame -= 1;
    }

    /**
     * get double score
     *
     * @return boolean of double score
     */
    public boolean isDoubleScore() {
        return doubleScore;
    }

    /**
     * get invincible
     *
     * @return boolean of invincible
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * override method of update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        super.update(input);
        if (active) {
            setPosition(new Point(super.getPosition().x, super.getPosition().y + DISAPPEAR_SPEED));
            reduceDuration();
            if (remainFrame <= 0) {
                unAvailable();
            }
        }
    }
}
