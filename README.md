# 2d-racing-game

Description: This program runs a simple racing game. All cars start at the starting line, after a few seconds they are animated to
'race' across the GUI to the finish line. Whichever car reaches the finish line first is declared the winner at the bottom of the screen.

Class functionalities:

RacingGame.java--adds players based on the number entered into the for loop. Calls main game functionalites and checks if the thread is running on the EDT.

GameHandler.java--creates player objects and stores them in an array. This class also handles winning conditions, player positions, animations, and GUI components.

Player.java--representative of each 'player'. Handles the position of the individual player as it relates to the player displayed on the GUI, the moves the player makes and the player images (different colored cars depending on number of players entered in RacingGame.java).

Response--handles the move type the player makes using a Random number generator and switch case in a Factory Design Pattern.

Screenshots:



