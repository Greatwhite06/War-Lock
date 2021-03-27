package brocode.warlock;

import brocode.warlock.Screens.OfficeScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarLock extends Game {
	public static final int V_WIDTH = 408;
	public static final int V_HEIGHT = 208;

	//Pixels per meter: use this float (divisional reasons) to scale everything that has pixel size (positional locations, viewports, character models)
	public static final float PPM = 100;

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
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}