package brocode.warlock.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import brocode.warlock.WarLock;
import brocode.warlock.VirusTypes;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new WarLock(), config);

		//This is just to test the output of the methods in VirusTypes. Can be deleted.
		System.out.println(VirusTypes.wormsR());
		System.out.println(VirusTypes.ransomWareR());
		System.out.println(VirusTypes.trojanR());


	}
}
