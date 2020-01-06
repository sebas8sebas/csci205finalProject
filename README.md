<h3>Tanks 2D Developed by The Tank Team</h3>
<br>
<h4>Team Members:</h4>
Sebastian Ascoli
<br>
Jonathan Basom
<br>
Minh Bui
<br>
Steven Iovine
<br>
<br>

<h4>External Resources Used</h4>
<a href="https://opengameart.org/content/music-4">Main Menu Music</a>
<br>
<a href="http://www.wavsource.com/sfx/sfx2.htm">Winning/Losing Sound Effects</a>
<br>
<a href="http://soundbible.com/">Shooting/Explosion Sound Effects</a>
<br>
<a href="http://www.jjames.info/img/graphics/home_button.png">Home Button Image</a>
<br>
<a href="https://www.clipartmax.com/png/middle/0-7790_army-tank-clip-art-army-tank-clipart-clipart-panda-tank-clipart.png">Main Menu Logo</a>
<br>
<a href="https://www.pngfind.com/pngs/m/85-857342_tanks-2d-top-down-tank-hd-png-download.png">Player Tank Sprite</a>
<br>
<a href="https://media.indiedb.com/images/games/1/56/55503/MulticolorTanks.png">Various Colored Tanks</a>
<br>
<a href="https://img.favpng.com/1/17/23/sprite-gamemaker-studio-animation-2d-computer-graphics-png-favpng-yyMaXcSZd2w1h3x01vksF0Rv7.jpg">Explosion Sprite Sheet</a>
<br>
<a href="https://i7.pngguru.com/preview/199/79/820/sprite-fire-animaatio-gamemaker-studio-sprite.jpg">Bullet Fire Sprite Sheet</a>
<br>
<a href="https://www.stickpng.com/img/objects/shield/plain-silver-shield">Shield Power-Up Icon</a>
<br>
<a href="https://i.pinimg.com/originals/68/d7/cf/68d7cff1b56135cd1aa13d3057858452.png">Wall Materials 1</a>
<br>
<a href="https://i.pinimg.com/originals/58/19/29/58192975a3dbee15df617973d99dd6f6.png">Wall Materials 2</a>
<br>
<a href="https://www.pngix.com/viewpng/xoihom_play-pause-button-transparent-png-download-windows-media/">Pause Icon</a>
<br>
<a href="https://s.pngix.com/pngfile/s/149-1496672_you-win-you-win-pixel-art-hd-png.png">Winning Scene Image</a>
<br>
<a href="http://slick.ninjacave.com/javadoc/">Slick 2D API</a>
<br>
<a href="https://github.com/williamahartman/Jamepad">Jamepad Open Source Library</a>
<br>
<a href="https://alvinalexander.com/java/java-mouse-current-position-location-coordinates">Java - Getting the mouse cursor location (current mouse position)</a>
<br>
<a href="https://stackoverflow.com/questions/37312791/controlling-pc-cursor-with-joystick-in-java">Controlling PC Cursor with Joystick in Java</a>
<br>
<a href="https://blog.jetbrains.com/idea/2010/08/quickly-create-jar-artifact/">Quickly Create Jar Artifact for Application</a>
<br>
<a href="http://ninjacave.com/jarsplice">Tool to Generate Proper .jar File</a>
<br>
<br>

<h4>Project Description</h4>
<br>
<p>
Tanks 2D is an interactive video game where the user controls a tank and attempts to shoot other tanks without being shot first in order to win the game. The game consists of two different game modes: single player and two player.  In the single player mode, the user has to beat 10 different levels in order to beat the game; These 10 levels increase in difficulty as the game progresses. In each level, the user battles against computer controlled tanks.  In the two player mode, two users control tanks that fight against each other, and the player who dies first loses.  If the 2 player controlled tanks collide, or for some reason, they die at the same time, then the game ends in a tie.
</p>
<br>

