package brocode.warlock.Screens;

import brocode.warlock.Scenes.Hud;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OfficeScreen implements Screen {
    private WarLock game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    public OfficeScreen(WarLock game){
        gamecam = new OrthographicCamera();

        //gamePort = new FitViewport()
        //hud = new Hud(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
