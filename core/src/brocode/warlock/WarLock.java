package brocode.warlock;

import brocode.warlock.Screens.OfficeScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarLock extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final String TITLE = "W A R L O C K", VERSION = "1.0";

	// Pixels per meter: use this float (divisional reasons) to scale everything that has pixel size (positional locations, viewports, character models)
	public static final float PPM = 100;

	// setup default values for filters. We are using powers of 2 for convenience
	public static final short GROUND_BIT = 1;
	public static final short WIZARD_BIT = 2;
	public static final short FURNITURE_BIT = 4;

	public SpriteBatch batch;

	/* NOTE: using AssetManager in a static way can cause issues. Instead, we may want to pass around Assetmanager to
	the classes that need it. It is used in a static context here to save time */
	public static AssetManager manager;

	@Override
	public void create() {
		batch = new SpriteBatch();
		manager = new AssetManager();


		setScreen(new OfficeScreen(this));
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