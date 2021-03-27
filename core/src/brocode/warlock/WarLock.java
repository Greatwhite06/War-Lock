package brocode.warlock;

import brocode.warlock.Screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import brocode.warlock.Screens.MainMenu;

public class WarLock extends Game {
	SpriteBatch batch;
	Texture img;
	Texture img2;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img2 = new Texture("gitpfp.jpg");
		this.setScreen(new MainMenu(this));

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2, 100, 100);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}