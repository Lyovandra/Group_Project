# Paint Application

## Project Description
A comprehensive Java Swing paint application that allows users to draw, move, resize, and delete geometric shapes. It features a robust undo/redo system and color management.

## Features
- **Geometric Shapes**: Draw lines, rectangles, and circles.
- **Selection Mode**: Select shapes to move or resize them.
- **Undo/Redo**: Full support for undoing and redoing actions.
- **Color Customization**: Set stroke and fill colors for shapes.
- **Keyboard Shortcuts**: 
  - `Delete`: Remove selected shape.
  - `Ctrl+Z`: Undo.
  - `Ctrl+Y`: Redo.

## Class Structure
- **PaintApp**: The entry point of the application.
- **PaintFrame**: The main window containing the UI components.
- **DrawingCanvas**: The core component where shapes are rendered and user input is handled.
- **PaintShape**: Abstract base class for all shapes (`LineShape`, `RectangleShape`, `CircleShape`).
- **CommandManager**: Handles the command pattern for undo/redo functionality.
- **ShapeCommand**: Interface for undoable actions (`AddShapeCommand`, `DeleteShapeCommand`, `MoveShapeCommand`, `ResizeShapeCommand`).
- **ColorManager**: Manages the current color state.

## Presentation Materials
- `OO(PAINT).pptx`: Project presentation slides.

## How to Run
1. Compile the Java files in the `src/paintapp/` directory.
2. Run the `paintapp.PaintApp` class.

```bash
javac src/paintapp/*.java
java -cp src paintapp.PaintApp
```
