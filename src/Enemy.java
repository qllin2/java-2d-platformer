import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * class of enemy properties
 */
public class Enemy extends CollisionEntity {
    private final double DAMAGE_SIZE;
    private boolean danger = true;
    private final double MAX_RANDOM_DISPLACEMENT_X;
    private final double RANDOM_SPEED;
    private double direction; // 1 for right, -1 for left
    private double count = 0;

    /**
     * constructor of enemy
     *
     * @param enemyImage             :image of enemy
     * @param position               :enemy position
     * @param radius                 :enemy radius
     * @param speed                  :enemy move speed
     * @param damageSize             :enemy cause damage size
     * @param maxRandomDisplacementX :maximum random distance
     * @param randomSpeed            :random speed of enemy
     */
    public Enemy(Image enemyImage, Point position, double radius, double speed, double damageSize,
                 double maxRandomDisplacementX, double randomSpeed) {
        super(enemyImage, position, speed, radius);
        this.DAMAGE_SIZE = damageSize;
        this.MAX_RANDOM_DISPLACEMENT_X = maxRandomDisplacementX;
        this.RANDOM_SPEED = randomSpeed;
        this.direction = RandomUtils.nextBoolean() ? 1 : -1;
    }

    /**
     * override method of update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        count += RANDOM_SPEED;
        if (count % MAX_RANDOM_DISPLACEMENT_X == 0) {
            direction = -direction;
        }
        super.setPosition(new Point(super.getPosition().x + direction * RANDOM_SPEED, super.getPosition().y));
        super.update(input);

    }

    /**
     * method to set enemy not danger
     */
    public void notDanger() {
        danger = false;
    }

    /**
     * method to see if enemy still danger or not
     *
     * @return boolean danger
     */
    public boolean showDanger() {
        return danger;
    }

    /**
     * method to get damage size
     *
     * @return double damage size
     */
    public double getDAMAGE_SIZE() {
        return DAMAGE_SIZE;
    }

    /**
     * method to reset enemy status
     */
    @Override
    public void reset() {
        super.reset();
        danger = true;
        count = 0;
        direction = RandomUtils.nextBoolean() ? 1 : -1;
    }

}
