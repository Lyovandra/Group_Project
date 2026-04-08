package paintapp;

public class AddShapeCommand implements ShapeCommand {
    private final PaintShape shape;

    public AddShapeCommand(PaintShape shape) {
        this.shape = shape;
    }

    @Override
    public void execute(DrawingCanvas canvas) {
        canvas.addShape(shape);
        canvas.setSelectedShape(shape);
    }

    @Override
    public void undo(DrawingCanvas canvas) {
        canvas.removeShape(shape);
        canvas.setSelectedShape(null);
    }
}
