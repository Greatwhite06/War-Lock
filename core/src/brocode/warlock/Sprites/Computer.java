package brocode.warlock.Sprites;

import brocode.warlock.Screens.LoggingScreen;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Computer extends InteractiveTileObject{
    public Computer(LoggingScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WarLock.COMPUTER_BIT);
    }

    @Override
    public void onKeyPress(){
        Gdx.app.log("Computer", "keypress");
    }

}


