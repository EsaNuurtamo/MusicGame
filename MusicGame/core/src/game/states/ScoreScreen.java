package game.states;

import game.MyConst;
import game.levels.ColorBarsLvl;
import game.levels.AbstractLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ScoreScreen implements Screen {

	Game game;
	private int lastScore=-1;
	private Stage stage;

	private Table table;
	
	private AbstractLevel level;
	private TextField txtField;
	private String levelType;
	private Preferences pref;

	public ScoreScreen(Game game, AbstractLevel level) {
		this.game = game;
		this.level=level;
		pref=Gdx.app.getPreferences(level.getName());
	}
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
                
        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);

	}

	@Override
	public void show() {
		
		table = new Table();
		stage = new Stage(new FitViewport(MyConst.APP_WIDTH, MyConst.APP_HEIGHT));
		
		if(lastScore==-1||!isHighScore()){
			createLeaderboardTable();
		}else{
			createSubmitTable();
		}
        

		Gdx.input.setInputProcessor(stage);

	}
	
	public void createLeaderboardTable() {
		stage.clear();
		table.clear();
		
		table.add(new Label("LEADERBOARD",MyConst.skin,"title")).colspan(2).row();
		table.add(new Label("-------------------------------",MyConst.skin)).colspan(2).row();
		
		//creating cells for scores
		List<Score> scores=getScores();
		if(scores!=null){
			for(int i=scores.size()-1;i>=0;i--){
				table.row().height(30);
				table.add(new Label(scores.size()-i+". Name: "+scores.get(i).getOwner(),MyConst.skin,"score")).left();
				table.add(new Label("Score: "+scores.get(i).getPoints(),MyConst.skin, "score"));
				table.row().height(30);
				
			}
		}
		table.add(new Label("-------------------------------",MyConst.skin)).colspan(2).row();
		
		
		//navigation buttons
		createButton("Retry");
		createButton("Main Menu");
		createButton("Exit");
		table.setFillParent(true);
		
		stage.addActor(table);
	}

	public void createSubmitTable() {
		stage.clear();
		table.clear();
		
		table.align(Align.center);
		table.add(new Label("New highscore!",MyConst.skin)).colspan(2).row();
		txtField=new TextField("", MyConst.skin);
		txtField.setMaxLength(10);
		table.add(txtField).width(300).colspan(2).row();
		createButton("Submit");
		table.setFillParent(true);
		
		stage.addActor(table);
	}

	public void createButton(String name) {

		TextButton button = new TextButton(name, MyConst.skin);

		createListener(button);
		table.add(button).size(300, 60).colspan(2).row();

	}

	public void createListener(TextButton button) {

		button.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TextButton t = (TextButton) event.getListenerActor();
				String s = t.getLabel().getText().toString();

				if (s.equals("Retry")) {
					PlayScreen state=new PlayScreen(game);
					level.init();
					state.setLevel(level);
					game.setScreen(state);
					dispose();
				} else if (s.equals("Main Menu")) {
					game.setScreen(new MenuScreen(game));
					dispose();
				} else if (s.equals("Exit")) {
					Gdx.app.exit();
				} else if(s.equals("Submit")){
					if(txtField.getText().isEmpty()){
						return;
					}
					addScore(new Score(txtField.getText(),lastScore));
					lastScore=-1;
					
					createLeaderboardTable();
				}
						

			}
		});
	}
	
	public boolean isHighScore(){
		List<Score> list=getScores();
		if(list==null||lastScore>list.get(0).getPoints()||list.size()<10){
			return true;
		}else{
			return false;
		}
	}
	
	
	public List<Score> getScores(){
		
		if(pref.get().isEmpty())return null;
		
    	List<Score> sco=new ArrayList<Score>();
    	Map<String, Integer> s=(Map<String,Integer>)pref.get();
    	
		for(String name:s.keySet()){
    		sco.add(new Score(name,pref.getInteger(name)));
    	}
		
		Collections.sort(sco);
		
		if(sco.size()>10){
			sco=sco.subList(sco.size()-10, sco.size());
			pref.clear();
			pref.flush();
	    	for(Score scr:sco){
	    		pref.putInteger(scr.getOwner(), scr.getPoints());
	    	}
	    	pref.flush();
		}
		
		
    	
    	
    	return sco;
    	
    	
    	
    	
    	
    }
	
	public void addScore(Score scr){
		
		
    	if(pref.contains(scr.getOwner())){
    		//replace the old value only if new one is bigger
    		if(pref.getInteger(scr.getOwner())<scr.getPoints()){
    			pref.putInteger(scr.getOwner(), scr.getPoints());
    		}
    	}else{
    		pref.putInteger(scr.getOwner(), scr.getPoints());
    	}
		
        
    	pref.flush();
    	
	}
	
	

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		

	}
	public void setPoints(int points) {
		this.lastScore = points;
	}
	

}
