import bagel.Image;
import bagel.Input;
import bagel.util.Point;

/**
 * class of flying platform
 */
public class FlyingPlatform extends NonCharacter {
    private final double MAX_RANDOM_DISPLACEMENT_X;
    private final double HALF_LENGTH;
    private final double HALF_HEIGHT;
    private boolean playerOnPlatform = false;
    private double count = 0;


    private final double RANDOM_SPEED;
    private double direction; // 1 for right, -1 for left

    /**
     * constructor of flying platform
     *
     * @param image                  :flying platform image
     * @param position               :position of flying platform
     * @param speed                  :speed of flying platform
     * @param maxRandomDisplacementX :maxRandomDisplacement of flying platform
     * @param halfLength             : half-length of flying platform
     * @param halfHeight             :half-height of flying platform
     * @param randomSpeed            :random speed of flying platform
     */
    public FlyingPlatform(Image image, Point position, double speed, double maxRandomDisplacementX, double halfLength,
                          double halfHeight, double randomSpeed) {
        super(image, position, speed);
        this.MAX_RANDOM_DISPLACEMENT_X = maxRandomDisplacementX;
        this.HALF_LENGTH = halfLength;
        this.HALF_HEIGHT = halfHeight;
        this.RANDOM_SPEED = randomSpeed;
        this.direction = RandomUtils.nextBoolean() ? 1 : -1;
    }

    /**
     * override method to update status
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        count += RANDOM_SPEED;
        if (count % MAX_RANDOM_DISPLACEMENT_X == 0) {
            direction = -direction;
        }

        super.setPosition(new Point(super.getPosition().x + (direction * RANDOM_SPEED), super.getPosition().y));
        super.update(input);
    }


    /**
     * Returns whether the player is on the platform.
     *
     * @return true if the player is on the platform, false otherwise
     */
    public boolean isPlayerOnPlatform() {
        return playerOnPlatform;
    }

    /**
     * Sets whether the player is on the platform.
     *
     * @param playerOnPlatform true if the player is on the platform, false otherwise
     */
    public void setPlayerOnPlatform(boolean playerOnPlatform) {
        this.playerOnPlatform = playerOnPlatform;
    }

    /**
     * Gets the half-length of the platform.
     *
     * @return the half-length of the platform
     */
    public double getHALF_LENGTH() {
        return HALF_LENGTH;
    }

    /**
     * Gets the half height of the platform.
     *
     * @return the half height of the platform
     */
    public double getHALF_HEIGHT() {
        return HALF_HEIGHT;
    }
}
