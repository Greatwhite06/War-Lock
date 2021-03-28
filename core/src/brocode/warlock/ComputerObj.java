package brocode.warlock;

import java.util.List;
import java.lang.String;

//This will be used to implement in the email screen file. These can be used on the screen.
public class ComputerObj {
    List<String> getBody = null;
    List<String> getReply = null;

    int priorityValue = 0;
    Boolean VirusOnPC = false;

    public ComputerObj left;
    public ComputerObj middle;
    public ComputerObj right;
    int val;

    public ComputerObj(int val)
    {
        this.val = val;
        left = right = null;
    }


}
