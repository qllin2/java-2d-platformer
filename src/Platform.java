import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

/**
 * this is a platform class
 */
public class Platform extends NonCharacter {

    /**
     * constructor of platform
     *
     * @param platformImage :image of platform
     * @param position      :platform coordinate
     * @param speed         :platform move speed
     */
    public Platform(Image platformImage, Point position, double speed) {
        super(platformImage, position, speed);
    }

    /**
     * override method of update
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        if (input.isDown(Keys.RIGHT)) {
            super.update(input);
        }
        if (input.isDown(Keys.LEFT) && getPosition().x < 3000) {
            super.update(input);
        }
    }

}
