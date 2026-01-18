import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

/**
 * class of Non character
 */
public abstract class NonCharacter extends GameEntity {
    private final double SPEED;

    /**
     * constructor of non player
     *
     * @param image    :image of non player
     * @param position :coordinate of non player
     * @param speed    :speed of non player
     */
    public NonCharacter(Image image, Point position, double speed) {
        super(image, position);
        this.SPEED = speed;
    }

    /**
     * override method of update
     *
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
    }
}