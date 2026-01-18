import bagel.Image;
import bagel.util.Point;

/**
 * abstract class that have common attribute and action of character
 */
public abstract class Character extends GameEntity {
    private double health;
    private final double RADIUS;
    private final double ORI_HEALTH;
    private boolean dead = false;

    /**
     * constructor of character
     *
     * @param image    :image of character
     * @param position :position of character
     * @param health   :health point of character
     * @param RADIUS   :radius of character
     */
    public Character(Image image, Point position, double health, double RADIUS) {
        super(image, position);
        ORI_HEALTH = health;
        this.RADIUS = RADIUS;
        this.health = health;
    }

    /**
     * check if character dead
     *
     * @return boolean dead status
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * method to set status
     *
     * @param status :current status
     */
    public void setDead(boolean status) {
        this.dead = status;
    }

    /**
     * get character collision radius
     *
     * @return double radius
     */
    public double getRADIUS() {
        return RADIUS;
    }

    /**
     * method to get health point
     *
     * @return double health point
     */
    public double getHealth() {
        return health;
    }

    /**
     * method to damage to character health point
     *
     * @param damage :damage number
     */
    public void takeDamage(double damage) {
        this.health -= damage;
    }

    /**
     * method of reset health and position
     */
    @Override
    public void reset() {
        super.reset();
        this.health = ORI_HEALTH;
        this.dead = false;
    }
}
