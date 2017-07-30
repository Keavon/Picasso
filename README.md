# Picasso Game Engine

Picasso is a very simple 3D game engine and physics engine written from scratch in Java using only the Swing standard library. It may be used to make very simple 3D games, although using a real game engine or library is advised for the purpose of creating more advanced games. It gets its name from the famous painter Pablo Picasso because of its use of the painter's algorithm for sorting the depths of faces in the geometry of the scene. Included is a demo game where the player controls a marble which must be rolled from the start to the finish without it falling off the edges of the level. The entire project consists of 2568 lines of Java code across 32 classes. Feel free to modify, adapt, and learn from the project and entirely throw out the demo game and extend the engine to suit your needs or to make your own game. If you have any questions, don't hesitate to send me an email with the address posted on my GitHub profile.

### Engine Features
* Directional lighting (scene's directional light direction can be customized to make planes facing it brighter than planes facing away to give a sense of shading)
* Colored faces (every face can be given its own color, but textures are not supported)
* OBJ 3D model file import (must be exported without normals or UVs and the material name(s) must equal the hex value of the each material's color)
* Physics engine (supports collisions between meshes and spherical rigidbodies but rolling is not always accurate)
* Sky background (4x1 panorama image is required, and this is a finicky feature)
* GUI system with clickable images (allows for the creation of clickable menus)
* Windowed or fullscreen mode (however, unable to go back from fullscreen to windowed mode)
* Multiple scenes
* Mouse movement, clicks, scroll wheel, and keyboard input
* Hidden mouse delta movement (mouse is hidden and remains centered, allowing unlimited directional mouse movement input)
* Custom geometry depth sorting (acts as render layers so specified geometry can always be rendered above or below others)

### Marble Demo Game
[Download the marble demo game](https://github.com/Keavon/Picasso/releases)

[![Click to Play Video](http://i.imgur.com/lVgFotx.png)](https://www.youtube.com/watch?v=6BFcBsFAYqU)
