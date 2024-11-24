# Pacman Game in Java  

This is a visually graphical **Pacman Game** developed in Java using the **Swing library** for creating an interactive GUI. The project utilizes **inheritance** and **composition** concepts of Object-Oriented Programming (OOP).  

## Features  
1. **Pacman (Directed Walker):**  
   - Pacman is controlled by the player using keyboard input switches of arrow keys. 
   - The goal is to eat all the food while avoiding the ghosts.  

2. **Ghosts (Random Walkers):**  
   - Ghosts move randomly across the game board.  
   - They act as obstacles to make the game challenging.  

3. **Obstacles (Walls):**  
   - The game board contains walls, which serve as barriers for both Pacman and the ghosts.  

4. **Game Rules:**  
   - Pacman has **3 turns** (lives) to clear all the food, similar to the traditional Pacman game.  
   - If Pacman collides with a ghost, one turn is lost.  

5. **Background Sound:**  
   - A sound file plays in the background to enhance the gaming experience.  

## Implementation Highlights  
- **Classes:**  
  - `DirectedWalker`: Represents Pacman and manages directional movements.  
  - `RandomWalker`: Represents ghosts and handles their random movements.  
- **GUI:**  
  - Created using the **Swing library**, ensuring an interactive and visually appealing interface.  
- **Collision Detection:**  
  - Handles collisions between Pacman, ghosts, and walls.  
- **Smooth Movement:**  
  - Ensures entities move continuously rather than abruptly.  

## How to Play  
1. Use the keyboard arrow keys to control Pacman.  
2. Avoid ghosts while navigating the maze to collect all the food items.  
3. Complete the game before running out of turns.

## Images of the game:
![Screenshot 2024-11-25 005603](https://github.com/user-attachments/assets/013cde1c-7fa5-4502-8354-bbe9a749970f)
![Screenshot 2024-11-25 005648](https://github.com/user-attachments/assets/60f176ff-1e8c-4b3e-8552-3eb66c06d8ad)


## Technologies Used  
- **Java (Swing Library):** For GUI design and game mechanics.  
- **Object-Oriented Programming:** For structured and reusable code using inheritance and composition.  
- **Audio Integration:** Adds background music to enrich the player experience.  

## How to Run  
1. Clone this repository:  
   ```bash  
   git clone https://github.com/blackdragon101/Pacman-Game.git  
   ```  
2. Open the project in your preferred Java IDE (e.g., IntelliJ, Eclipse).  
3. Compile and run the main class file to start the game.  

## Future Enhancements  
- Add more levels with increasing difficulty.  
- Introduce power-ups to allow Pacman to eat ghosts temporarily.  
- Implement a scoring system to keep track of player performance.  

Enjoy the game! 🎮
