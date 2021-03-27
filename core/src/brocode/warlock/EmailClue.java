package brocode.warlock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;

public class EmailClue {
    private List<String> Body = new ArrayList<>();
    private List<String> Reply = new ArrayList<>();

    public void readfile(String emailPath){

        emailPath = "core/assets/Emails/Phising";

        if(Gdx.files.internal(emailPath).exists()){
            int scenarioNum = MathUtils.random(1, 3);
            int[] choice = new int[4];
            FileHandle email = Gdx.files.internal(emailPath);
            Scanner input = new Scanner(email.read());


            while(true){
                for(int i = 0; i < 3; i++){
                    choice[i] = input.nextInt();
                }
                if(choice[0] != scenarioNum){
                    for(int i = 0; i < choice[1];i++){
                        this.Body.add(input.next());
                    }
                    for(int i = 0; i < choice[2];i++){
                        this.Reply.add(input.next());
                    }
                    break;
                }
                else{
                    for(int i = 0 ;i < choice[3];i++){
                        input.next();
                    }
                }
            }
        }
    }

    public List<String> getBody() {
        return Body;
    }

    public List<String> getReply() {
        return Reply;
    }
}
