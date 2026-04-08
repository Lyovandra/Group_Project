package paintapp;

import java.awt.*;

public class CircleShape extends PaintShape {
    private int x;
    private int y;
    private int diameter;

    public CircleShape(Point a, Point b, Color strokeColor, Color fillColor) {
        super(strokeColor, fillColor);
        setFromPoints(a, b);
    }

    private void setFromPoints(Point a, Point b) {
        x = Math.min(a.x, b.x);
        y = Math.min(a.y, b.y);
        diameter = Math.max(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
        if (b.x < a.x) x = a.x - diameter;
        if (b.y < a.y) y = a.y - diameter;
        diameter = Math.max(1, diameter);
    }

    @Override
    public void draw(Graphics2D g2) {
        if (fillColor.getAlpha() > 0) {
            g2.setColor(fillColor);
            g2.fillOval(x, y, diameter, diameter);
        }
        g2.setColor(strokeColor);
        g2.drawOval(x, y, diameter, diameter);
    }

    @Override
    public boolean contains(Point p) {
        double cx = x + diameter / 2.0;
        double cy = y + diameter / 2.0;
        double r = diameter / 2.0;
        return Point.distance(p.x, p.y, cx, cy) <= r;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    @Override
    public double calculateArea() {
        double r = diameter / 2.0;
        return Math.PI * r * r;
    }

    @Override
    public void moveBy(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void resizeTo(Point p) {
        diameter = Math.max(1, Math.max(p.x - x, p.y - y));
    }
}
