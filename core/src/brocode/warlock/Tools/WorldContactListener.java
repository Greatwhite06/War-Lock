package brocode.warlock.Tools;

import brocode.warlock.Sprites.InteractiveTileObject;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener { //what gets called when two figures in box2d collide with each other
    @Override
    public void beginContact(Contact contact) { //when two figures begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        System.out.println("made it this far1");
        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            System.out.println("made it this far");
            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }
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
