package brocode.warlock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class EmailClue {

    private List<String> Body = new ArrayList<>();
    private List<String> Reply = new ArrayList<>();

    public void readfile(String emailPath) {

        emailPath = "core/assets/Emails/Phising";

        if(Gdx.files.internal(emailPath).exists()){
            int scenarioNum = MathUtils.random(1, 3);//choose variant
            int[] choice = new int[4]; // format lines [variant] [body lines] [reply lines] [next variant]
            FileHandle email = Gdx.files.internal(emailPath); //create handle for the file
            Scanner input = new Scanner(email.read()); //read line by line

            while(true){
                for(int i = 0; i < 3; i++){ //read format lines
                    choice[i] = input.nextInt(); //fill array with the format numbers
                }
                if(choice[0] != scenarioNum){ //if variant is correct go ahead
                    for(int i = 0; i < choice[1];i++){ //read for lines associated with body
                        this.Body.add(input.next()); //add lines to a string list for body
                    }
                    for(int i = 0; i < choice[2];i++){ //read for lines associateed with reply
                        this.Reply.add(input.next()); // add liens to a string list for reply
                    }
                    break; //end while loop
                }
                else{ // jump to the next variant
                    for(int i = 0 ;i < choice[3];i++){ //iterates until next variant format numbers start
                        input.next(); //reads and does not save skipped lines
                    }
                }
            }
        }
    }

    //Grab the body
    public List<String> getBody() {
        return Body;
    }
    //grab the reply
    public List<String> getReply() {
        return Reply;
    }


}
