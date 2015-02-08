package game.levels;

import game.Content;
import game.MyConst;
import game.levels.layouts.BarLayout;
import game.objects.Ball;
import game.objects.Bomb;
import game.objects.AbstractObject;
import game.states.PlayScreen;

import java.util.Random;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
/**
 * Level that has color areas and balls spawn from top and bottom and only 
 * @author esa
 *
 */
public class ColorBarsLvl extends AbstractLevel{
	int[] bombTimes;
	
	public ColorBarsLvl(PlayScreen state, int difficulty) {
		
		super(state, difficulty);
		
		name="ColorBars";
		music=Content.getMusic("Theme");
		
		
	}
	@Override
	public void initColors(Color... colors){
		layout.createLayout(colors);
		for(Color c:colors){
			objectColors.add(c);
		}
	}
	
	@Override
	public void init() {
		super.init();
		this.layout=new BarLayout(state);
		initBombTimes();
		System.out.println(difficulty);
		if(difficulty==0){
			initColors(new Color(0.5f,0,0,1f), new Color(1f, 0,0,1f));
		}else if(difficulty==1){
			initColors(Color.RED, Color.ORANGE, Color.OLIVE);
		}else{
			initColors(Color.RED, Color.ORANGE, Color.OLIVE, Color.CYAN);
		}
		
		
	}

	@Override
	public void spawnLogic(float delta) {
		/*if(spawnTimer<=0){
			float ratio=lvlTime/lvlTimeLimit;
			int number=1;
			
			//different phases of the game
			if(ratio<0.2){
				spawnTimer=2f;
				
			}else if(ratio<0.5){
				spawnTimer=1f;
				
			}else if(ratio<0.75){
				spawnTimer=0.75f;
				
			}else if(ratio<1){
				spawnTimer=1.5f;
				number=2;
			}
			
			
			
			
			
			spawnEnemies(number);
			
			
		}*/
		
		int number = (int)((0.1*lvlTime*0.2f*lvlTime)+lvlTime*0.2)+1;
		
		if(number<0)number=0;
		int toSpawn=number-numSpawned;
		spawnCircles(toSpawn);
		numSpawned+=toSpawn;
				
		
		for(int i=0; i<8;i++){
			if(((int)lvlTime)==bombTimes[i]){
				spawnBombs(1);
				bombTimes[i]=0;
			}
		}
		
		
		
		
	}
	
	
	public void spawnCircles(int number){
		for(int i=0;i<number;i++){
			int index=new Random().nextInt(objectColors.size());
			Color color=objectColors.get(index);
			if(Math.random()<0.1){
				color=Color.BLUE;
			}
			AbstractObject obj=new Ball(state,color,50+(float)(Math.random()*200));
			Vector2 position;
			Vector2 direction;
			float radius=obj.getRadius();
			//if(0.5f>Math.random()){
	    		//position=new Vector2(radius+(float)Math.random()*(MyConst.APP_WIDTH-2*obj.getRadius()), -2*radius);
	    		//direction=new Vector2(0f,50f);
	    		
	    	//}else{
	    		position=new Vector2(obj.getRadius()+(float)Math.random()*(MyConst.APP_WIDTH-2*radius), MyConst.APP_HEIGHT+2*radius);
	    		direction=new Vector2(0f,-50f);
	    		
	    	//}
			obj.setPosition(position);
			obj.setDirection(direction);
			objects.add(obj);
		}
	}
	
	public void spawnBombs(int number){
		Vector2 v=new Vector2(25+(int)(Math.random()*(MyConst.APP_WIDTH-50)),25+(int)(Math.random()*(MyConst.APP_HEIGHT-50)));
		for(int i=0; i<number;i++){
			objects.add(new Bomb(state,v, 50));
		}
		
	}
	
	public void initBombTimes(){
		bombTimes= new int[8];
		Random r=new Random();
		for(int i=0; i<8;i++){
			bombTimes[i]=r.nextInt((int)lvlTimeLimit)+1;
		}
	}
	@Override
	public void flingObject(AbstractObject obj, float x, float y) {
		
		if(obj instanceof Bomb)obj.setVelocity(new Vector2(x,y));
	}

}
