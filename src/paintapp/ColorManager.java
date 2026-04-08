package paintapp;

import java.awt.Color;

public class ColorManager {
    private Color strokeColor = Color.BLACK;
    private Color fillColor = new Color(0, 0, 0, 0);

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public String[] getColorNames() {
        return new String[] {
            "Black", "Red", "Green", "Blue", "Yellow", "Orange", "Magenta", "Cyan"
        };
    }

    public Color[] getColors() {
        return new Color[] {
            Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.MAGENTA, Color.CYAN
        };
    }
}
