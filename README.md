# 2d-racing-game

Description: This program runs a simple racing game. All cars start at the starting line, after a few seconds they are animated to
'race' across the GUI to the finish line. Whichever car reaches the finish line first is declared the winner at the bottom of the screen.

Class functionalities:

RacingGame.java--adds players based on the number entered into the for loop. Calls main game functionalites and checks if the thread is running on the EDT.

GameHandler.java--creates player objects and stores them in an array. This class also handles winning conditions, player positions, animations, and GUI components.

Player.java--representative of each 'player'. Handles the position of the individual player as it relates to the player displayed on the GUI, the moves the player makes and the player images (different colored cars depending on number of players entered in RacingGame.java).

Response--handles the move type the player makes using a Random number generator and switch case in a Factory Design Pattern.

Note* This program was created with Eclipse in java

Screenshots:
https://user-images.githubusercontent.com/83840115/117706058-728b3780-b192-11eb-94fc-b69654d7f4b6.png

https://user-images.githubusercontent.com/83840115/117706060-7323ce00-b192-11eb-9647-7a61e0607955.png

Sequence Diagram for RacingGame Main Method:
https://user-images.githubusercontent.com/83840115/117706018-669f7580-b192-11eb-8010-dedef7dfc286.png

Class Diagram:
https://user-images.githubusercontent.com/83840115/117706022-67380c00-b192-11eb-9c4f-ab22ff766c26.jpg




