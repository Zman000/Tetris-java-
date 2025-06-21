# Tetris (java)

## V1.01
## The following content is related to V1.01 of the game

## Introduction
- This is a basic Tetris game made using Java libraries only, mainly utilising **Swing** and **Java AWT** for the graphics.
- Collision detection, checking completed lines were all done manually and a functioning game was made.

## Features
- The blocks (called **Tetrominoes**) are stored in a separate file (**Tetrominoes.java**) in the format of 4x4 final boolean variables and are generated using *getRandomShape()* function.
- The blocks also support **Rotation** achieved by **rotateClkWise()** function in **Tetromino.java** by flipping the values of the shape variable.
- Player has the following controls:
 -  **Left** -> left_arrow_key
 -  **Right** -> right_arrow_key
 -  **Rotate clockwise** -> up_arrow_key
 -  **Soft drop** -> down_arrow_key
 -  **Hard drop** -> spacebar

- Upon completing a line, a message is displayed in the terminal, since no feature of score tracking was added. This is handled by **checkLine()** in **GamePanel.java**.
- The speed of the game will remain same throught the gameplay no matter what event occurs.

## Limitations
- Same color of all *Tetrominoes*, intoduces monotonous nature
- No score-tracking method, no excitement 
- No *Levels* or *Gravity* changes when clearing lines

## Requirements
- Java 8 or higher
- A basic terminal or IDE (VS Code / IntelliJ / Sublime Text)

## Steps to run
1. Download the repo or clone on local machine
  ```bash
  git clone https://github.com/Zman000/demo-repo.git
  cd demo-repo/version-1
  ```
2. Compile and run the Java file:
   ```bash
   javac Tetris.java
   java Tetris
