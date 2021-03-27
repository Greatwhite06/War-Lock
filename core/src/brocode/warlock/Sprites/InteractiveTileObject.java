package brocode.warlock.Sprites;

import brocode.warlock.Screens.OfficeScreen;
import brocode.warlock.WarLock;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(OfficeScreen screen, Rectangle bounds) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        //define the type of properties our body will contain
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / WarLock.PPM, (bounds.getY() + bounds.getHeight() / 2) / WarLock.PPM);

        //add this body to our box2d world
        body = world.createBody(bdef);

        //setAsBox = define fixture
        shape.setAsBox((bounds.getWidth() / 2) / WarLock.PPM, (bounds.getHeight() / 2) / WarLock.PPM);
        fdef.shape = shape;
        // capture barrier in fixture
        fixture = body.createFixture(fdef);
    }

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }
}