<h5>How to Play</h5>
<p>
When the application starts, the theGame menu appears with background music and four buttons that the user can select: single player game, two player game, settings menu, or instructions menu.  If the single player game is selected, the campaign mode begins with the maze for level 1 out of 10.  The player must navigate through the maze and try to shoot and destroy the various enemy tanks before advancing to the next level.  If the player tank dies, then the player loses a life, and the current level restarts if the player still has lives remaining.  The number of lives as well the number of kills throughout the single player campaign will be displayed on the top of the screen.  The top of the screen will also contain the current level number.  Each level has its own unique maze where tanks cannot move or shoot through the walls.  The moving enemy tanks will navigate through the mazes on their own, while the static tanks will wait for the player controlled tank to approach.  Both types of enemies will fire as soon as they have a clear path to the player.  Therefore, players may find it advantageous to hide behind walls and try to bounce the bullets off the walls at the enemies.  If the player completes all 10 levels, then the player is notified that they won the game, and they can go back to the theGame menu on their command.
</p>
<p>
After selecting the two player game mode from the theGame menu, a new maze loads on the screen and allows a simple, local two player game.  There are no computerized enemy tanks, but now there are two user controlled tanks.  Each player tries to destroy the other and be the last tank standing.  The player that dies first loses, and the other player wins. If both players die simultaneously, or if both tanks run into each other, then the game ends in a tie.  If at any point during a single or two player game a player wishes to pause the game, they may choose to do so.
</p>
<p>
If the settings menu is selected from the theGame menu, the user has three different settings that may be adjusted.  First, the difficulty of a single player game can be changed by selecting one of three options: easy, medium, or hard.  These options determine how fast enemy tanks can move and rotate and how many bullets an enemy can shoot at once.  The next setting allows the user to change the display size of the application so that the game properly fits his/her computer’s screen or personal preferences.  There are three display sizes available: small (500 x 500), medium (1000 x 1000) and large (1500 x 1500).  The final settings option allows the user to toggle between whether or not the FPS (frames per second) should be displayed on the screen during a game (both single and two player).
</p>
<p>
The last option on the theGame menu takes the user to the instructions scene, which contains all of the different possible controls for the game.  Players can choose between using keyboard controls or video game controllers.  Controllers that are supported include Xbox One, Xbox 360, and PS4.  <b>Note:</b> Mac users may have to install third party software in order for certain video game controllers to be compatible.   
</p>
<br>

<h5>Gameplay Controls</h5>
<table style="width:100%">
  <tr>
    <th>Action</th>
    <th>Keyboard Player 1</th>
    <th>Keyboard Player 2 <br>(2 Player Mode Only)</th>
    <th>Xbox Controller</th>
    <th>Playstation Controller</th>
  </tr>
  <tr>
    <td>Move Forwards</td>
    <td>Up Arrow</td>
    <td>W</td>
    <td>Left Joystick Up</td>
    <td>Left Joystick Up</td>
  </tr>
  <tr>
    <td>Move Backwards</td>
    <td>Down Arrow</td>
    <td>S</td>
    <td>Left Joystick Down</td>
    <td>Left Joystick Down</td>
  </tr>
  <tr>
    <td>Rotate Right</td>
    <td>Right Arrow</td>
    <td>D</td>
    <td>Left or Right Joystick Right</td>
    <td>Left or Right Joystick Right</td>
  </tr>
  <tr>
    <td>Rotate Left</td>
    <td>Left Arrow</td>
    <td>A</td>
    <td>Left or Right Joystick Left</td>
    <td>Left or Right Joystick Left</td>
  </tr>
  <tr>
    <td>Shoot</td>
    <td>Space Bar</td>
    <td>1</td>
    <td>Right Trigger</td>
    <td>Right Trigger</td>
  </tr>
  <tr>
    <td>Pause / Unpause</td>
    <td>P</td>
    <td>P</td>
    <td>Start Button</td>
    <td>Options Button</td>
  </tr>
  <tr>
    <td>Go to Main Menu</td>
    <td>Esc or Click Home Icon</td>
    <td>Esc or Click Home Icon</td>
    <td>Hold Back and Start</td>
    <td>Hold Share and Options</td>
  </tr>
