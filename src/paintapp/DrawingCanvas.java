package paintapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
    private PaintShape[] shapes = new PaintShape[1000];
    private int shapeCount = 0;
    private final CommandManager commandManager = new CommandManager();

    private ToolMode toolMode = ToolMode.SELECT;
    private PaintShape selectedShape;
    private Point startPoint;
    private Point currentPoint;
    private Color strokeColor = Color.BLACK;
    private Color fillColor = new Color(0, 0, 0, 0);

    private boolean dragging;
    private boolean resizing;
    private boolean moving;

    public DrawingCanvas() {
        setBackground(Color.WHITE);
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteShape");
        getActionMap().put("deleteShape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "undo");
        getActionMap().put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK), "redo");
        getActionMap().put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redo();
            }
        });
    }

    public void setToolMode(ToolMode toolMode) {
        this.toolMode = toolMode;
        this.selectedShape = null;
        repaint();
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void undo() {
        commandManager.undo(this);
        repaint();
    }

    public void redo() {
        commandManager.redo(this);
        repaint();
    }

    public void clearAll() {
        commandManager.execute(new ShapeCommand() {
            private PaintShape[] backup;
            private int backupCount;

            @Override
            public void execute(DrawingCanvas canvas) {
                backupCount = canvas.shapeCount;
                backup = new PaintShape[backupCount];
                for (int i = 0; i < backupCount; i++) {
                    backup[i] = canvas.shapes[i];
                }
                canvas.shapeCount = 0;
                canvas.selectedShape = null;
            }

            @Override
            public void undo(DrawingCanvas canvas) {
                canvas.shapeCount = backupCount;
                for (int i = 0; i < backupCount; i++) {
                    canvas.shapes[i] = backup[i];
                }
            }
        }, this);
        repaint();
    }

    public void deleteSelected() {
        if (selectedShape == null) return;
        commandManager.execute(new DeleteShapeCommand(selectedShape), this);
        selectedShape = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < shapeCount; i++) {
            shapes[i].draw(g2);
        }

        if (selectedShape != null) {
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1f, new float[]{6f, 6f}, 0f));
            Rectangle bounds = selectedShape.getBounds();
            g2.drawRect(bounds.x - 4, bounds.y - 4, bounds.width + 8, bounds.height + 8);
        }

        if (toolMode != ToolMode.SELECT && startPoint != null && currentPoint != null) {
            PaintShape preview = createShape(toolMode, startPoint, currentPoint, strokeColor, fillColor);
            if (preview != null) {
                preview.draw(g2);
            }
        }

        g2.dispose();
    }

    private PaintShape createShape(ToolMode mode, Point a, Point b, Color stroke, Color fill) {
        return switch (mode) {
            case LINE -> new LineShape(a, b, stroke);
            case RECTANGLE -> new RectangleShape(a, b, stroke, fill);
            case CIRCLE -> new CircleShape(a, b, stroke, fill);
            default -> null;
        };
    }

    private PaintShape hitTest(Point p) {
        for (int i = shapeCount - 1; i >= 0; i--) {
            PaintShape s = shapes[i];
            if (s.contains(p)) return s;
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        requestFocusInWindow();
        startPoint = e.getPoint();
        currentPoint = e.getPoint();

        if (toolMode == ToolMode.SELECT) {
            selectedShape = hitTest(e.getPoint());
            if (selectedShape != null) {
                Rectangle b = selectedShape.getBounds();
                resizing = isNearCorner(e.getPoint(), b);
                moving = !resizing;
                dragging = true;
            } else {
                dragging = false;
            }
            repaint();
            return;
        }

        dragging = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentPoint = e.getPoint();

        if (toolMode == ToolMode.SELECT && selectedShape != null && dragging) {
            if (moving) {
                int dx = e.getX() - startPoint.x;
                int dy = e.getY() - startPoint.y;
                selectedShape.moveBy(dx, dy);
                startPoint = e.getPoint();
            } else if (resizing) {
                selectedShape.resizeTo(e.getPoint());
            }
            repaint();
            return;
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (toolMode != ToolMode.SELECT && dragging) {
            PaintShape shape = createShape(toolMode, startPoint, e.getPoint(), strokeColor, fillColor);
            if (shape != null) {
                commandManager.execute(new AddShapeCommand(shape), this);
                selectedShape = shape;
            }
        } else if (toolMode == ToolMode.SELECT && selectedShape != null && dragging) {
            // movement/resizing already applied directly
        }

        dragging = false;
        moving = false;
        resizing = false;
        startPoint = null;
        currentPoint = null;
        repaint();
    }

    private boolean isNearCorner(Point p, Rectangle b) {
        int r = 10;
        Point[] corners = {
                new Point(b.x, b.y),
                new Point(b.x + b.width, b.y),
                new Point(b.x, b.y + b.height),
                new Point(b.x + b.width, b.y + b.height)
        };
        for (Point c : corners) {
            if (p.distance(c) <= r) return true;
        }
        return false;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    void addShape(PaintShape shape) {
        if (shapeCount < shapes.length) {
            shapes[shapeCount++] = shape;
        }
    }

    void removeShape(PaintShape shape) {
        for (int i = 0; i < shapeCount; i++) {
            if (shapes[i] == shape) {
                for (int j = i; j < shapeCount - 1; j++) {
                    shapes[j] = shapes[j + 1];
                }
                shapes[--shapeCount] = null;
                return;
            }
        }
    }

    int getShapeCount() {
        return shapeCount;
    }

    PaintShape getShape(int index) {
        if (index >= 0 && index < shapeCount) return shapes[index];
        return null;
    }

    void setSelectedShape(PaintShape shape) {
        selectedShape = shape;
    }
}
