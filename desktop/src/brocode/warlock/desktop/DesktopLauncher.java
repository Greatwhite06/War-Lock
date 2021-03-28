package brocode.warlock.desktop;

import brocode.warlock.Node;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import brocode.warlock.WarLock;
import brocode.warlock.VirusTypes;
import brocode.warlock.CircularTree;
import brocode.warlock.Screens.LoggingScreen;
import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Graphics.DisplayMode desktopMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "War-Lock";
		config.setFromDisplayMode(desktopMode);
		//config.vSyncEnabled = true;
		new LwjglApplication(new WarLock(), config);

		LoggingScreen test = new LoggingScreen();
		/*
		//This is just to test the output of the methods in VirusTypes. Can be deleted.
		System.out.println(VirusTypes.wormsR());
		System.out.println(VirusTypes.ransomWareR());
		System.out.println(VirusTypes.trojanR());
		System.out.println("\n");
		//THIS IS A TEST OUTPUT FOR THE TREE. CAN BE DELETED.
		CircularTree tree = new CircularTree();
		CircularTree.root = new Node(10);
		CircularTree.root.left = new Node(12);
		CircularTree.root.right = new Node(15);
		CircularTree.root.left.left = new Node(25);
		CircularTree.root.left.right = new Node(30);
		CircularTree.root.right.left = new Node(36);

		// head refers to the head of the Link List
		Node head = tree.bTreeToCList(tree.root);

		// Display the Circular LinkedList
		tree.display(head);

		 */


	}
}
