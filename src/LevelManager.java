
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

/**
 * class to control the level of game
 */
public class LevelManager {

    private static LevelManager instance = null;
    private Player player;
    private Platform platform;
    private final List<FlyingPlatform> FLYING_PLATFORM_LIST = new ArrayList<>();
    private final List<Coin> COINS_LIST = new ArrayList<>();
    private final List<Enemy> ENEMY_LIST = new ArrayList<>();
    private EndFlag endFlag;
    private final List<PowerUP> POWERUP_LIST = new ArrayList<>();
    private final List<Fireball> FIREBALL_LIST = new ArrayList<>();
    private EnemyBoss enemyBoss;
    private boolean startGame = false;
    private boolean endGame = false;
    private boolean winGame = false;
    private boolean lostGame = false;
    private int counterFrame = 0;
    private boolean bossDefeated = true;
    private static final double PLAYER_LEG_LENGTH = 50;

    /**
     * singleton class
     */
    private LevelManager() {
    }

    /**
     * create only one instance
     *
     * @return only instance
     */
    public static LevelManager getInstance() {
        if (instance == null) {
            instance = new LevelManager();
        }
        return instance;
    }

    /**
     * choose what level to load
     *
     * @param level :level number
     * @param game_props : game property
     */
    public void loadLevel(int level, Properties game_props) {
        switch (level) {
            case 1: {
                String path = game_props.getProperty("level1File");
                String[][] levelData = IOUtils.readCsv(path);
                loadLevel1(game_props, levelData);
                break;
            }
            case 2: {
                String path = game_props.getProperty("level2File");
                String[][] levelData = IOUtils.readCsv(path);
                loadLevel2(game_props, levelData);
                break;
            }
            case 3: {
                String path = game_props.getProperty("level3File");
                String[][] levelData = IOUtils.readCsv(path);
                loadLevel3(game_props, levelData);
                break;
            }
        }
    }

