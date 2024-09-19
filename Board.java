import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * The Board class represents a game board consisting of a grid of squares.
 * It manages the position, size, and color of each square and handles drawing the board on a canvas.
 */
public class Board {
    private double topX;            // The x-coordinate of the top-left corner of the board.
    private double topY;            // The y-coordinate of the top-left corner of the board.
    private double width;           // The width (and height) of each square on the board.
    private int numSquares;         // The number of squares along one side of the board.
    private Color fillColor1;       // The primary color used to fill the squares.
    private Color fillColor2;       // The secondary color used to fill alternating squares.

    private GraphicsContext gc;     // The GraphicsContext used for drawing the board.
    private ArrayList<Rectangle> grid; // List to store the grid squares.

    /**
     * Constructor for the Board class.
     * Initializes the board's properties and creates an empty grid list.
     *
     * @param topX       The x-coordinate of the top-left corner of the board.
     * @param topY       The y-coordinate of the top-left corner of the board.
     * @param width      The width of each square on the board.
     * @param numSquares The number of squares along one side of the board.
     * @param fillColor1 The primary color used to fill the squares.
     * @param fillColor2 The secondary color used to fill alternating squares.
     */
    public Board(double topX, double topY, double width, int numSquares, Color fillColor1, Color fillColor2) {
        this.topX = topX;
        this.topY = topY;
        this.width = width;
        this.numSquares = numSquares;
        this.fillColor1 = fillColor1;
        this.fillColor2 = fillColor2;

        this.grid = new ArrayList<Rectangle>();
    }

    /**
     * Returns the number of squares in the grid.
     *
     * @return The size of the grid.
     */
    public int gridSize() {
        return grid.size();
    }

    /**
     * Returns the x-coordinate of the top-left corner of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The x-coordinate of the top-left corner of the square.
     */
    public double getSquareTopLeftX(int squareNumber) {
        return grid.get(squareNumber).getTopLeft_x();
    }

    /**
     * Returns the y-coordinate of the top-left corner of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The y-coordinate of the top-left corner of the square.
     */
    public double getSquareTopLeftY(int squareNumber) {
        return grid.get(squareNumber).getTopLeft_y();
    }

    /**
     * Returns the x-coordinate of the center of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The x-coordinate of the center of the square.
     */
    public double getSquareCenterX(int squareNumber) {
        return grid.get(squareNumber).getTopLeft_x() + width / 2;
    }

    /**
     * Returns the y-coordinate of the center of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The y-coordinate of the center of the square.
     */
    public double getSquareCenterY(int squareNumber) {
        return grid.get(squareNumber).getTopLeft_y() + width / 2;
    }

    /**
     * Returns the row index of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The row index of the square.
     */
    public int getRow(int squareNumber) {
        return grid.get(squareNumber).row;
    }

    /**
     * Returns the column index of a specified square.
     *
     * @param squareNumber The index of the square.
     * @return The column index of the square.
     */
    public int getCol(int squareNumber) {
        return grid.get(squareNumber).col;
    }

    /**
     * Returns the width of each square on the board.
     *
     * @return The width of the squares.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Determines the index of the square that contains the given x and y coordinates.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return The index of the square containing the point, or -1 if no square contains it.
     */
    public int getSquareIndex(double x, double y) {
        int result = -1;
        double tx, ty, w, h, dx, dy;
        for (int i = 0; i < grid.size(); i++) {
            Rectangle r = grid.get(i);
            tx = r.getTopLeft_x();
            ty = r.getTopLeft_y();
            w = r.getWidth();
            h = r.getLength();
            dx = Math.abs(x - tx);
            dy = Math.abs(y - ty);
            if (dx < w && dy < h) {
                return i; // The point is within the square.
            }
        }
        return result; // The point is not within any square.
    }

    /**
     * Checks if a specific square is empty, meaning it does not contain any pieces.
     *
     * @param squareIndex The index of the square to check.
     * @param pieces      The list of pieces on the board.
     * @return True if the square is empty, false if it is occupied by a piece.
     */
    public boolean isEmptySquare(int squareIndex, ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            if (piece.getTileNumber() == squareIndex) {
                return false; // The square is occupied by a piece.
            }
        }
        return true; // The square is empty.
    }

    /**
     * Draws the game board on the canvas using the provided GraphicsContext.
     * Alternates colors for the squares and adds them to the grid list.
     *
     * @param gc The GraphicsContext used for drawing the board.
     */
    public void draw(GraphicsContext gc) {
        Color currentColor;
        int count = 0;

        for (int row = 0; row < numSquares; row++) {
            for (int col = 0; col < numSquares; col++) {
                currentColor = (count % 2 == 0) ? fillColor1 : fillColor2;

                Rectangle square = new Rectangle(width, width);
                square.setTopLeft_x(topX + (col * width));
                square.setTopLeft_y(topY + (row * width));
                square.setFillColor(currentColor);
                square.row = row;
                square.col = col;

                square.draw(gc);
                grid.add(square);
                count++;
            }
            count++;
        }
    }
}