</table>
<br>

<h4>Package Structure</h4>
<p>
The source code for this project is located within the /src directory.  Within that folder, there are five subdirectories:
gamePieces, interfaces, theGame, scenes, and utilities.  The gamePieces directory has all of the important classes needed to create a 
basic game.  There is the bullet subdirectory, which contains the Bullet class, the mazes subdirectory, which contains the Maze class
and all of the other classes needed to construct a maze, the shield subdirectory, which contains the Shield class, and there is 
the tanks subdirectory, which contains all of the different types of Tank classes such as PlayerTank, EnemyTank (abstract), 
MovingEnemyTank, and SimpleStaticEnemy.
</p>
<p>
The interfaces directory contains all of the interfaces that are used within the system such as Renderable, Drawable, and Intersectable.
The theGame directory contains a class called TheGame which encapsulates all of the classes needed to create the Tanks2D application.
</p>
<p>
The scenes directory contains all of the different scenes that can be displayed on the screen while the application is running.  The two
theGame subdirectories are gameScenes and menuScenes.  The gameScenes directory has an abstract class GameScene that is the basis for any 
scene that consists of actually playing the game.  There are also two more subdirectories: singlePlayerGame and twoPlayerGame.  As the 
names indicate, the singlePlayerGame directory contains all of the levels for a single player game, while the twoPlayerGame directory
contains the scene for a local two player game.  With regards to the menuScenes directory, there are three subdirectories containing the
classes for the start menu, the settings menu, and the instructions menu.  There is also a MenuButton class that is used to add buttons
to the menus.
</p>
<p>
The final package is the utilities directory.  This contains a simple utilities class called Utilities that is used at various points
throughout the project.  
</p>
<p>
Finally, the Main class is located directly within the src directory and runs an instance of TheGame class in order to start a game.
</p>
<br>

<h4>Third Party Libraries</h4>
<br>
<a href="http://slick.ninjacave.com/">Slick2D Build 237 updated to include LWJGL 2.9.2<a>
<br>
<a href="https://github.com/williamahartman/Jamepad">Jamepad 1.3.2</a>
<br>
<br>

<h4>Build and Run Instruction</h4>
<br>
<h5>How to Build Project into a .jar File<h5>

<p>
In IntelliJ, do the following:


1. Make sure the project folder is selected as a resource in the project structure (if you do not do this, then the images and sound will not load)

2. Follow <a href="https://blog.jetbrains.com/idea/2010/08/quickly-create-jar-artifact/">these instructions</a>. However, you must select the option "copy to the output directory and link via manifest." Also make sure to select the Main class.

3. Go to build -> build artifact -> build

4. You will get many .jar files in the out/artifacts directory (which will not work outside of IntelliJ). In order to get only one working .jar file, you need to download <a href="http://ninjacave.com/jarsplice">this tool</a>, and follow the 
instructions on the website to generate a .jar file. You must use all the .jar files IntelliJ generates, but you must not select any native library because those are included in IntelliJ's .jar files and will be detected as duplicates.

5. You should be able to simply click on the .jar file generated and run the program
</p>
<br>

<h5>How to Run the .jar File<h5>
<p>
Within the /dist directory, there should be a file called tanks2d.jar.  This is the .jar file that is needed in order to 
run the entire application. This jar file was compiled using java sdk 12, so if you are going to run it, make sure you are using 
java jre 12 or newer. If you wish to run the original code, you can simply go into the /src directory and run the 
Main class. 
</p>

<h5>How to Run the project in intelliJ<h5>
<p>
If you want to run the project in IntelliJ, you must go to file > project structure > libraries, click on the “+” icon and select 
the lib folder. After doing that, the external libraries should be configured properly and you should be able to run the Main class 
within IntelliJ’s environment.
</p>



