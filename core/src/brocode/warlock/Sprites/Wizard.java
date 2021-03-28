package brocode.warlock.Sprites;

import brocode.warlock.Screens.OfficeScreen;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

public class Wizard extends Sprite {
    public boolean isRunningRight() {
        return runningRight;
    }
    public void setRunningRight(boolean runningRight) {
        this.runningRight = runningRight;
    }

    public enum State {STANDING, RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private Vector2 velocity = new Vector2();
    private float speed = 60 * 2, gravity = 60 * 1.8f;
    private TextureRegion wizardStand;
    private Animation<TextureRegion>wizardRun;
    private float stateTimer;
    private boolean runningRight;

    public Wizard(OfficeScreen screen){
        super(screen.getAtlas().findRegion("isowizard"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // creates an array of texture regions to pass the constructor for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 8; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        wizardRun = new Animation(0.1f, frames);
        frames.clear();

        wizardStand = new TextureRegion(getTexture(), 0, 0, 32, 32);

        defineWizard();
        setBounds(0, 0, 32 / WarLock.PPM, 32 / WarLock.PPM);
        setRegion(wizardStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        //returns the appropriate frame that will display the sprite's texture region
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case RUNNING:
                region = wizardRun.getKeyFrame(stateTimer, true); // this is a looping animation, if returns to end, will return to first frame
                break;
            case STANDING:
            default:
                region = wizardStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        //if our currentstate != previousstate we need to reset the timer
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if (b2body.getLinearVelocity().x != 0 || b2body.getLinearVelocity().y != 0) // wizard not at a state of rest? run animation
            return State.RUNNING;
        else                                        // wizard doing something else? stand animation
            return State.STANDING;
    }

    public boolean gameOverReached(){
        return true;
        // FIXME: 3/27/2021  add condition for game over being reached and return
    }

    public float getStateTimer(){
        return stateTimer;
    }

    //Wizard's body and fixture definitions
    public void defineWizard(){
        BodyDef bdef = new BodyDef();

        //test start position on map
        // FIXME: 3/28/2021 change for spawn location
        bdef.position.set(256 / WarLock.PPM, 256 / WarLock.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //Now that the wizard's defined, we are able create in our world
        b2body = world.createBody(bdef);

        //defining wizard's body properties
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(14 / WarLock.PPM);

        fdef.filter.categoryBits = WarLock.WIZARD_BIT;
        //these are the things mario can collide with
        fdef.filter.maskBits = WarLock.GROUND_BIT | WarLock.COMPUTER_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape head = new EdgeShape();
        //where?
        head.set(new Vector2(-2 / WarLock.PPM, 12 / WarLock.PPM), new Vector2(2 / WarLock.PPM, 12 / WarLock.PPM));

        fdef.shape = head;
        // when you create a fixture definition that is a sensor, it no longer collides with anything in the world, it is just available for you to query for user data
        fdef.isSensor = true;

        // this will uniquely identify this head fixture as "head" so we can pull this in the future to see if this fixture is mario's head
        b2body.createFixture(fdef).setUserData("head");

    }
}
