package brocode.warlock.Tools;

import brocode.warlock.Sprites.InteractiveTileObject;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener { //what gets called when two figures in box2d collide with each other
    @Override
    public void beginContact(Contact contact) { //when two figures begin to collide

    }

    @Override
    public void endContact(Contact contact) { //when two figures disconnect from each other

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) { //once something has collided, can change the characteristics of object

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) { //results of collision (angles, etc)

    }
}