    /**
     * load level 1 world csv
     *
     * @param game_props :game property file
     * @param level1Data : 2d array of level1
     */
    private void loadLevel1(Properties game_props, String[][] level1Data) {
        for (String[] entry : level1Data) {
            String type = entry[0];
            double x = Double.parseDouble(entry[1]);
            double y = Double.parseDouble(entry[2]);
            Point position = new Point(x, y);
            switch (type.toLowerCase()) {
                case "player": {
                    player = new Player(new Image(game_props.getProperty("gameObjects.player.imageRight")),
                            new Image(game_props.getProperty("gameObjects.player.imageLeft")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.player.health")),
                            Double.parseDouble(game_props.getProperty("gameObjects.player.radius")));
                    break;
                }
                case "coin": {
                    COINS_LIST.add(new Coin(new Image(game_props.getProperty("gameObjects.coin.image")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.coin.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.coin.radius")),
                            Double.parseDouble(game_props.getProperty("gameObjects.coin.value"))));
                    break;
                }
                case "enemy": {
                    ENEMY_LIST.add(new Enemy(new Image(game_props.getProperty("gameObjects.enemy.image")),
                            position, Double.parseDouble(game_props.getProperty("gameObjects.enemy.radius")),
                            Double.parseDouble(game_props.getProperty("gameObjects.enemy.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.enemy.damageSize")),
                            Double.parseDouble(game_props.getProperty("gameObjects.enemy.maxRandomDisplacementX")),
                            Double.parseDouble(game_props.getProperty("gameObjects.enemy.randomSpeed"))));
                    break;
                }
                case "platform": {
                    platform = new Platform(new Image(game_props.getProperty("gameObjects.platform.image")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.platform.speed")));
                    break;
                }
                case "end_flag": {

                    endFlag = new EndFlag(new Image(game_props.getProperty("gameObjects.endFlag.image")),
                            position,
                            Double.parseDouble(game_props.getProperty("gameObjects.endFlag.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.endFlag.radius")));
                    break;
                }
            }
        }
    }

    /**
     * load level2 world file
     *
     * @param game_props :game property file
     * @param level2Data :2d array of level2 data
     */
    public void loadLevel2(Properties game_props, String[][] level2Data) {
        loadLevel1(game_props, level2Data);  //load level 1 first
        for (String[] entry : level2Data) {
            String type = entry[0];
            double x = Double.parseDouble(entry[1]);
            double y = Double.parseDouble(entry[2]);
            Point position = new Point(x, y);
            switch (type.toLowerCase()) {
                case "flying_platform": {
                    FLYING_PLATFORM_LIST.add(new FlyingPlatform(new Image(game_props.getProperty("gameObjects.flyingPlatform.image")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.maxRandomDisplacementX")),
                            Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.halfLength")),
                            Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.halfHeight")),
                            Double.parseDouble(game_props.getProperty("gameObjects.flyingPlatform.randomSpeed"))));
                    break;
                }
                case "double_score": {
                    POWERUP_LIST.add(new PowerUP(new Image(game_props.getProperty("gameObjects.doubleScore.image")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.radius")), "DoubleScore",
                            Double.parseDouble(game_props.getProperty("gameObjects.doubleScore.maxFrames"))));
                    break;
                }
                case "invincible_power": {
                    POWERUP_LIST.add(new PowerUP(new Image(game_props.getProperty("gameObjects.invinciblePower.image")), position,
                            Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.speed")),
                            Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.radius")), "Invincible",
                            Double.parseDouble(game_props.getProperty("gameObjects.invinciblePower.maxFrames"))));
                    break;
                }
            }
        }
    }

    /**
     * load level3 world file
     *
     * @param game_props :game property file
     * @param level3Data :2d array of level 3
     */
    public void loadLevel3(Properties game_props, String[][] level3Data) {
        bossDefeated = false;
        FireballConfig.load(game_props);  //read fireball information
        loadLevel2(game_props, level3Data);  //load level 1 and 2 first
        for (String[] entry : level3Data) {
            String type = entry[0];
            double x = Double.parseDouble(entry[1]);
            double y = Double.parseDouble(entry[2]);
            Point position = new Point(x, y);
            if (type.equalsIgnoreCase("enemy_boss")) {
                enemyBoss = new EnemyBoss(new Image(game_props.getProperty("gameObjects.enemyBoss.image")), position,
                        Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.health")),
                        Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.radius")),
                        Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.speed")),
                        Double.parseDouble(game_props.getProperty("gameObjects.enemyBoss.activationRadius")));
            }
        }
    }

    /**
     * update logic of game level 1
     *
     * @param input :user input
     */
    public void updateLevel1(Input input) {
        platform.update(input);
        player.update(input);
        endFlag.update(input);
        //check if landed on ground and set position
        if ((player.getPosition().y > player.getORIPosition().y) && !player.isGround()) {
            player.setJumping(false);
            player.setVerticalSpeed(0);
            player.setPosition(new Point(player.getORIPosition().x, player.getORIPosition().y));
            player.setGround(true);
        }
        for (Coin coin : COINS_LIST) {
            coin.update(input);
            coin.draw();
            if (coin.showAvailable() && coin.checkCollision(player)) {
                coin.notAvailable();
                player.earnScore(coin.getVALUE());
            }
        }
        for (Enemy enemy : ENEMY_LIST) {
            enemy.update(input);
            enemy.draw();
            if (enemy.showDanger() && enemy.checkCollision(player)) {
                player.takeDamage(enemy.getDAMAGE_SIZE());
                if (!player.isInvincible()) {
                    enemy.notDanger();
                }
            }
        }
        if (endFlag.checkCollision(player) && bossDefeated) {
            endGame = true;
            winGame = true;
        }
        platform.draw();
        player.draw();
        endFlag.draw();
    }

    /**
     * update logic of level 2 game
     *
     * @param input :user input
     */
    public void updateLevel2(Input input) {
        updateLevel1(input);
        for (FlyingPlatform flyingPlatform : FLYING_PLATFORM_LIST) {
            flyingPlatform.update(input);
            flyingPlatform.draw();
            double distanceX = Math.abs(player.getPosition().x - flyingPlatform.getPosition().x);
            double distanceY = flyingPlatform.getPosition().y - player.getPosition().y;
            // check if land on the flying platform or not
            if ((distanceX < flyingPlatform.getHALF_LENGTH()) && (distanceY <= flyingPlatform.getHALF_HEIGHT())
                    && (distanceY >= (flyingPlatform.getHALF_HEIGHT() - 1))) {
                if (player.isJumping()) {
                    player.setJumping(false);
                    player.setVerticalSpeed(0);
                    player.setPosition(new Point(player.getPosition().x, flyingPlatform.getPosition().y - PLAYER_LEG_LENGTH));
                    player.setFlyingLand(true);
                    flyingPlatform.setPlayerOnPlatform(true);
                }
            } else if (flyingPlatform.isPlayerOnPlatform()) {
                player.setFlyingLand(false);
                flyingPlatform.setPlayerOnPlatform(false);
            }
        }

        boolean anyDoubleScoreActive = false;
        boolean anyInvincibleActive = false;
        for (PowerUP powerUP : POWERUP_LIST) {
            if (powerUP.checkCollision(player)) {
                powerUP.setActive();
                if (powerUP.isDoubleScore()) {
                    player.setDoubleScore(true);
                } else if (powerUP.isInvincible()) {
                    player.setInvincible(true);
                }
            }
            if (powerUP.isActive()) {
                if (powerUP.isDoubleScore()) {
                    anyDoubleScoreActive = true;
                } else if (powerUP.isInvincible()) {
                    anyInvincibleActive = true;
                }
            }
            powerUP.update(input);
            powerUP.draw();
        }
        if (!anyDoubleScoreActive) {
            player.setDoubleScore(false);
        }
        if (!anyInvincibleActive) {
            player.setInvincible(false);
        }
    }

    /**
     * update level3 logic
     *
     * @param input :user input
     */
    public void updateLevel3(Input input) {
        updateLevel2(input);
        counterFrame++;
        enemyBoss.update(input);
        enemyBoss.draw();
        if (input.wasPressed(Keys.S)) {
            double fireballSpeed = FireballConfig.getFireballSpeed();
            if (player.getPosition().x > enemyBoss.getPosition().x) {
                fireballSpeed *= -1;
            }
            FIREBALL_LIST.add(new Fireball(FireballConfig.getImage(), player.getPosition(), FireballConfig.getRadius(),
                    FireballConfig.getMoveSpeed(), fireballSpeed, FireballConfig.getDamageSize(), Fireball.Shooter.PLAYER));
        }
        if (!enemyBoss.isDead() && enemyBoss.checkCounter(player)) {
            if ((counterFrame % 100 == 0) && RandomUtils.nextBoolean()) {
                double fireballSpeed = FireballConfig.getFireballSpeed();
                if (player.getPosition().x < enemyBoss.getPosition().x) {
                    fireballSpeed *= -1;
                }
                FIREBALL_LIST.add(new Fireball(FireballConfig.getImage(), enemyBoss.getPosition(), FireballConfig.getRadius(),
                        FireballConfig.getMoveSpeed(), fireballSpeed, FireballConfig.getDamageSize(), Fireball.Shooter.BOSS));
            }
        }
        for (Fireball fireball : FIREBALL_LIST) {
            fireball.update(input);
            if (fireball.isActive()) {
                fireball.draw();
                if (fireball.getSHOOTER() == Fireball.Shooter.PLAYER) {
                    if (fireball.checkCollision(enemyBoss)) {
                        enemyBoss.takeDamage(fireball.getDAMAGE_SIZE());
                        fireball.deActivate();
                    }
                } else if (fireball.getSHOOTER() == Fireball.Shooter.BOSS) {
                    if (fireball.checkCollision(player)) {
                        player.takeDamage(fireball.getDAMAGE_SIZE());
                        fireball.deActivate();
                    }
                }
            }

        }

        if (enemyBoss.isDead()) {
            bossDefeated = true;
        }
    }

    /**
     * method to reset whole game
     */
    public void reset() {
        player = null;
        platform = null;
        FLYING_PLATFORM_LIST.clear();
        COINS_LIST.clear();
        ENEMY_LIST.clear();
        endFlag = null;
        POWERUP_LIST.clear();
        FIREBALL_LIST.clear();
        enemyBoss = null;
        startGame = false;
        endGame = false;
        winGame = false;
        lostGame = false;
        counterFrame = 0;
        bossDefeated = true;
    }

    /**
     * Gets the player instance.
     *
     * @return the player instance
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets the enemy boss instance.
     *
     * @return the enemy boss instance
     */
    public EnemyBoss getEnemyBoss() {
        return enemyBoss;
    }

    /**
     * Sets whether the game has started.
     *
     * @param startGame true if the game has started, false otherwise
     */
    public void setStartGame(boolean startGame) {
        this.startGame = startGame;
    }

    /**
     * Sets whether the game has ended.
     *
     * @param endGame true if the game has ended, false otherwise
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    /**
     * Sets whether the game is lost.
     *
     * @param lostGame true if the game is lost, false otherwise
     */
    public void setLostGame(boolean lostGame) {
        this.lostGame = lostGame;
    }

    /**
     * Checks whether the game is lost.
     *
     * @return true if the game is lost, false otherwise
     */
    public boolean isLostGame() {
        return lostGame;
    }

    /**
     * Checks whether the game is won.
     *
     * @return true if the game is won, false otherwise
     */
    public boolean isWinGame() {
        return winGame;
    }

    /**
     * Checks whether the game has ended.
     *
     * @return true if the game has ended, false otherwise
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Checks whether the game has started.
     *
     * @return true if the game has started, false otherwise
     */
    public boolean isStartGame() {
        return startGame;
    }
}
