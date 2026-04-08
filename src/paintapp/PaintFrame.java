package paintapp;

import javax.swing.*;
import java.awt.*;

public class PaintFrame extends JFrame {
    private final DrawingCanvas canvas = new DrawingCanvas();
    private final ColorManager colorManager = new ColorManager();

    public PaintFrame() {
        super("Paint App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(canvas, BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.NORTH);

        setSize(1100, 750);
        setLocationRelativeTo(null);
    }

    private JComponent createToolbar() {
        JToolBar bar = new JToolBar();

        JButton select = new JButton("Select");
        JButton line = new JButton("Line");
        JButton rect = new JButton("Rectangle");
        JButton circle = new JButton("Circle");
        JButton delete = new JButton("Delete");
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JButton strokeColor = new JButton("Stroke");
        JButton fillColor = new JButton("Fill");
        JButton clear = new JButton("Clear");

        select.addActionListener(e -> canvas.setToolMode(ToolMode.SELECT));
        line.addActionListener(e -> canvas.setToolMode(ToolMode.LINE));
        rect.addActionListener(e -> canvas.setToolMode(ToolMode.RECTANGLE));
        circle.addActionListener(e -> canvas.setToolMode(ToolMode.CIRCLE));
        delete.addActionListener(e -> canvas.deleteSelected());
        undo.addActionListener(e -> canvas.undo());
        redo.addActionListener(e -> canvas.redo());
        clear.addActionListener(e -> canvas.clearAll());

        strokeColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Stroke Color", colorManager.getStrokeColor());
            if (c != null) {
                colorManager.setStrokeColor(c);
                canvas.setStrokeColor(c);
            }
        });

        fillColor.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Choose Fill Color", colorManager.getFillColor());
            if (c != null) {
                colorManager.setFillColor(c);
                canvas.setFillColor(c);
            }
        });

        bar.add(select);
        bar.add(line);
        bar.add(rect);
        bar.add(circle);
        bar.addSeparator();
        bar.add(strokeColor);
        bar.add(fillColor);
        bar.addSeparator();
        bar.add(delete);
        bar.add(undo);
        bar.add(redo);
        bar.add(clear);

        return bar;
    }
}
