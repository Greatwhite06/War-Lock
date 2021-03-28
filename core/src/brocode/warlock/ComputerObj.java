package brocode.warlock;

import java.util.List;
import java.lang.String;

//This will be used to implement in the email screen file. These can be used on the screen.
public interface ComputerObj {
    List<String> getBody = null;
    List<String> getReply = null;
    //This is for a deeper mechanic if we end up using a spam folder for the window interface.
    Object spam = new Object();
    int priorityValue = 0;
    Boolean VirusOnPC = false;
}
