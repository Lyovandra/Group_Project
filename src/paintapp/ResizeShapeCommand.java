package paintapp;

import java.awt.*;

public class ResizeShapeCommand implements ShapeCommand {
    private final PaintShape shape;
    private final Rectangle before;
    private final Rectangle after;

    public ResizeShapeCommand(PaintShape shape, Rectangle before, Rectangle after) {
        this.shape = shape;
        this.before = before;
        this.after = after;
    }

    @Override
    public void execute(DrawingCanvas canvas) {
        shape.resizeTo(new Point(after.x + after.width, after.y + after.height));
    }

    @Override
    public void undo(DrawingCanvas canvas) {
        shape.resizeTo(new Point(before.x + before.width, before.y + before.height));
    }
}
