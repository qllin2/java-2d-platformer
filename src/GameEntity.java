import bagel.Image;
import bagel.Input;
import bagel.util.Point;


/**
 * abstract class manage all game entity
 */
public abstract class GameEntity {
    private final Image IMAGE;
    private Point position;
    private final Point ORI_POSITION;

    /**
     * constructor of game entity
     *
     * @param IMAGE    :image of game entity
     * @param position :game entity position
     */
    public GameEntity(Image IMAGE, Point position) {
        this.IMAGE = IMAGE;
        this.position = position;
        this.ORI_POSITION = position;
    }

    /**
     * method to get position
     *
     * @return Point this return position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * method to set position
     *
     * @param position : new position data to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * method to access original position
     *
     * @return Point original position
     */
    public Point getORIPosition() {
        return ORI_POSITION;
    }

    /**
     * method to reset position
     */
    public void reset() {
        position = ORI_POSITION;
    }

    /**
     * method to draw image
     */
    public void draw() {
        IMAGE.draw(position.x, position.y);
    }

    /**
     * abstract method update
     *
     * @param input :user input
     */
    public abstract void update(Input input);
}
