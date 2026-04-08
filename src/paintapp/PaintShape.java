package paintapp;

import java.awt.*;

public abstract class PaintShape {
    protected Color strokeColor;
    protected Color fillColor;

    public PaintShape(Color strokeColor, Color fillColor) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
    }

    public abstract void draw(Graphics2D g2);
    public abstract boolean contains(Point p);
    public abstract Rectangle getBounds();
    public abstract double calculateArea();
    public abstract void moveBy(int dx, int dy);
    public abstract void resizeTo(Point p);
}
