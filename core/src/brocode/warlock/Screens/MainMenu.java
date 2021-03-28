package brocode.warlock.Screens;

import brocode.warlock.WarLock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import brocode.warlock.Screens.Description;

public class MainMenu extends ScreenAdapter {
    WarLock game;
    OrthographicCamera guiCam;
    private Music menumusic;
    public static float mastervol = .08f;
    /*
    Rectangle soundBounds;
    Rectangle playBounds;
    Rectangle highscoresBounds;
    Rectangle helpBounds;
     */
    Vector3 touchPoint;

    public MainMenu (WarLock game) {
        this.game = game;

        //sets up camera for main menu
        guiCam = new OrthographicCamera(WarLock.V_WIDTH, WarLock.V_HEIGHT);
        guiCam.position.set(WarLock.V_WIDTH / 2, WarLock.V_HEIGHT / 2, 0);

        menumusic = WarLock.manager.get("audio/music/menumusic.mp3", Music.class);
        menumusic.setLooping(true);
        menumusic.setVolume(mastervol);
        menumusic.play();


    }

    public void update () {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            menumusic.stop();
            game.setScreen(new OfficeScreen(game));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new Description(game));
        }
/*
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                //Assets.playSound(Assets.clickSound);
                game.setScreen(new OfficeScreen(game));
                return;
            }

            if (highscoresBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new HighscoresScreen(game));
                return;
            }

            if (helpBounds.contains(touchPoint.x, touchPoint.y)) {
               // Assets.playSound(Assets.clickSound);
               // game.setScreen(new HelpScreen(game));
                return;
            }

            if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                Settings.soundEnabled = !Settings.soundEnabled;
                if (Settings.soundEnabled)
                    Assets.music.play();
                else
                    Assets.music.pause();
            }
        }
            */
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(WarLock.backgroundRegion, 0, 0, WarLock.V_WIDTH, WarLock.V_HEIGHT);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(WarLock.titleTexture, 105,155 , 180, 40);
        game.batch.draw(WarLock.wizTexture, 70, 4, 96 + 10, 96);
        game.batch.draw(WarLock.spaceTexture, 180, 50, 212, 22);
        game.batch.draw(WarLock.infoTexture, 195, 25, 179, 17);


        //game.batch.draw(Box2DUtils.Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
        game.batch.end();
    }

    @Override
    public void render (float delta) {
        update();
        draw();
    }

    @Override
    public void pause () {
        //Settings.save();
    }
}
