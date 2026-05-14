package paintapp;

import javax.swing.SwingUtilities;

/**
 * Entry point for the Paint Application.
 * This class initializes the user interface and starts the application.
 */
public class PaintApp {
    /**
     * Main method to launch the application.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintFrame frame = new PaintFrame();
            frame.setVisible(true);
        });
    }
}
