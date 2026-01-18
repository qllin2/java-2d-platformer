import bagel.*;
import bagel.util.Point;

/**
 * this is class to create boss enemy
 */
public class EnemyBoss extends Character{
    private final double SPEED;
    private final double activationRadius;
    private static final int DEAD_FALL_SPEED = 2;

    /**
     * constructor of enemy boss
     * @param IMAGE :image of boss
     * @param position :position of boss
     * @param health :health of boss
     * @param radius :radius of boss
     * @param SPEED :speed of boss
     * @param activationRadius :activation radius of boss
     */
    public EnemyBoss(Image IMAGE, Point position, double health,double radius, double SPEED, double activationRadius) {
        super(IMAGE, position, health, radius);
        this.SPEED = SPEED;
        this.activationRadius = activationRadius;
    }

    /**
     * override method update
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        if (input.isDown(Keys.RIGHT)) {
            super.setPosition(new Point(super.getPosition().x - SPEED, super.getPosition().y));
        }
        if (input.isDown(Keys.LEFT)) {
            super.setPosition(new Point(super.getPosition().x + SPEED, super.getPosition().y));
        }
        if (super.getHealth() <= 0) {
            super.setDead(true);
        }
        if (super.isDead()) {
            die();
        }

    }

    /**
     * method to check if counter between boss and player
     *
     * @param character :character object
     * @return boolean collision or not
     */
    public boolean checkCounter(Character character) {
        double collisionRange = activationRadius + character.getRADIUS();
        double distance = super.getPosition().distanceTo(character.getPosition());
        return distance <= collisionRange;
    }

    /**
     * method to set boss die
     */
    public void die() {
        super.setPosition(new Point(super.getPosition().x, super.getPosition().y + DEAD_FALL_SPEED));
        super.setDead(true);
    }

}
