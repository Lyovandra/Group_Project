package paintapp;

import java.awt.*;

public class LineShape extends PaintShape {
    private Point start;
    private Point end;

    public LineShape(Point start, Point end, Color strokeColor) {
        super(strokeColor, new Color(0, 0, 0, 0));
        this.start = new Point(start);
        this.end = new Point(end);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(strokeColor);
        g2.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public boolean contains(Point p) {
        double dist = distanceToSegment(p, start, end);
        return dist <= 6.0;
    }

    @Override
    public Rectangle getBounds() {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.abs(start.x - end.x);
        int h = Math.abs(start.y - end.y);
        return new Rectangle(x, y, w, h);
    }

    @Override
    public double calculateArea() {
        return 0;
    }

    @Override
    public void moveBy(int dx, int dy) {
        start.translate(dx, dy);
        end.translate(dx, dy);
    }

    @Override
    public void resizeTo(Point p) {
        end = new Point(p);
    }

    private double distanceToSegment(Point p, Point a, Point b) {
        double px = p.x, py = p.y, ax = a.x, ay = a.y, bx = b.x, by = b.y;
        double dx = bx - ax, dy = by - ay;
        if (dx == 0 && dy == 0) return p.distance(a);
        double t = ((px - ax) * dx + (py - ay) * dy) / (dx * dx + dy * dy);
        t = Math.max(0, Math.min(1, t));
        double cx = ax + t * dx, cy = ay + t * dy;
        return Point.distance(px, py, cx, cy);
    }
}
