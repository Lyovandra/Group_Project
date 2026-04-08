package paintapp;

public interface ShapeCommand {
    void execute(DrawingCanvas canvas);
    void undo(DrawingCanvas canvas);
}
