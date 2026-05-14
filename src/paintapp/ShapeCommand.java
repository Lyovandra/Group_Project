package paintapp;

/**
 * Interface for commands that perform operations on the drawing canvas.
 * Supports execution and undoing of actions.
 */
public interface ShapeCommand {
    /**
     * Executes the command on the specified canvas.
     * @param canvas the drawing canvas to operate on
     */
    void execute(DrawingCanvas canvas);

    /**
     * Reverts the effects of the command on the specified canvas.
     * @param canvas the drawing canvas to revert changes on
     */
    void undo(DrawingCanvas canvas);
}
