package paintapp;

/**
 * Manages the execution, undo, and redo of shape-related commands.
 * Maintains separate stacks for undo and redo operations.
 */
public class CommandManager {
    private ShapeCommand[] undoStack = new ShapeCommand[1000];
    private int undoTop = 0;
    private ShapeCommand[] redoStack = new ShapeCommand[1000];
    private int redoTop = 0;

    /**
     * Executes a command and adds it to the undo stack.
     * Clears the redo stack upon execution of a new command.
     * @param command the command to execute
     * @param canvas the drawing canvas to apply the command to
     */
    public void execute(ShapeCommand command, DrawingCanvas canvas) {
        command.execute(canvas);
        if (undoTop < undoStack.length) {
            undoStack[undoTop++] = command;
        }
        redoTop = 0; // Clear redo stack
    }

    /**
     * Undoes the last executed command.
     * Moves the command from the undo stack to the redo stack.
     * @param canvas the drawing canvas to revert the command on
     */
    public void undo(DrawingCanvas canvas) {
        if (undoTop <= 0) return;
        ShapeCommand command = undoStack[--undoTop];
        command.undo(canvas);
        if (redoTop < redoStack.length) {
            redoStack[redoTop++] = command;
        }
    }

    /**
     * Redoes the last undone command.
     * Moves the command from the redo stack back to the undo stack.
     * @param canvas the drawing canvas to re-apply the command to
     */
    public void redo(DrawingCanvas canvas) {
        if (redoTop <= 0) return;
        ShapeCommand command = redoStack[--redoTop];
        command.execute(canvas);
        if (undoTop < undoStack.length) {
            undoStack[undoTop++] = command;
        }
    }
}
