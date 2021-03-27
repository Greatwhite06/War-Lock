package brocode.warlock.Sprites;

import brocode.warlock.Screens.OfficeScreen;
import brocode.warlock.WarLock;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class Wizard extends Sprite {
    public World world;
    public Body b2body;

    private float stateTimer;

    public Wizard(OfficeScreen screen){
        this.world = screen.getWorld();
        stateTimer = 0;

        defineWizard();
        setBounds(0, 0, 16 / WarLock.PPM, 16 / WarLock.PPM);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        //returns the appropriate frame that will display the sprite's texture region
        //setRegion(getFrame(dt));
    }

    public void defineWizard(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / WarLock.PPM, 32 / WarLock.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(12 / WarLock.PPM);

        fdef.shape = shape;

    }
}
