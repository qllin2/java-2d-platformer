import bagel.Image;

import java.util.Properties;

/**
 * class of config, contains fireball information
 */
public class FireballConfig {
    private static Image image;
    private static double radius;
    private static double fireballSpeed;
    private static double damageSize;
    private static double moveSpeed;

    /**
     * load config
     *
     * @param gameProps :game property
     */
    public static void load(Properties gameProps) {
        image = new Image(gameProps.getProperty("gameObjects.fireball.image"));
        radius = Double.parseDouble(gameProps.getProperty("gameObjects.fireball.radius"));
        fireballSpeed = Double.parseDouble(gameProps.getProperty("gameObjects.fireball.speed"));
        damageSize = Double.parseDouble(gameProps.getProperty("gameObjects.fireball.damageSize"));
        moveSpeed = Double.parseDouble(gameProps.getProperty("gameObjects.platform.speed"));
    }

    /**
     * method to get image
     *
     * @return :fireball image object
     */
    public static Image getImage() {
        return image;
    }

    /**
     * method to get radius
     *
     * @return :fireball radius
     */
    public static double getRadius() {
        return radius;
    }

    /**
     * get fireball speed
     *
     * @return fireball speed
     */
    public static double getFireballSpeed() {
        return fireballSpeed;
    }

    /**
     * method get damage size
     *
     * @return :damage size
     */
    public static double getDamageSize() {
        return damageSize;
    }

    /**
     * method to get move speed of fireball
     *
     * @return :fireball move speed
     */
    public static double getMoveSpeed() {
        return moveSpeed;
    }
}
