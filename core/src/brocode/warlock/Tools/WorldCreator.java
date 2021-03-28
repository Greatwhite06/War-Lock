package brocode.warlock.Tools;

import brocode.warlock.Screens.OfficeScreen;
import brocode.warlock.WarLock;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class WorldCreator {
    public WorldCreator(OfficeScreen screen) {
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //defines what body consists of
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //wall boundaries
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / WarLock.PPM, (rect.getY() + rect.getHeight() / 2) / WarLock.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / WarLock.PPM, (rect.getHeight() / 2) / WarLock.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //furniture boundaries
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the furniture) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / WarLock.PPM, (rect.getY() + rect.getHeight() / 2) / WarLock.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / WarLock.PPM, (rect.getHeight() / 2) / WarLock.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);
        }

        //computer
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the furniture) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / WarLock.PPM, (rect.getY() + rect.getHeight() / 2) / WarLock.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            /*
            shape.setAsBox((rect.getWidth() / 2) / WarLock.PPM, (rect.getHeight() / 2) / WarLock.PPM);
            fdef.shape = shape;

            body.createFixture(fdef);

             */
        }
    /*
        //create ground bodies/fixtures
        //the "2" in map.getLayers().get(2) references the index of the object layer in Tiled from starting from the ground up (0.background->1.graphics->*2*.ground)
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create brick bodies/fixtures (index 5)
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen, rect);
        }

        //create pipe bodies/fixtures (index 3)
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            //define the type of properties our body (the ground) will contain
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            //add this body to our box2d world
            body = world.createBody(bdef);

            //setAsBox = define fixture
            shape.setAsBox((rect.getWidth() / 2) / MarioBros.PPM, (rect.getHeight() / 2) / MarioBros.PPM);
            fdef.shape = shape;
            //this is so when enemy collides with pipe he can turn around (and not when he falls on ground or something else)
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
            body.createFixture(fdef);
        }

        //create coin bodies/fixtures (index 4)
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
            */
    }
}
