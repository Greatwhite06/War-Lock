package brocode.warlock.Sprites;

import brocode.warlock.Screens.LoggingScreen;
import brocode.warlock.Screens.OfficeScreen;
import brocode.warlock.VirusTypes;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import javax.swing.*;

public class Computer extends InteractiveTileObject{
    public Computer(OfficeScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        setCategoryFilter(WarLock.COMPUTER_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Computer", "Collision");
        //setCategoryFilter(WarLock.ACCESSED_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.E)){
            System.out.println("Computer Accessed");
        }
    }
}



