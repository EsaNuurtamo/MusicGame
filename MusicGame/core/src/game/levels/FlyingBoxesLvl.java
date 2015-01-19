package game.levels;

import game.MyConst;
import game.objects.Ball;
import game.objects.ColorBar;
import game.objects.AbstractObject;
import game.states.PlayScreen;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class FlyingBoxesLvl extends AbstractLevel{
	int[] lista;
	Ball ball;
	public FlyingBoxesLvl(PlayScreen state) {
		super(state, 0);
		name="FlyingBoxes";
		
		
		
	}
	
	@Override
	public void init() {
		super.init();
		initColors(Color.RED, Color.ORANGE, Color.OLIVE);
		spawnBall();
	}

	@Override
	public void spawnLogic(float delta) {
		if(spawnTimer<=0){
			float ratio=lvlTime/lvlTimeLimit;
			int number=2;
			
			//different phases of the game
			if(ratio<0.2){
				spawnTimer=3f;
				
			}else if(ratio<0.5){
				spawnTimer=2f;
				
			}else if(ratio<0.75){
				spawnTimer=1.5f;
				
			}else if(ratio<1){
				spawnTimer=0.75f;
				
			}
			
			
			
			
			
			spawnBoxes(number);
			
			
		}
		
		
		
		
		
		
		
	}
	
	@Override
	public void handleActions() {
		
		boolean spawn=false;
		Intersector i=new Intersector();
		
		for(AbstractObject obj:objects){
			
			if(obj instanceof Ball)continue;
			ColorBar cb=(ColorBar)obj;
			if(i.overlaps(ball.getCircle(), cb.getBox())&&cb.getColor().equals(ball.getColor())){
				obj.setDestroyed(true);
				ball.setDestroyed(true);
				score++;
				spawn=true;
				
			}
		}
		if(ball.isDestroyed()){
			spawnBall();
		}
	}
	@Override
	public void flingObject(AbstractObject obj, float x, float y) {
		
		if(obj instanceof Ball){
			Vector2 v=new Vector2(x,y);
			obj.setDirection(v);
			obj.setSpeed(v.len()*0.25f);
			
		}
	}
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
        
        
    }
	
	public void spawnBoxes(int number){
		for(int i=0;i<number;i++){
			int index=new Random().nextInt(objectColors.size());
			Color color=objectColors.get(index);
			AbstractObject obj=new ColorBar(state,color, null, 100, 100);
			Vector2 position;
			Vector2 direction;
			
			
			//randomized starting direction
			double rand=Math.random();
			
			if(0.75f<rand){
	    		position=new Vector2((float)Math.random()*(MyConst.APP_WIDTH-obj.getWidth()), 0);
	    	
			}else if(0.5f<rand){
	    		position=new Vector2((float)Math.random()*(MyConst.APP_WIDTH-obj.getWidth()), MyConst.APP_HEIGHT-obj.getHeight());
	    		
	    	}else if(0.25f<rand){
	    		position=new Vector2(MyConst.APP_WIDTH-obj.getWidth(), (float)Math.random()*(MyConst.APP_HEIGHT-obj.getHeight()));
	    		
	    	}else{
	    		position=new Vector2(0, (float)Math.random()*(MyConst.APP_HEIGHT-obj.getHeight()));
	    		
	    	}
			
			direction=new Vector2(MyConst.getCenter().sub(position));
			obj.setPosition(position);
			obj.setDirection(direction);
			//obj.setSpeed(25+(float)(Math.random()*50));
			obj.setSpeed(0);
			objects.add(obj);
		}
	}
	
	
	
	
	
	
	public void spawnBall(){
		int index=new Random().nextInt(objectColors.size());
		Color color=objectColors.get(index);
		Ball b=new Ball(state, color, new Vector2(MyConst.APP_WIDTH/2, MyConst.APP_HEIGHT/2));
		objects.add(b);
		ball=b;
	}

}
