package game.states;

import game.MyConst;
import game.levels.ColorBarsLvl;
import game.levels.FlyingBoxesLvl;
import game.levels.SpiralLvl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * MenuScreen is the main menu screen that shows one menu page.
 * Has access to all the menu pages through MenuNode menu.
 * @author esa
 *
 */
public class MenuScreen implements Screen {
	
	public static Skin skin;
	private Stage stage;
	private SpriteBatch batch;
    private Table table;
	private Game game;
    private TextField save;
    private MenuNode menu;
        
	
    public MenuScreen(Game g){
        this.game=g;
        skin=MyConst.skin;
	}

	
	

	public void render (float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            if(menu.getParent()!=null){
                menu=menu.getParent();
                createMenuTable();
            }
        }
        

        stage.act();
        stage.draw();
                
		
	}
	
	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void dispose () {
		stage.dispose();
              
		
	}

	@Override
	public void show() {
            batch = new SpriteBatch();
            table=new Table();
            stage = new Stage(new FitViewport(MyConst.APP_WIDTH, MyConst.APP_HEIGHT));
            
            menu=
            new MenuNode("MusicGame",new MenuNode("New Game", new MenuNode("Color Bars", new MenuNode("Easy"),
            		                                                                     new MenuNode("Medium"),
            		                                                                     new MenuNode("Hard")),
            													
            												  new MenuNode("Flying Boxes"),
            												  new MenuNode("Spiral level"),
            												  new MenuNode("Main Menu")),
                                     new MenuNode("Exit"));
            createMenuTable();                            

            
            
            Gdx.input.setInputProcessor(stage);    

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
        
     
        
    public void createMenuTable(){
        stage.clear();
        table.clear();
        table.align(Align.center);
        
        table.add(new Label(menu.getName(),skin, "title")).height(100).row();
        
        
        
        for(MenuNode node:menu.getChildren()){
                
        	createButton(node.getName());
         }
        
        
        
        table.setFillParent(true);
        stage.addActor(table);
     }
        
        
        public void createButton(String name){
            
            TextButton button = new TextButton(name, skin);
            
            createListener(button);
            table.add(button).size(350, 100).row();
            
        }
        
        //defining actions for different buttons
        public void createListener(TextButton button){
            
            button.addListener(new ClickListener(){
                
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    TextButton t=(TextButton)event.getListenerActor();
                    String s=t.getLabel().getText().toString();
                    
                         
                    /*if(s.equals("New Game")){
                        game.setScreen(new PlayState(game));
                        dispose();
                    }else *//*if(s.equals("Leaderboard")){
                        game.setScreen(new ScoresScreen(game, new ColorBarsLvl(play)));
                        dispose();
                        
                    }else */
                    PlayScreen play=new PlayScreen(game);
                    if(s.equals("Exit")){
                    	
                    	Gdx.app.exit();
                    	
                    }else if(menu.getName().equals("Color Bars")){
                    	
                    	int d=0;
                    	if(s.equals("Easy")){
                    		d=0;
                    	}else if(s.equals("Medium")){
                    		d=1;
                    	}else{
                    		d=2;
                    	}
                    	ColorBarsLvl lvl=new ColorBarsLvl(play, d);
                    	lvl.getMusic().play();
                    	play.setLevel(lvl);
                    	game.setScreen(play);
                        dispose();
                    }else if(s.equals("Flying Boxes")){
                    	play.setLevel(new FlyingBoxesLvl(play));
                    	game.setScreen(play);
                        dispose();
                    }else if(s.equals("Spiral level")){
                    	play.setLevel(new SpiralLvl(play));
                    	game.setScreen(play);
                        dispose();
                    }else if(s.equals("Main Menu")){
                    	game.setScreen(new MenuScreen(game));
                        dispose();
                    }else{
                    	if(menu.getChild(s)==null){
                            return;
                        }
                        menu=menu.getChild(s);
                        createMenuTable();
                    }
                    
                    
                    
                }
            });
        }
}
