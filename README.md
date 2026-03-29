# Paint App JPanel Applet Design Draft

## Project Description
This project aims to develop a paint application utilizing Java's JPanel for rendering graphics. The applet will enable users to draw, manipulate shapes, and manage colors dynamically. 

### Features:
- User can draw geometric shapes (circle, rectangle, line, etc.).
- Mouse input handling for drawing and manipulating the shapes.
- Color management for shape fill and stroke.
- Undo and redo functionality for shape manipulation.

## Class Hierarchy Diagram
```
PaintApplet
├── Shape
│   ├── Circle
│   ├── Rectangle
│   └── Line
├── ColorManager
│   ├── ColorPalette
│   └── ColorPicker
├── InputHandler
│   ├── MouseInput
│   └── KeyboardInput
└── ShapeManipulator
    ├── MoveShape
    ├── ResizeShape
    └── DeleteShape
``` 

### Class Descriptions:
1. **PaintApplet**: The main JFrame that holds the JPanel where the drawing takes place.
2. **Shape**: An abstract class representing a generic shape. 
    - **Circle**: A shape that represents a circle.
    - **Rectangle**: A shape that represents a rectangle.
    - **Line**: A shape that represents a line.
3. **ColorManager**: Handles color selection and storage. 
    - **ColorPalette**: Default colors available to the user.
    - **ColorPicker**: Allows custom color selection.
4. **InputHandler**: Captures user input from mouse and keyboard.
    - **MouseInput**: Specifically handles mouse actions like click, drag, and release.
    - **KeyboardInput**: Handles keyboard events (e.g., shortcuts for actions).
5. **ShapeManipulator**: Provides functions for manipulating shapes on the canvas.
    - **MoveShape**: Allows moving shapes around.
    - **ResizeShape**: Resizes the selected shape.
    - **DeleteShape**: Deletes the selected shape from the canvas.

## Implementation Notes:
### Geometric Shapes:
- Each shape class extends the abstract Shape class and implements its draw and calculateArea methods.

### Mouse Input Handling:
- Implement MouseListener and MouseMotionListener to handle user interactions.
- Track the mouse's position to start and end drawings or manipulations.

### Color Management:
- Use Java's Color class to manage RGB values and create custom colors.

### Shape Manipulation Features:
- Implement functionalities to select, move, resize, and delete shapes.
- Keep track of selected shapes to apply transformations.

## Conclusion
This design draft outlines the core components of the paint app and serves as a foundational document for further development. Future enhancements may include adding support for advanced shapes, layers, and saving artwork.