package brocode.warlock.Screens;

import brocode.warlock.Scenes.Hud;
import brocode.warlock.Sprites.Wizard;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class OfficeScreen implements Screen {
    //Reference to our Game, used to set Screens
    private WarLock game;
    private TextureAtlas atlas;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr; //graphical representation of our fixtures and bodies within our 2d world

    //Realizes wizard character
    private Wizard player;
    private Music music;

    public OfficeScreen(WarLock game){
        atlas = new TextureAtlas("Wizard GFX/wizard.pack");
        this.game = game;
        //create cam used to follow wizard through cam world
        gamecam = new OrthographicCamera();

        gamePort = new FitViewport(WarLock.V_WIDTH / WarLock.PPM, WarLock.V_HEIGHT / WarLock.PPM, gamecam);
        //hud = new Hud(game.batch);

        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("office.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / WarLock.PPM);

        //initially set our gamecam to be centered correctly at the start of the game
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //doSleep = true means bodies at rest won't get physics calculations.. saves time processing
        world = new World(new Vector2(0, 0), false);
        //creates green lines to debug boundaries
        b2dr = new Box2DDebugRenderer();

        player = new Wizard(this);

        //music = WarLock.manager.get("file/path.mp3", Music.class);
        //music.setLooping(true);
        //music.play();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.b2body.applyLinearImpulse(new Vector2(0, 0.1f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            player.b2body.applyLinearImpulse(new Vector2(0, -0.1f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

    }

    public void update(float dt){
        handleInput(dt);

        //for box2d to execute our physics simulation, we must tell it how many times to calculate per second
        //velocity and position affect how two bodies interact during a collision: higher numbers = longer but more precise
        //timeStep = 1/60 = 60 times per second
        world.step(1/60f, 6, 2);

        player.update(dt);

        hud.update(dt);

        //attach our gamecam to our players.x coordinate
        gamecam.position.x = player.b2body.getPosition().x;

        //update our gamecam with correct coordinates after changes
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines (green lines around objects represent collision areas) (also: gamecam.combined is the projection matrix)
        b2dr.render(world, gamecam.combined);

        //main cam when running through the game
        game.batch.setProjectionMatrix(gamecam.combined);
        //open box to put all textures we want inside
        game.batch.begin();
        //giving sprite game batch to be drawn
        player.draw(game.batch);

        game.batch.end();

        //Set our batch to now draw what the Hud camera sees
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        //hud.dispose();
    }
}
