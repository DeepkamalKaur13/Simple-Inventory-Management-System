import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The RedPiece class represents a game piece that is drawn on a canvas in the form of a red circle.
 * It extends the GamePiece class, inheriting properties such as position, outline color, fill color, and size.
 *
 * @author Deepkamal Kaur
 */
public class Brown extends Piece {

    /**
     * Constructor for the RedPiece class.
     * Initializes the position, outline color, and fill color of the red piece,
     * and sets the size (radius) for the piece.
     *
     * @param posX        The x-coordinate of the piece.
     * @param posY        The y-coordinate of the piece.
     * @param outlineColor The outline color of the piece.
     * @param fillColor   The fill color of the piece.
     */
    public Brown(double posX, double posY, Color outlineColor, Color fillColor,double size) {
        super(posX, posY, outlineColor, fillColor, size);
    }

    /**
     * Draws the brown piece on the canvas using the provided GraphicsContext.
     * The piece is drawn as a filled oval with an outline, centered on the (posX, posY) position.
     *
     * @param gc The GraphicsContext used for drawing the piece.
     */
    @Override
    public void draw(GraphicsContext gc) {
        // Set the fill color and stroke color for the piece.
        gc.setFill(getAdjustedFillColor());
        gc.setStroke(getAdjustedOutlineColor());

        // Draw the filled oval and its border centered at the (posX, posY) position.
        gc.fillOval(posX - 15, posY - 15, 30, 30);
        gc.strokeOval(posX - 15, posY - 15, 30, 30);
    }
}
