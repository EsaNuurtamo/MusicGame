package game.levels;

import game.MyConst;
import game.input.Mouse;
import game.levels.layouts.BarLayout;
import game.objects.Ball;
import game.objects.Bomb;
import game.objects.ColorBar;
import game.objects.AbstractObject;
import game.states.PlayScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
/**
 * Has all the basic principles of any level. It handles the effect of touches and it updates the logic of the objects.
 * All specific level classes can have different layouts and spawn logic.
 * @author esa
 */
public abstract class AbstractLevel {
	protected float lvlTime;
	protected float lvlTimeLimit;
	protected boolean gameOver;
	protected int health;
	protected int score;
	protected PlayScreen state;
	protected float spawnTimer;
	protected BarLayout layout;
	protected List<AbstractObject> objects;
	protected List<Color> objectColors;
	protected boolean changing;
	protected int numSpawned;
	protected String name;
	
	public AbstractLevel(PlayScreen state) {
		this.state=state;
		init();
	}
	public void initColors(Color... colors){
		for(Color c:colors){
			objectColors.add(c);
		}
	}
	
	public void init(){
		this.objects=new ArrayList<AbstractObject>();
		this.objectColors=new ArrayList<Color>();
		lvlTime=0;
		lvlTimeLimit=60;
		gameOver=false;
		health=10;
		score=0;
		spawnTimer=0;
		changing=false;
		numSpawned=0;
	}
	
	
	
	public void update(float delta){
		//ending conditions
		if(lvlTime>=lvlTimeLimit){
			gameOver=true;
			return;
		}
		
		//advance time
		lvlTime+=delta;
		spawnTimer-=delta;
		
		//spawn objects
		spawnLogic(delta);
		handleActions();
		
		//updateobjects
		updateObjects(delta);
	}
	
	public void handleActions(){
		
		//handle controls
		if(Gdx.input.justTouched()){
			AbstractObject obj=getOverlapCircle(objects, state.getViewport());
			ColorBar box=getOverlapBox(layout.getBoxes(), state.getViewport());
			
			if(obj!=null&&box!=null&&obj.getColor().equals(box.getColor())){
				obj.setDestroyed(true);
				score++;
			}
		}
		
	}
	
	

	public abstract void spawnLogic(float delta);
	
	
	
	public void updateObjects(float delta){
        Iterator i=objects.iterator();
        while(i.hasNext()){
            AbstractObject obj=(AbstractObject)i.next();
            if(obj.isDestroyed()){
                
                i.remove();
                continue;
            }
            obj.update(delta);
            
        }
        if(layout==null)return;
        for(ColorBar box:layout.getBoxes()){
        	box.update(delta);
        }
        
    }
	
	public void draw(ShapeRenderer renderer){
		if(layout!=null)layout.draw(renderer);
        for(AbstractObject obj:objects){
        	obj.draw(renderer);
        }
	}
	
	public AbstractObject getOverlapCircle(List<AbstractObject> objects, Viewport port){
		
		AbstractObject object=null;
		for(AbstractObject obj:objects){
			if(obj.getCircle().contains(Mouse.getWorldPos(port))){
				object=obj;
			}
		}
		return object;
	}
	
	public ColorBar getOverlapBox(List<ColorBar> boxes, Viewport port){
		
		ColorBar box=null;
		for(ColorBar obj:boxes){
			if(obj.getBox().contains(Mouse.getWorldPos(port))){
				box=obj;
			}
		}
		return box;
	}
	
	public void decreaseHealth(){
		health--;
	}
	
    public void flingObject(AbstractObject obj, float x, float y){
    	
    }
	
	
	public float getLvlTime() {
		return lvlTime;
	}

	public void setLvlTime(float lvlTime) {
		this.lvlTime = lvlTime;
	}

	public float getLvlTimeLimit() {
		return lvlTimeLimit;
	}

	public void setLvlTimeLimit(float lvlTimeLimit) {
		this.lvlTimeLimit = lvlTimeLimit;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PlayScreen getState() {
		return state;
	}

	public void setState(PlayScreen state) {
		this.state = state;
	}
	
	public BarLayout getLayout() {
		return layout;
	}
	
	
	
	
	public int getNumSpawned() {
		return numSpawned;
	}
	
	public void setNumSpawned(int numSpawned) {
		this.numSpawned = numSpawned;
	}
	public List<AbstractObject> getObjects() {
		return objects;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
