import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * class to create fireball object
 */
public class Fireball extends CollisionEntity {
    private final double DAMAGE_SIZE;
    private final double FIREBALL_SPEED;
    private boolean active = true;
    private final Shooter SHOOTER;


    /**
     * Enum representing the shooter of the fireball.
     * It can be either the PLAYER or the BOSS.
     */
    public enum Shooter {
        PLAYER,
        BOSS
    }

    /**
     * constructor of fireball
     *
     * @param image         :image of fireball
     * @param position      :position of fireball
     * @param radius        :radius of fireball
     * @param moveSpeed     :move speed of fireball
     * @param fireballSpeed :speed of fireball itself
     * @param damageSize    :damage size of fireball
     */
    public Fireball(Image image, Point position, double radius, double moveSpeed, double fireballSpeed, double damageSize, Shooter shooter) {
        super(image, position, radius, moveSpeed);
        this.DAMAGE_SIZE = damageSize;
        this.FIREBALL_SPEED = fireballSpeed;
        this.SHOOTER = shooter;
    }

    /**
     * method to get active
     *
     * @return boolean fire active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * method to set fireball not active
     */
    public void deActivate() {
        active = false;
    }

    /**
     * method to get damage
     *
     * @return double damage size
     */
    public double getDAMAGE_SIZE() {
        return DAMAGE_SIZE;
    }

    /**
     * Gets the shooter of the fireball.
     *
     * @return the shooter of the fireball, either PLAYER or BOSS
     */
    public Shooter getSHOOTER() {
        return SHOOTER;
    }

    /**
     * override method of update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        super.setPosition(new Point(super.getPosition().x + FIREBALL_SPEED, super.getPosition().y));
        super.update(input);
    }
}
