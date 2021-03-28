package brocode.warlock.Screens;

import brocode.warlock.Screens.MainMenu;
import brocode.warlock.WarLock;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Description implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;

    public Description(Game game){
        this.game = game;
        viewport = new FitViewport(WarLock.V_WIDTH+200, WarLock.V_HEIGHT+200, new OrthographicCamera());
        stage = new Stage(viewport, ((WarLock) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Table tableTwo = new Table();
        tableTwo.center();
        tableTwo.setFillParent(true);

        Table tableThree = new Table();
        tableThree.center();
        tableThree.setFillParent(true);

        Table tableFour = new Table();
        tableFour.center();
        tableFour.setFillParent(true);

        Table tableFive = new Table();
        tableFive.center();
        tableFive.setFillParent(true);

        Table Controls = new Table();
        Controls.center();
        Controls.setFillParent(true);

        Table ControlsTwo = new Table();
        ControlsTwo.center();
        ControlsTwo.setFillParent(true);

        Table ControlsThree = new Table();
        ControlsThree.center();
        ControlsThree.setFillParent(true);

        Table leave = new Table();
        leave.bottom();
        leave.setFillParent(true);

        Label DescriptionOne = new Label("In War-Lock you play as Wizzy. Wizzy is a wizard that combats Warlock hackers.", font);
        Label DescriptionTwo = new Label("Wizzy must find the 3 terminals that give clues to the identity of the Warlock Virus.", font);
        Label DescriptionThree = new Label("Wizzy can use the papers lying around the office to learn more about the Virus types.", font);
        Label DescriptionFour = new Label("Once Wizzy is confident, head over to the main terminal and diagnose the Virus.", font);
        Label DescriptionFive = new Label("If the diagnose's is wrong it's game over!", font);

        Label DescriptionControls = new Label("CONTROLS:", font);
        Label DescriptionControlsTwo = new Label("WASD or the arrow keys to move.", font);
        Label DescriptionControlsThree = new Label("Press 'E' to interact with the terminal and the office papers.", font);

        Label DescriptionLeave = new Label("PRESS ESC TO RETURN TO MENU", font);




        table.add(DescriptionOne).pad(0,0,350,0);
        tableTwo.add(DescriptionTwo).pad(0,0,310,0);
        tableThree.add(DescriptionThree).pad(0,0,270,0);
        tableFour.add(DescriptionFour).pad(0,0,230,0);
        tableFive.add(DescriptionFive).pad(0,0,190,0);

        Controls.add(DescriptionControls).pad(0,0,80,0);
        ControlsTwo.add(DescriptionControlsTwo).pad(0,0,0,0);
        ControlsThree.add(DescriptionControlsThree).pad(60,0,0,0);

        leave.add(DescriptionLeave).pad(0,0,30,0);





        stage.addActor(table);
        stage.addActor(tableTwo);
        stage.addActor(tableThree);
        stage.addActor(tableFour);
        stage.addActor(tableFive);

        stage.addActor(Controls);
        stage.addActor(ControlsTwo);
        stage.addActor(ControlsThree);

        stage.addActor(leave);

    }

    public void update(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu((WarLock) game));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
        stage.dispose();
    }
}
