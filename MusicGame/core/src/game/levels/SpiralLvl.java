package game.levels;

import game.MyConst;
import game.levels.layouts.SpiralLayout;
import game.objects.AbstractObject;
import game.objects.SpiralBall;
import game.states.PlayScreen;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class SpiralLvl extends AbstractLevel{
	private SpiralLayout out;
	public SpiralLvl(PlayScreen state) {
		super(state,0);
		name="Spiral";
		
	}
	
	@Override
	public void init() {
		super.init();
		out=new SpiralLayout(state);
		initColors(Color.RED, Color.ORANGE, Color.OLIVE, Color.GREEN);
		
	}
	
	@Override
	public void initColors(Color... colors) {
		out.createLayout(colors);
		super.initColors(colors);
	}

	@Override
	public void spawnLogic(float delta) {
		int number = (int)((0.1*lvlTime*0.2f*lvlTime)+lvlTime*0.2)+1;
		if(number<0)number=0;
		int toSpawn=number-numSpawned;
		spawnBalls(toSpawn);
		numSpawned+=toSpawn;
		
	}
	public void draw(ShapeRenderer renderer){
		if(out!=null)out.draw(renderer);
        for(AbstractObject obj:objects){
        	obj.draw(renderer);
        }
	}
	
	/*@Override
	public void updateObjects(float delta) {
		
		super.updateObjects(delta);
		for(GameObject obj:objects){
			if(obj instanceof Ball){
				Vector2 v=new Vector2(MyConst.getCenter().sub(obj.getPosition()));
				obj.setDirection(v.cpy().rotate90(0));
				obj.setPosition(obj.getPosition().add(v.limit(delta*20)));
			}
		}
	}*/
	
	public void spawnBalls(int number){
		for(int i=0;i<number;i++){
			int index=new Random().nextInt(objectColors.size());
			Color color=objectColors.get(index);
			AbstractObject obj=new SpiralBall(state,color, 50+(float)(Math.random()*200));
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
			
			direction=new Vector2(MyConst.getCenter().sub(position).rotate90(0));
			obj.setPosition(position);
			obj.setDirection(direction);
			
			float r=(float)Math.random();
			if(r<0.5){
				((SpiralBall)obj).setClockwise(false);
			}
			
			objects.add(obj);
		}
	}

}
