import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;
import bagel.util.Point;

/**
 * this is class of player property and action
 */
public class Player extends Character {
    private final Image IMAGE_LEFT;
    private final Image IMAGE_RIGHT;
    private double score = 0;
    private Image currState;
    private double verticalSpeed = 0;
    private boolean isJumping = false;
    private boolean deadFalling = false;
    private static final double JUMP_SPEED = -20;
    private static final double DEAD_FALL_SPEED = 2;
    private static final double FALL_SPEED = 1;
    private boolean doubleScore = false;
    private boolean invincible = false;
    private boolean isGround = true;
    private boolean isFlyingLand = false;

    /**
     * constructor to initialize a player
     *
     * @param IMAGE_RIGHT :right image
     * @param IMAGE_LEFT  :left image
     * @param position    :coordinate of player
     * @param health      :health point
     * @param radius      :radius number
     */
    public Player(Image IMAGE_RIGHT, Image IMAGE_LEFT, Point position, double health, double radius) {
        super(IMAGE_RIGHT, position, health, radius);
        this.IMAGE_RIGHT = IMAGE_RIGHT;
        this.IMAGE_LEFT = IMAGE_LEFT;
        this.currState = IMAGE_RIGHT;
    }

    /**
     * method to run player own logic
     *
     * @param input :user input
     */
    @Override
    public void update(Input input) {
        if (input.isDown(Keys.LEFT)) {
            currState = IMAGE_LEFT;
        } else if (input.isDown(Keys.RIGHT)) {
            currState = IMAGE_RIGHT;
        }
        if (!deadFalling) {
            if (input.wasPressed(Keys.UP) && !isJumping) {
                verticalSpeed = JUMP_SPEED;
                isJumping = true;
                isGround = false;
            }

            // While jumping
            if ((isJumping | !isFlyingLand) && !isGround) {
                super.setPosition(new Point(super.getPosition().x, super.getPosition().y + verticalSpeed));
                verticalSpeed += FALL_SPEED;
            }

        } else {
            super.setPosition(new Point(super.getPosition().x, super.getPosition().y + verticalSpeed));
            if (super.getHealth() <= 0 && super.getPosition().y > Window.getHeight() + 10) {
                setDead(true);
            }
        }

        if (super.getHealth() <= 0 && !deadFalling) {
            verticalSpeed = DEAD_FALL_SPEED;
            deadFalling = true;
        }
    }


    /**
     * get score point
     *
     * @return double score
     */
    public double getScore() {
        return score;
    }

    /**
     * method to set vertical speed
     *
     * @param verticalSpeed : vertical speed
     */
    public void setVerticalSpeed(double verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    /**
     * method to increase character score point
     *
     * @param value increase number
     */
    public void earnScore(double value) {
        if (doubleScore) {
            value *= 2;
        }
        this.score += value;
    }

    /**
     * method to take damage
     *
     * @param value :damage number
     */
    @Override
    public void takeDamage(double value) {
        if (invincible) {
            super.takeDamage(0.0);
        } else {
            super.takeDamage(value);
        }
    }

    /**
     * method to draw the player image
     */
    @Override
    public void draw() {
        currState.draw(getPosition().x, getPosition().y);
    }


    /**
     * method to reset the player status
     */
    @Override
    public void reset() {
        super.reset();
        score = 0;
        isJumping = false;
        verticalSpeed = 0;
        currState = IMAGE_RIGHT;
        deadFalling = false;
    }

    /**
     * Sets whether the player has double score active.
     *
     * @param doubleScore true if double score is active, false otherwise
     */
    public void setDoubleScore(boolean doubleScore) {
        this.doubleScore = doubleScore;
    }

    /**
     * Sets whether the player is invincible.
     *
     * @param invincible true if the player is invincible, false otherwise
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Checks whether the player is jumping.
     *
     * @return true if the player is jumping, false otherwise
     */
    public boolean isJumping() {
        return isJumping;
    }

    /**
     * Sets whether the player is jumping.
     *
     * @param jumping true if the player is jumping, false otherwise
     */
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    /**
     * Sets whether the player has landed on a flying platform.
     *
     * @param flyingLand true if the player has landed on a flying platform, false otherwise
     */
    public void setFlyingLand(boolean flyingLand) {
        isFlyingLand = flyingLand;
    }

    /**
     * Checks whether the player is on the ground.
     *
     * @return true if the player is on the ground, false otherwise
     */
    public boolean isGround() {
        return isGround;
    }

    /**
     * Sets whether the player is on the ground.
     *
     * @param ground true if the player is on the ground, false otherwise
     */
    public void setGround(boolean ground) {
        isGround = ground;
    }

    /**
     * Checks whether the player is invincible.
     *
     * @return true if the player is invincible, false otherwise
     */
    public boolean isInvincible() {
        return invincible;
    }
}
