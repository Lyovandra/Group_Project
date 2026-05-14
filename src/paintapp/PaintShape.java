package paintapp;

import java.awt.*;

/**
 * Abstract base class for all shapes that can be drawn on the canvas.
 * Defines common properties like stroke and fill color, and required methods
 * for drawing, collision detection, and transformation.
 */
public abstract class PaintShape {
    protected Color strokeColor;
    protected Color fillColor;

    /**
     * Constructs a PaintShape with specified colors.
     * @param strokeColor the color of the shape's outline
     * @param fillColor the color of the shape's interior
     */
    public PaintShape(Color strokeColor, Color fillColor) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    /**
     * Draws the shape on the provided Graphics context.
     * @param g2 the Graphics2D context to draw on
     */
    public abstract void draw(Graphics2D g2);

    /**
     * Checks if a point is within the shape's boundaries.
     * @param p the point to check
     * @return true if the point is inside the shape, false otherwise
     */
    public abstract boolean contains(Point p);

    /**
     * Gets the bounding rectangle of the shape.
     * @return the bounding Rectangle
     */
    public abstract Rectangle getBounds();

    /**
     * Calculates the area of the shape.
     * @return the area as a double
     */
    public abstract double calculateArea();

    /**
     * Moves the shape by the specified displacement.
     * @param dx horizontal displacement
     * @param dy vertical displacement
     */
    public abstract void moveBy(int dx, int dy);

    /**
     * Resizes the shape to match a new point (typically a corner).
     * @param p the new point to resize towards
     */
    public abstract void resizeTo(Point p);
}
