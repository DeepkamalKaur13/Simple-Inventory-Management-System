import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.ArrayList;

/**
 * The GameApp class represents the main application for a grid-based game.
 * It manages user interactions, including selecting, moving, and removing pieces on the board.
 */
public class GameApp extends Application {

    private Board board;                // The game board.
    private ArrayList<Piece> pieces;    // List to store game pieces.
    private GraphicsContext gc;         // GraphicsContext for drawing on the canvas.
    private Piece selectedPiece;        // Currently selected piece for movement.

    private TextField rowBox;           // TextField to input the row of the piece to remove.
    private TextField colBox;           // TextField to input the column of the piece to remove.
    private Button removeButton;        // Button to trigger piece removal.
    private Label footer;               // Label to display information about the number of pieces.
    private boolean isBrown = true;     // Toggle to alternate between Brown and Green pieces.

    /**
     * Handles mouse click events to select, move, or add pieces on the board.
     *
     * @param me The MouseEvent containing information about the mouse click.
     */
    public void mousePressed(MouseEvent me) {
        int i = board.getSquareIndex(me.getX(), me.getY()); // Determine which square was clicked.
        double x = board.getSquareCenterX(i);               // Get the center x-coordinate of the clicked square.
        double y = board.getSquareCenterY(i);               // Get the center y-coordinate of the clicked square.

        // If a piece is already selected, try to move it to the clicked square.
        if (selectedPiece != null) {
            if (board.isEmptySquare(i, pieces)) { // Ensure the target square is empty.
                selectedPiece.setGridPosition(board.getRow(i), board.getCol(i));
                selectedPiece.setTileNumber(i);
                selectedPiece.setPosition(x, y);
                selectedPiece.setHighlighted(false); // Deselect the piece after moving.
                selectedPiece = null;
            }
        } else {
            // Otherwise, check if a piece was clicked and select it.
            for (Piece piece : pieces) {
                if (piece.contains(me.getX(), me.getY())) {
                    selectedPiece = piece;
                    selectedPiece.setHighlighted(true);
                    break;
                }
            }


            if (selectedPiece == null) {
                Piece piece;
                if (isBrown) {
                    piece = new Brown(x, y, Color.WHITE, Color.BROWN,20);
                } else {
                    piece = new Green(x, y, Color.BLUE, Color.LIGHTGREEN,20);
                }
                piece.setTileNumber(i);
                piece.setGridPosition(board.getRow(i), board.getCol(i));
                pieces.add(piece);
                isBrown = !isBrown; // Toggle for the next click.
            }
        }
        refresh(); // Refresh the board and pieces display.
        footer.setText(String.format("Num pieces: %d", pieces.size())); // Update the footer with the number of pieces.
    }

    /**
     * Refreshes the canvas by redrawing the board and all pieces.
     */
    public void refresh() {
        board.draw(gc);
        for (Piece piece : pieces) {
            piece.draw(gc);
        }
    }

    /**
     * Handles the remove button click event to remove a piece from the board.
     *
     * @param e The ActionEvent triggered by the remove button.
     */
    public void removeHandler(ActionEvent e) {
        try {
            int row = Integer.parseInt(rowBox.getText()); // Get the row index from the text field.
            int col = Integer.parseInt(colBox.getText()); // Get the column index from the text field.

            pieces.removeIf(piece -> piece.getGridRow() == row && piece.getGridCol() == col); // Remove the piece if it matches the row and column.

            refresh(); // Refresh the board and pieces display.
        } catch (NumberFormatException ex) {
            footer.setText("Invalid row or column number."); // Handle invalid input.
        }
    }

    @Override
    /**
     * Starts the application by setting up the user interface and event handlers.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if an error occurs during setup.
     */
    public void start(Stage stage) throws Exception {
        Pane root = new Pane(); // Create the root pane.
        Scene scene = new Scene(root, 500, 500); // Set the size of the scene.
        stage.setTitle("Grid Game"); // Set the window title.
        stage.setScene(scene);

        board = new Board(75, 45, 40, 8, Color.web("lightgray"), Color.web("lightgray")); // Initialize the board.
        pieces = new ArrayList<Piece>(); // Initialize the list of pieces.

        Canvas canvas = new Canvas(500, 400); // Create a canvas for drawing.
        rowBox = new TextField("0"); // Initialize the row input text field.
        colBox = new TextField("0"); // Initialize the column input text field.
        removeButton = new Button("Remove"); // Initialize the remove button.
        footer = new Label(); // Initialize the footer label.

        gc = canvas.getGraphicsContext2D(); // Get the GraphicsContext for the canvas.
        gc.setFill(Color.LIGHTBLUE); // Set the background color of the canvas.
        gc.fillRect(0, 0, 500, 400); // Fill the canvas background.

        board.draw(gc); // Draw the initial state of the board.

        // Add UI components to the root pane.
        root.getChildren().addAll(canvas, rowBox, colBox, removeButton, footer);

        // Position the UI components.
        rowBox.relocate(10, 450);
        colBox.relocate(180, 450);
        removeButton.relocate(350, 450);
        footer.relocate(10, 480);

        // Set up event handlers.
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::mousePressed);
        removeButton.setOnAction(this::removeHandler);

        stage.show(); // Show the application window.
    }

    /**
     * The main method to launch the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
