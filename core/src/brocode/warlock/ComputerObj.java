package brocode.warlock;

import com.badlogic.gdx.math.MathUtils;

import java.util.List;
import java.lang.String;

//This will be used to implement in the email screen file. These can be used on the screen.
public class ComputerObj {
    List<String> getBody = null;

    public Boolean VirusOnPC = false;
    private String Notepad = "Emails/Windows-98-desktop-notepad-640-480.png";
    private String Email = "Emails/Windows-98-desktop-email-640-480.png";

    public ComputerObj left;
    public ComputerObj right;
    public int val;
    private enum Symptom {dataBlock,Captive,Modified,Steal1,Steal2,Steal3,deviceBlock,fileCorrupt};
    private enum Method {DirectAccess,RemoteAccess,Phising}
    public Symptom symptom;
    public Method method;


    public ComputerObj(int val)
    {
        this.val = val;
        left = right = null;
    }

    public void Infect(){
        this.VirusOnPC = true;

    }

}
