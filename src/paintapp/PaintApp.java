package paintapp;

import javax.swing.SwingUtilities;

public class PaintApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintFrame frame = new PaintFrame();
            frame.setVisible(true);
        });
    }
}
