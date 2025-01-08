# Orbito: A Simple Java Game

**Orbito** is a two-player grid-based game implemented in Java using the Swing framework. The game allows two players to take turns placing their markers (`X` for Player 1 and `O` for Player 2) on a 4x4 grid, with the goal of forming a straight line of four markers horizontally, vertically, or diagonally. The game also incorporates a "rotating matrix" mechanic, adding complexity and strategy to the gameplay.

---

## Features

- **Interactive Gameplay**: Players take turns clicking buttons on a 4x4 grid to place their markers.
- **Matrix Rotation**: The grid rotates after certain phases, shifting the board dynamically and adding a strategic element.
- **Winner Detection**: The game automatically detects and announces the winner if a player forms a line of four markers.
- **Tie Detection**: If all cells are filled and no player wins, the game announces a tie.
- **Reset Functionality**: After a game ends, the board resets for a new game.

---

## Gameplay Rules

1. **Player 1 starts**:
   - Select a cell to place their marker (`X`).
   - The board rotates once after Player 1's move.

2. **Player 2 follows**:
   - Player 2 selects an initial marker to move.
   - Player 2 can move the selected marker to an adjacent empty cell.
   - Player 2 places their marker (`O`) on the grid after moving.
   - The board rotates once after Player 2's turn.

3. **Win Condition**:
   - A player wins by forming a straight line of four markers (`X` or `O`) horizontally, vertically, or diagonally.

4. **Tie Condition**:
   - If all cells are filled and no player forms a line of four, the game declares a tie.

---

## How to Run

1. **Requirements**:
   - Java Development Kit (JDK) 8 or later.
   - An IDE or text editor for running Java applications (e.g., IntelliJ IDEA, Eclipse, or VS Code).

2. **Steps**:
   - Save the provided code into two files:
     - `Orbito.java`
     - `App.java`
   - Compile the code:
     ```sh
     javac Orbito.java App.java
     ```
   - Run the application:
     ```sh
     java App
     ```

3. The game window will open, and players can start interacting with the grid.

---

## Code Overview

- **`Orbito`**:
  - The primary game logic is implemented in this class.
  - Handles the grid layout, button interactions, turn-based gameplay, matrix rotation, and win/tie detection.
  - Uses a `tracker` array to manage the state of the grid.

- **`App`**:
  - The main entry point of the program.
  - Creates an instance of `Orbito` to start the game.

---

## Key Methods

- `actionPerformed(ActionEvent e)`: Handles button clicks and manages gameplay phases.
- `updateBoard()`: Updates the button labels to reflect the current state of the grid.
- `rotateMatrix()`: Rotates the 4x4 grid matrix.
- `checkForWinner()`: Checks for winning or tie conditions.
- `reset()`: Resets the game board and state.

---

## Customization

You can customize the game by:
- Adjusting the grid size (currently 4x4).
- Changing the win condition (e.g., requiring five markers in a row).
- Modifying the rotation logic for a different gameplay experience.

---

## Known Issues and Improvements

### Issues
1. **Fixed Matrix Size**: The current implementation supports only a 4x4 grid.
2. **Limited Error Handling**: The code assumes valid inputs and may not handle edge cases gracefully.

### Potential Improvements
- Add AI for single-player mode.
- Enhance the user interface with better graphics.
- Support dynamic grid sizes.

---

## Author

This project was implemented as an interactive Java Swing application demonstrating game logic, matrix manipulation, and GUI design.

---

Enjoy playing **Orbito** and strategize to win against your opponent! 🎮
