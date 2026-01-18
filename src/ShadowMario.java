import bagel.*;
import bagel.util.Colour;

import java.util.Properties;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2024
 * edit from my project1 edition
 * Please enter your name below
 *
 * @QILIN
 */
public class ShadowMario extends AbstractGame {

    private final Image BACKGROUND_IMAGE;
    private final TextElement TITLE;
    private final TextElement INSTR;
    private final TextElement SCORE;
    private final TextElement PLAYER_HEALTH;
    private final TextElement BOSS_HEALTH;
    private final TextElement GAME_OVER;
    private final TextElement GAME_WON;
    private final String SCORE_TEXT;
    private final String HEALTH_TEXT;
    private int currentLevel;
    private static final double lineSpacingSize = 1.2;
    private final Properties game_props;
    private final LevelManager levelManager;


    /**
     * This is constructor to initialize ShadowMaria
     *
     * @param game_props    This is game property file
     * @param message_props This is message property file
     */
    public ShadowMario(Properties game_props, Properties message_props) {
        super(Integer.parseInt(game_props.getProperty("windowWidth")),
                Integer.parseInt(game_props.getProperty("windowHeight")),
                message_props.getProperty("title"));

        BACKGROUND_IMAGE = new Image(game_props.getProperty("backgroundImage"));
        // Create text object and input the properties

        TITLE = new TextElement(message_props.getProperty("title"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("title.fontSize"))),
                Double.parseDouble(game_props.getProperty("title.x")),
                Double.parseDouble(game_props.getProperty("title.y")),
                Integer.parseInt(game_props.getProperty("title.fontSize")) / lineSpacingSize);
        INSTR = new TextElement(message_props.getProperty("instruction"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("instruction.fontSize"))), 0,
                Double.parseDouble(game_props.getProperty("instruction.y")),
                Integer.parseInt(game_props.getProperty("instruction.fontSize")) / lineSpacingSize);
        // Move second line away from first line with 1.2 the font size
        INSTR.setX((Window.getWidth() - INSTR.getWidth()) / 2.0);

        GAME_OVER = new TextElement(message_props.getProperty("gameOver"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("message.fontSize"))), 0,
                Double.parseDouble(game_props.getProperty("message.y")),
                Integer.parseInt(game_props.getProperty("message.fontSize")) / lineSpacingSize);
        GAME_OVER.setX((Window.getWidth() - GAME_OVER.getWidth()) / 2.0);

        GAME_WON = new TextElement(message_props.getProperty("gameWon"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("message.fontSize"))), 0,
                Double.parseDouble(game_props.getProperty("message.y")),
                Integer.parseInt(game_props.getProperty("message.fontSize")) / lineSpacingSize);
        GAME_WON.setX((Window.getWidth() - GAME_WON.getWidth()) / 2.0);

        SCORE = new TextElement(message_props.getProperty("score"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("score.fontSize"))),
                Double.parseDouble(game_props.getProperty("score.x")),
                Double.parseDouble(game_props.getProperty("score.y")),
                Integer.parseInt(game_props.getProperty("score.fontSize")) / lineSpacingSize);
        SCORE_TEXT = SCORE.getText();

        PLAYER_HEALTH = new TextElement(message_props.getProperty("health"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("playerHealth.fontSize"))),
                Double.parseDouble(game_props.getProperty("playerHealth.x")),
                Double.parseDouble(game_props.getProperty("playerHealth.y")),
                Integer.parseInt(game_props.getProperty("playerHealth.fontSize")) / lineSpacingSize);
        HEALTH_TEXT = PLAYER_HEALTH.getText();
        BOSS_HEALTH = new TextElement(message_props.getProperty("health"), new Font(game_props.getProperty("font"),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.fontSize"))),
                Double.parseDouble(game_props.getProperty("enemyBossHealth.x")),
                Double.parseDouble(game_props.getProperty("enemyBossHealth.y")),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.fontSize")) / lineSpacingSize);

        levelManager = LevelManager.getInstance();
        this.game_props = game_props;


    }

    /**
     * This is main method to read file and run game
     *
     * @param args default input here
     */
    public static void main(String[] args) {
        Properties game_props = IOUtils.readPropertiesFile("res/app.properties");
        Properties message_props = IOUtils.readPropertiesFile("res/message_en.properties");
        ShadowMario game = new ShadowMario(game_props, message_props);
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     *
     * @param input This is user input
     */
    @Override
    protected void update(Input input) {

        // close window
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        if (!levelManager.isStartGame()) {
            TITLE.draw();
            INSTR.draw();
            // Check for starting the game
            if (input.wasPressed(Keys.NUM_1)) {
                currentLevel = 1;
                levelManager.loadLevel(1, game_props);
                levelManager.setStartGame(true);
            } else if (input.wasPressed(Keys.NUM_2)) {
                currentLevel = 2;
                levelManager.loadLevel(2, game_props);
                levelManager.setStartGame(true);
            } else if (input.wasPressed(Keys.NUM_3)) {
                currentLevel = 3;
                levelManager.loadLevel(3, game_props);
                levelManager.setStartGame(true);
            }
        } else if (levelManager.isEndGame()) {
            if (levelManager.isWinGame()) {
                GAME_WON.draw();
            } else if (levelManager.isLostGame()) {
                GAME_OVER.draw();
            }
            if (input.wasPressed(Keys.SPACE)) {
                restartGame();
            }
        } else {
            if (levelManager.getPlayer().isDead()) {
                levelManager.setEndGame(true);
                levelManager.setLostGame(true);
            } else {
                switch (currentLevel) {
                    case 1:
                        levelManager.updateLevel1(input);
                        break;
                    case 2:
                        levelManager.updateLevel2(input);
                        break;
                    case 3:
                        levelManager.updateLevel3(input);
                        BOSS_HEALTH.setText(HEALTH_TEXT + " " + (int) (levelManager.getEnemyBoss().getHealth() * 100));
                        BOSS_HEALTH.drawWithColor(new DrawOptions().setBlendColour(Colour.RED));
                        break;
                }
            }

            SCORE.setText(SCORE_TEXT + " " + (int) levelManager.getPlayer().getScore());
            PLAYER_HEALTH.setText(HEALTH_TEXT + " " + (int) Math.round(levelManager.getPlayer().getHealth() * 100));
            SCORE.draw();
            PLAYER_HEALTH.draw();
        }
    }


    /**
     * This is method to restart game
     */
    public void restartGame() {
        levelManager.reset();
        currentLevel = 0;
    }
}

