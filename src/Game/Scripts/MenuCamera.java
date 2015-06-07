package Game.Scripts;

import Game.Scenes.*;
import PicassoEngine.*;

public class MenuCamera extends PicassoScript {
	
	private PicassoEngine engine;
	private GUIElement title;
	private GUIElement selectLevel;
	private GUIElement selectLevelHover;
	private GUIElement quit;
	private GUIElement quitHover;
	private GUIElement back;
	private GUIElement backHover;
	private GUIElement level1;
	private GUIElement level1Hover;
	private GUIElement level2;
	private GUIElement level2Hover;
	private boolean showingLevelSelect = false;
	
	public MenuCamera(GameObject gameObject, Scene scene, PicassoEngine engine) {
		super(gameObject);
		this.engine = engine;
		
		// Menu Elements
		title = new GUIElement(0, "images/GUI/menu.png", 50, 50, 50, 100, 0, -10);
		selectLevel = new GUIElement(0, "images/GUI/select_level.png", 50, 50, 50, 0, 0, 10);
		selectLevelHover = new GUIElement(0, "images/GUI/select_level_hover.png", 50, 50, 50, 0, 0, 10);
		quit = new GUIElement(0, "images/GUI/quit.png", 50, 50, 50, 0, 0, 196);
		quitHover = new GUIElement(0, "images/GUI/quit_hover.png", 50, 50, 50, 0, 0, 196);
		
		// Level Selection Elements
		back = new GUIElement(0, "images/GUI/back_to_main_menu.png", 50, 50, 50, 100, 0, -100);
		back.hide();
		backHover = new GUIElement(0, "images/GUI/back_to_main_menu_hover.png", 50, 50, 50, 100, 0, -100);
		backHover.hide();
		level1 = new GUIElement(0, "images/GUI/level1.png", 50, 50, 100, 50, -10, 60);
		level1.hide();
		level1Hover = new GUIElement(0, "images/GUI/level1_hover.png", 50, 50, 100, 50, -10, 60);
		level1Hover.hide();
		level2 = new GUIElement(0, "images/GUI/level2.png", 50, 50, 0, 50, 10, 60);
		level2.hide();
		level2Hover = new GUIElement(0, "images/GUI/level2_hover.png", 50, 50, 0, 50, 10, 60);
		level2Hover.hide();
		
		// Show GUI elements
		scene.addGUIElement(title);
		scene.addGUIElement(selectLevel);
		scene.addGUIElement(selectLevelHover);
		scene.addGUIElement(quit);
		scene.addGUIElement(quitHover);
		scene.addGUIElement(back);
		scene.addGUIElement(backHover);
		scene.addGUIElement(level1);
		scene.addGUIElement(level1Hover);
		scene.addGUIElement(level2);
		scene.addGUIElement(level2Hover);
	}
	
	public void Update() {
		if (!showingLevelSelect) {
			if (selectLevel.mouseHover() || selectLevelHover.mouseHover()) {
				selectLevel.hide();
				selectLevelHover.show();
			} else {
				selectLevel.show();
				selectLevelHover.hide();
			}
			
			if (quit.mouseHover() || quitHover.mouseHover()) {
				quit.hide();
				quitHover.show();
			} else {
				quit.show();
				quitHover.hide();
			}
			if (quit.mouseUp() || quitHover.mouseUp()) {
				Application.quit();
			}
			
			if (selectLevel.mouseUp() || selectLevelHover.mouseUp()) {
				showingLevelSelect = true;
				title.hide();
				selectLevel.hide();
				selectLevelHover.hide();
				quit.hide();
				quitHover.hide();
				back.show();
				backHover.show();
				level1.show();
				level1Hover.show();
				level2.show();
				level2Hover.show();
			}
		} else {
			if (back.mouseHover() || backHover.mouseHover()) {
				back.hide();
				backHover.show();
			} else {
				back.show();
				backHover.hide();
			}
			
			if (level1.mouseHover() || level1Hover.mouseHover()) {
				level1.hide();
				level1Hover.show();
			} else {
				level1.show();
				level1Hover.hide();
			}
			
			if (level2.mouseHover() || level2Hover.mouseHover()) {
				level2.hide();
				level2Hover.show();
			} else {
				level2.show();
				level2Hover.hide();
			}
			
			if (level1.mouseUp() || level1Hover.mouseUp()) {
				engine.loadScene(new level1());
			}
			
			if (level2.mouseUp() || level2Hover.mouseUp()) {
				engine.loadScene(new level2());
			}
			
			if (back.mouseUp() || backHover.mouseUp()) {
				showingLevelSelect = false;
				title.show();
				selectLevel.show();
				selectLevelHover.show();
				quit.show();
				quitHover.show();
				back.hide();
				backHover.hide();
				level1.hide();
				level1Hover.hide();
				level2.hide();
				level2Hover.hide();
			}
		}
		
		// Rotate background
		gameObject.addRotation(new Vector3(0, Time.deltaTime * 0.25, 0));
	}
}
