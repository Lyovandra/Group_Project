package paintapp;

public class MoveShapeCommand implements ShapeCommand {
    private final PaintShape shape;
    private final int dx;
    private final int dy;

    public MoveShapeCommand(PaintShape shape, int dx, int dy) {
        this.shape = shape;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute(DrawingCanvas canvas) {
        shape.moveBy(dx, dy);
    }

    @Override
    public void undo(DrawingCanvas canvas) {
        shape.moveBy(-dx, -dy);
    }
}
