# sudokuGUI Game
I completed this project individually to produce a software product - sudoku game. Gade: A-

The project contains two stages:
- In Stage 1, I developed a Command-line User Interface (CUI) version of the
product. I used text files to store input and output data from the program
-  In Stage 2, I upgraded the product to a Graphical User Interface (GUI) version.
Meanwhile, I included a Database component to the product, implement MVC design
patterns and included necessary unit tests for important functionalities of the program.

## Game Overview
#### Login Page
This is a page for the player to login into the game. Datebase will be connected to record the user account info and check whether the user is an existing user.

![login_page](/images/player_register.PNG)

#### Main Game
- After the user login, there will be a randomly generated sudoku game for the player.
- The player can be aware of the game rule by clicking the **Game Rule**.
- By clicking **New Game**, the player will get a new randomly generately game.
- If the player decides to **Quit** or **Submit**, player will get the scores she/he gains and the scores will be stored into the database.

![main_game](/images/main_game.PNG)
