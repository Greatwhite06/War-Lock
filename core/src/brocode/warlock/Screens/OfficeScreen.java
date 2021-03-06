package brocode.warlock.Screens;

import brocode.warlock.ComputerObj;
import brocode.warlock.Scenes.Hud;
import brocode.warlock.Sprites.Wizard;
import brocode.warlock.Tools.WorldContactListener;
import brocode.warlock.Tools.WorldCreator;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.dermetfan.gdx.assets.AnnotationAssetManager;

public class OfficeScreen implements Screen {
    // Reference to our Game, used to set Screens
    private WarLock game;
    protected Stage stage;
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
    private Music gamemusic;
    //sets master volume
    public static float mastervol = .08f;
    //public void setMasterVolume(float i){mastervol = i;}

    private ComputerObj network;
    private int[] infectID = new int[3];

    public OfficeScreen(WarLock game) {

        atlas = new TextureAtlas("Wizard GFX/wizard.pack");
        this.game = game;
        //create cam used to follow wizard through cam world
        createCamera();

        hud = new Hud(game.batch);

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

        world.setContactListener(new WorldContactListener());
        new WorldCreator(this);
        network = new ComputerObj(0);
        createNetwork(1);
        recInfect(network,infectID);

        player = new Wizard(this);

        gamemusic = WarLock.manager.get("audio/music/virusmusic.mp3", Music.class);
        gamemusic.setLooping(true);
        gamemusic.setVolume(mastervol);
        gamemusic.play();

    }
    private void createNetwork(int val){
        System.out.print("Infected Computers ID: ");
        for(int i =0; i < 3;i++){
            infectID[i] = MathUtils.random(1,6);
            System.out.print(infectID[i] + " ");
        }
        System.out.println();

        network.left = new ComputerObj(1);
        if(network.left.val == infectID[0] || network.left.val == infectID[1] || network.left.val == infectID[2]){
            network.left.Infect();
        }
        network.right = new ComputerObj(2);
        if(network.right.val == infectID[0] || network.right.val == infectID[1] || network.right.val == infectID[2]){
            network.right.Infect();
        }
        network.left.left= new ComputerObj(3);
        network.left.right = new ComputerObj(4);
        network.right.left = new ComputerObj(5);
        network.right.right = new ComputerObj(6);
    }

    private void recInfect(ComputerObj computer, int[] infectID){
        //base case: if left and right are null, go back
        if(computer.left == null || computer.right == null) return;

        //action: check each computer for infection possibility
        for(int i = 0;i < 3; i++){
            if (computer.val == infectID[i]){
                computer.VirusOnPC = true;
                break;
            }
        }
        //recursion: check left and right
        recInfect(computer.left, infectID);
        recInfect(computer.right, infectID);
    }

    private void createCamera(){
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(WarLock.V_WIDTH * 2 / WarLock.PPM, WarLock.V_HEIGHT * 2 / WarLock.PPM, gamecam);
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if(((Gdx.input.isKeyPressed(Input.Keys.UP)) || (Gdx.input.isKeyPressed(Input.Keys.W))) && player.b2body.getLinearVelocity().y <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0, 0.25f), player.b2body.getWorldCenter(), true);
        else if(((Gdx.input.isKeyPressed(Input.Keys.DOWN)) || (Gdx.input.isKeyPressed(Input.Keys.S))) && player.b2body.getLinearVelocity().y >= -2)
            player.b2body.applyLinearImpulse(new Vector2(0, -0.25f), player.b2body.getWorldCenter(), true);
        else if (((Gdx.input.isKeyPressed(Input.Keys.RIGHT)) || (Gdx.input.isKeyPressed(Input.Keys.D))) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.25f, 0), player.b2body.getWorldCenter(), true);
        else if(((Gdx.input.isKeyPressed(Input.Keys.LEFT)) || (Gdx.input.isKeyPressed(Input.Keys.A))) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.25f, 0), player.b2body.getWorldCenter(), true);
        else {
            player.b2body.setLinearDamping(10f);
        }

        // FIXME: 3/27/2021 Pause menu implementation?
        /*
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))){
                game.setScreen(new PauseMenu(game));
        }
         */
    }

    public void update(float dt){
        handleInput(dt);
        //for box2d to execute our physics simulation, we must tell it how many times to calculate per second
        //velocity and position affect how two bodies interact during a collision: higher numbers = longer but more precise
        //timeStep = 1/60 = 60 times per second
        world.step(1/60f, 6, 2);

        player.update(dt);
        hud.update(dt);

        if(hud.worldTimer <= 0) {
            System.out.println("GAME OVER!");
            gamemusic.stop();
            game.setScreen(new GameOverScreen(game));
        }
        //attach our gamecam to our players.x coordinate
        gamecam.position.x = player.getX();
        gamecam.position.y = player.getY();

        //update our gamecam with correct coordinates after changes
        gamecam.update();
        //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //clear the game screen with Black
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
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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
        hud.dispose();
        player.getTexture().dispose();
    }
}
