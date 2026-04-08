package paintapp;

public class CommandManager {
    private ShapeCommand[] undoStack = new ShapeCommand[1000];
    private int undoTop = 0;
    private ShapeCommand[] redoStack = new ShapeCommand[1000];
    private int redoTop = 0;

    public void execute(ShapeCommand command, DrawingCanvas canvas) {
        command.execute(canvas);
        if (undoTop < undoStack.length) {
            undoStack[undoTop++] = command;
        }
        redoTop = 0; // Clear redo stack
    }

    public void undo(DrawingCanvas canvas) {
        if (undoTop <= 0) return;
        ShapeCommand command = undoStack[--undoTop];
        command.undo(canvas);
        if (redoTop < redoStack.length) {
            redoStack[redoTop++] = command;
        }
    }

    public void redo(DrawingCanvas canvas) {
        if (redoTop <= 0) return;
        ShapeCommand command = redoStack[--redoTop];
        command.execute(canvas);
        if (undoTop < undoStack.length) {
            undoStack[undoTop++] = command;
        }
    }
}
