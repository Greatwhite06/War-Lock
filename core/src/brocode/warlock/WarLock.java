package brocode.warlock;

import brocode.warlock.Screens.MainMenu;
import brocode.warlock.Tools.KeyController;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
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
	public static final short COMPUTER_BIT = 8;
	public static final short ACCESSED_BIT = 16;
	public SpriteBatch batch;
	public static boolean inRange = false;

	public static Texture background;
	public static TextureRegion backgroundRegion;

	public static Texture wizLink;
	public static Texture titleLink;
	public static Texture spaceLink;
	public static Texture infoLink;
	public static TextureRegion wizTexture;
	public static TextureRegion spaceTexture;
	public static TextureRegion infoTexture;
	public static TextureRegion gameOver;
	public static TextureRegion highScoresRegion;
	public static TextureRegion titleTexture;
	public static TextureRegion soundOn;
	public static TextureRegion soundOff;
	public static TextureRegion arrow;
	public static TextureRegion pause;

	public KeyController controller;

	/* NOTE: using AssetManager in a static way can cause issues. Instead, we may want to pass around Assetmanager to
	the classes that need it. It is used in a static context here to save time */
	public static AssetManager manager;

	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	@Override
	public void create() {
		controller = new KeyController();
		//tells GDX to listen for keypresses
		Gdx.input.setInputProcessor(controller);

		//Loads in texture for background and sizes it
		manager = new AssetManager();
		background = loadTexture("Wizard GFX/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 400, 208);

		//loads in texture for single wizard and gives it a size
		wizLink = loadTexture("Wizard GFX/SingleWizard.png");
		titleLink = loadTexture("Wizard GFX/War-Lock.png");
		spaceLink = loadTexture("Wizard GFX/Press-Space-to-Start.png");
		infoLink = loadTexture("Wizard GFX/Press-esc-for-info.png");

		wizTexture = new TextureRegion(wizLink, 0, 0, 32, 32);
		spaceTexture = new TextureRegion(spaceLink, 0, 0, 212, 21);
		infoTexture = new TextureRegion(infoLink, 0, 0, 179, 17);
		//gameOver = new TextureRegion(items, 352, 256, 160, 96);
		//highScoresRegion = new TextureRegion(WarLock.items, 0, 257, 300, 110 / 3);
		titleTexture = new TextureRegion(titleLink, 0, 0, 138, 22);
		//soundOff = new TextureRegion(items, 0, 0, 64, 64);
		//soundOn = new TextureRegion(items, 64, 0, 64, 64);
		//arrow = new TextureRegion(items, 0, 64, 64, 64);
		//pause = new TextureRegion(items, 64, 64, 64, 64);
		batch = new SpriteBatch();
		manager.load("audio/music/virusmusic.mp3", Music.class);
		manager.load("audio/music/menumusic.mp3", Music.class);
		manager.finishLoading();

		setScreen(new MainMenu(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
		wizLink.dispose();
		spaceLink.dispose();
		titleLink.dispose();
		infoLink.dispose();
		background.dispose();
	}


	@Override
	public void render() {
		super.render();
		manager.update();
	}
}