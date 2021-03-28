package brocode.warlock.actors;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

public class Wizard extends Actor {
    public Sprite sprite;
    final float STEP = 1f;
    public enum PlayerMoveState {RIGHT, LEFT, UP, DOWN, IDLE}
    public PlayerMoveState moveState;
    private TextureRegion wizardStand;
    private Animation<TextureRegion> wizardRun;
    private float stateTimer;

    public Wizard(TextureAtlas atlas, String actorName) {
        super(atlas.findRegion("isowizard"));
        moveState = PlayerMoveState.IDLE;
        sprite = atlas.createSprite(actorName);

        // creates an array of texture regions to pass the constructor for the animations
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 9; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        wizardRun = new Animation(0.1f, frames);
        frames.clear();
        wizardStand = new TextureRegion(getTexture(), 0, 0, 32, 32);

        this.setWidth(sprite.getWidth());
        this.setHeight(sprite.getHeight());
        spritePos(sprite.getX(), sprite.getY());

        setTouchable(Touchable.enabled);
        sprite.setRegion(wizardStand);
    }

    public TextureRegion getFrame(float dt) {

        TextureRegion region;
        if(moveState != PlayerMoveState.IDLE){
            region = wizardStand;
        }
        else{
            region = wizardRun.getKeyFrame(stateTimer, true); // this is a looping animation, if returns to end, will return to first frame
        }

        //if our currentstate != previousstate we need to reset the timer
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }


    public void spritePos(float x, float y){
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public boolean keyUp(InputEvent event, int keycode) {
        moveState = PlayerMoveState.IDLE;
        return false;
    }

    public void update(float dt){
        //returns the appropriate frame that will display the sprite's texture region
        sprite.setRegion(getFrame(dt));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch(moveState) {
            case RIGHT :
                // Move right by using whatever method you want.
                this.setPosition(getX() + STEP,getY());
                break;
            case LEFT :
                this.setPosition(getX() + STEP,getY());
                // Move left by using whatever method you want.
                break;
            case IDLE :
                // Don't change x coordinate of your player.
                break;
            default :

                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
