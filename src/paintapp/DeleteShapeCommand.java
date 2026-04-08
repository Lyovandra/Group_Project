package paintapp;

public class DeleteShapeCommand implements ShapeCommand {
    private final PaintShape shape;

    public DeleteShapeCommand(PaintShape shape) {
        this.shape = shape;
    }

    @Override
    public void execute(DrawingCanvas canvas) {
        canvas.removeShape(shape);
    }

    @Override
    public void undo(DrawingCanvas canvas) {
        canvas.addShape(shape);
    }
}
