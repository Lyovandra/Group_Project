package paintapp;

/**
 * Represents the different modes available in the paint application.
 * Each mode determines how mouse interactions are interpreted.
 */
public enum ToolMode {
    /** Selection mode for moving or resizing existing shapes. */
    SELECT,
    /** Mode for drawing straight lines. */
    LINE,
    /** Mode for drawing rectangles. */
    RECTANGLE,
    /** Mode for drawing circles. */
    CIRCLE
}
