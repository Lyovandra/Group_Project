package paintapp;

import java.awt.*;

public class RectangleShape extends PaintShape {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public RectangleShape(Point a, Point b, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        setFromPoints(a, b);
    }

    protected void setFromPoints(Point a, Point b) {
        x = Math.min(a.x, b.x);
        y = Math.min(a.y, b.y);
        width = Math.abs(a.x - b.x);
        height = Math.abs(a.y - b.y);
    }

    @Override
    public void draw(Graphics2D g2) {
        if (fillColor.getAlpha() > 0) {
            g2.setColor(fillColor);
            g2.fillRect(x, y, width, height);
        }
        g2.setColor(strokeColor);
        g2.drawRect(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        return getBounds().contains(p);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void resizeTo(Point p) {
        width = Math.max(1, p.x - x);
        height = Math.max(1, p.y - y);
    }
}
