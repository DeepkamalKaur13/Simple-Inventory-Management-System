import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The abstract GamePiece class represents a generic piece that can be drawn on a canvas.
 * It serves as a base class for specific types of game pieces, such as RedPiece or GreenPiece.
 * It provides common properties like position, color, and size, as well as methods for handling selection and position.
 *
 * @author Deepkamal Kaur
 */
public abstract class Piece {

   // Coordinates of the game piece.
   protected double posX, posY;

   // Border and fill colors of the game piece.
   protected Color outlineColor, fillColor;

   // Size (radius) of the game piece.
   protected double size;

   // Grid row and column position of the game piece.
   private int gridRow, gridCol;

   // Flag indicating whether the game piece is selected.
   private boolean isHighlighted = false;

   // The square number associated with the piece.
   protected int tileNumber;

   /**
    * Constructor for the GamePiece class.
    * Initializes the game piece with its position, border color, fill color, and size.
    *
    * @param posX        The x-coordinate of the game piece.
    * @param posY        The y-coordinate of the game piece.
    * @param outlineColor The border color of the game piece.
    * @param fillColor   The fill color of the game piece.
    * @param size        The size (radius) of the game piece.
    */
   public Piece(double posX, double posY, Color outlineColor, Color fillColor, double size) {
      this.posX = posX;
      this.posY = posY;
      this.outlineColor = outlineColor;
      this.fillColor = fillColor;
      this.size = size;
   }

   /**
    * Abstract method to draw the game piece on the canvas.
    * This method must be implemented by any subclass of GamePiece.
    *
    * @param gc The GraphicsContext used for drawing the game piece.
    */
   public abstract void draw(GraphicsContext gc);

   /**
    * Determines whether a point (px, py) lies within the game piece.
    * This method is useful for detecting clicks or selections.
    *
    * @param pointX The x-coordinate of the point.
    * @param pointY The y-coordinate of the point.
    * @return true if the point is within the game piece, false otherwise.
    */
   public boolean contains(double pointX, double pointY) {
      double deltaX = pointX - posX;
      double deltaY = pointY - posY;
      return Math.sqrt(deltaX * deltaX + deltaY * deltaY) <= size;
   }

   /**
    * Sets the row and column of the game piece on the grid.
    *
    * @param gridRow The row position.
    * @param gridCol The column position.
    */
   public void setGridPosition(int gridRow, int gridCol) {
      this.gridRow = gridRow;
      this.gridCol = gridCol;
   }

   /**
    * Gets the row of the game piece.
    *
    * @return The row position of the game piece.
    */
   public int getGridRow() {
      return gridRow;
   }

   /**
    * Gets the column of the game piece.
    *
    * @return The column position of the game piece.
    */
   public int getGridCol() {
      return gridCol;
   }

   /**
    * Sets the tile number associated with the game piece.
    *
    * @param tileNumber The tile number.
    */
   public void setTileNumber(int tileNumber) {
      this.tileNumber = tileNumber;
   }

   /**
    * Gets the tile number associated with the game piece.
    *
    * @return The tile number.
    */
   public int getTileNumber() {
      return this.tileNumber;
   }

   /**
    * Sets the position of the game piece.
    *
    * @param posX The x-coordinate of the new position.
    * @param posY The y-coordinate of the new position.
    */
   public void setPosition(double posX, double posY) {
      this.posX = posX;
      this.posY = posY;
   }

   /**
    * Sets whether the game piece is highlighted (selected).
    *
    * @param isHighlighted true if the game piece is highlighted, false otherwise.
    */
   public void setHighlighted(boolean isHighlighted) {
      this.isHighlighted = isHighlighted;
   }

   /**
    * Gets the adjusted border color of the game piece.
    * If the game piece is highlighted, the border color is set to yellow.
    *
    * @return The adjusted border color.
    */
   protected Color getAdjustedOutlineColor() {
      return isHighlighted ? Color.YELLOW : outlineColor;
   }

   /**
    * Gets the adjusted fill color of the game piece.
    * If the game piece is highlighted, the fill color is set to orange.
    *
    * @return The adjusted fill color.
    */
   protected Color getAdjustedFillColor() {
      return isHighlighted ? Color.ORANGE : fillColor;
   }
}
