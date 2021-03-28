package brocode.warlock;

import brocode.warlock.Screens.MainMenu;
import brocode.warlock.Screens.OfficeScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WarLock extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;

	// Pixels per meter: use this float (divisional reasons) to scale everything that has pixel size (positional locations, viewports, character models)
	public static final float PPM = 100;

	// setup default values for filters. We are using powers of 2 for convenience
	public static final short GROUND_BIT = 1;
	public static final short WIZARD_BIT = 2;
	public static final short FURNITURE_BIT = 4;
	public SpriteBatch batch;

	public static Texture background;
	public static TextureRegion backgroundRegion;

	public static Texture items;
	public static TextureRegion mainMenu;
	public static TextureRegion pauseMenu;
	public static TextureRegion ready;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion logo;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;

	/* NOTE: using AssetManager in a static way can cause issues. Instead, we may want to pass around Assetmanager to
	the classes that need it. It is used in a static context here to save time */
	public static AssetManager manager;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	@Override
	public void create() {
		background = loadTexture("Wizard GFX/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 400, 208);

		items = loadTexture("Wizard GFX/SingleWizard.png");
		mainMenu = new TextureRegion(items, 0, 224, 400, 208);
		//pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
		//ready = new TextureRegion(items, 320, 224, 192, 32);
		//gameOver = new TextureRegion(items, 352, 256, 160, 96);
		//highScoresRegion = new TextureRegion(WarLock.items, 0, 257, 300, 110 / 3);
		logo = new TextureRegion(items, 200, 104, 64, 64);
		//soundOff = new TextureRegion(items, 0, 0, 64, 64);
		//soundOn = new TextureRegion(items, 64, 0, 64, 64);
		//arrow = new TextureRegion(items, 0, 64, 64, 64);
		//pause = new TextureRegion(items, 64, 64, 64, 64);
		batch = new SpriteBatch();

		manager = new AssetManager();
		setScreen(new MainMenu(this));
		//setScreen(new OfficeScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}


	@Override
	public void render() {
		super.render();
		manager.update();
	}
}