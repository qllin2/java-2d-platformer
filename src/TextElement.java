import bagel.DrawOptions;
import bagel.Font;

/**
 * This is a class to store text property
 */
public class TextElement {
    private String text;
    private String[] lines;
    private final Font FONT;
    private double x;
    private double y;
    private final double LINE_SPACING;

    /**
     * Constructor of text element
     *
     * @param text         :text
     * @param font         :font
     * @param x            :x value
     * @param y            :y value
     * @param LINE_SPACING :distance between lines
     */
    public TextElement(String text, Font font, double x, double y, double LINE_SPACING) {
        this.text = text;
        this.FONT = font;
        this.x = x;
        this.y = y;
        this.LINE_SPACING = LINE_SPACING;
        this.lines = text.split("\n");
    }

    /**
     * method to draw the lines of game, can deal with multiple lines
     */
    public void draw() {
        lines = text.split("\n");
        double currY = y;
        for (String line : lines) {
            FONT.drawString(line, x, currY);
            currY += LINE_SPACING;
        }
    }

    /**
     * method to draw the lines with colour of game, can deal with multiple lines
     */
    public void drawWithColor(DrawOptions options) {
        lines = text.split("\n");
        double currY = y;
        for (String line : lines) {
            FONT.drawString(line, x, currY, options);
            currY += LINE_SPACING;
        }
    }

    /**
     * method to get most width between lines
     *
     * @return double max width
     */
    public double getWidth() {
        double maxWidth = 0;
        for (String line : lines) {
            double lineWidth = FONT.getWidth(line);
            if (lineWidth > maxWidth) {
                maxWidth = lineWidth;
            }
        }
        return maxWidth;
    }

    /**
     * method to get text
     *
     * @return text
     */
    public String getText() {
        return text;
    }

    /**
     * method to set x
     *
     * @param x :x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * method to set y
     *
     * @param y :y
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * method to set text
     *
     * @param s :string
     */
    public void setText(String s) {
        text = s;
    }
}