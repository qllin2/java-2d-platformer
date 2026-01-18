import bagel.Image;
import bagel.util.Point;

/**
 * subclass of Non-player contain check collision method
 */
public abstract class CollisionEntity extends NonCharacter {
    private final double RADIUS;

    /**
     * constructor of collision entity
     *
     * @param image    :image of collision entity
     * @param position :position of collision entity
     * @param speed    :move speed of collision entity
     * @param radius   :radius of collision entity
     */
    public CollisionEntity(Image image, Point position, double speed, double radius) {
        super(image, position, speed);
        this.RADIUS = radius;
    }

    /**
     * method to check if collision between character and entity
     *
     * @param character :character object
     * @return boolean collision or not
     */
    public boolean checkCollision(Character character) {
        double collisionRange = RADIUS + character.getRADIUS();
        double distance = super.getPosition().distanceTo(character.getPosition());
        return distance <= collisionRange;
    }
}
