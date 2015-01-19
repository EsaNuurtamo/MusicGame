package game.states;


import game.MyConst;
import game.input.TouchControls;
import game.levels.ColorBarsLvl;
import game.levels.AbstractLevel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * PlayScreen has all the data for updating and drawing the game (camera, tilemap, batches... ect.)
 * All
 * @author esa
 *
 */
public class PlayScreen implements Screen{
	//Objects
	
	
	private SpriteBatch batch;
	private BitmapFont font;
	private ShapeRenderer renderer;
	private TouchControls touches;
    private OrthographicCamera camera;
    private Viewport viewport;
    //Gamehandler
    private Game game;
    private AbstractLevel level;
    private boolean exiting=false;
     
    public PlayScreen(Game g) {
        this.game=g;  
        this.level=new ColorBarsLvl(this, 0);
    }
    
    @Override
    public void show() {
    	
        camera=new OrthographicCamera(MyConst.APP_WIDTH,MyConst.APP_HEIGHT);
        camera.position.set(MyConst.APP_WIDTH/2, MyConst.APP_HEIGHT/2, camera.position.z);
        camera.update();
        viewport=new FitViewport(MyConst.APP_WIDTH, MyConst.APP_HEIGHT, camera);
        
        renderer=new ShapeRenderer();
        renderer.setProjectionMatrix(camera.combined);
        batch=new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        font=new BitmapFont(Gdx.files.internal("mainFont.fnt"));
        font.setColor(Color.WHITE);
        font.scale(0.005f);
        
        
        touches = new TouchControls(this);
        
        //inputMultiplexer.addProcessor();
        Gdx.input.setInputProcessor(new GestureDetector(touches));
        
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(exiting){
        	ScoreScreen s=new ScoreScreen(game, level);
        	s.setPoints(level.getScore());
        	game.setScreen(s);
        	dispose();
        	return;
        }
        
        if(level.isGameOver()){
        	batch.begin();
	        	font.draw(batch, "GameOver!", MyConst.APP_WIDTH/4, MyConst.APP_HEIGHT/2+100);
	        	font.draw(batch, "Your Score: "+level.getScore(), MyConst.APP_WIDTH/6, MyConst.APP_HEIGHT/2+30);
        	batch.end();
        	
        	Timer.schedule(new Task(){
        	    @Override
        	    public void run() {
        	    	exiting=true;
                	
        	    }
        	}, 2f);
        	
        	return;
        }
        
        //siirr‰ t‰m‰ jonnekin j‰rkev‰‰n paikkaan
        if(!Gdx.input.isTouched()){
        	touches.setFlingReady(false);
        }
        
        level.update(delta);
        
        
        
        
        renderer.begin(ShapeType.Filled);
	        level.draw(renderer);
        renderer.end();
        
        //draw hud
        //HUOM luo oma class hudille
        batch.begin();
        	font.draw(batch, "Health: "+level.getHealth()+
        			         " Score: "+level.getScore(), 
        			         5, MyConst.APP_HEIGHT-10
            );
        	font.draw(batch, 
			         "Time: "+(int)level.getLvlTime(), 
			         5, MyConst.APP_HEIGHT-40
   );
        batch.end();	
        
        
        
    }

    @Override
    public void resize(int w, int h) {
    	viewport.update(w, h);
           
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

    

    public OrthographicCamera getCamera() {
        return camera;
    }
    
    @Override
	public void dispose() {
    	
		batch.dispose();
		renderer.dispose();
		font.dispose();
		
	}

   public Viewport getViewport() {
	return viewport;
    }
   public void setLevel(AbstractLevel level) {
	this.level = level;
}
   public AbstractLevel getLevel() {
	return level;
}
        
}
