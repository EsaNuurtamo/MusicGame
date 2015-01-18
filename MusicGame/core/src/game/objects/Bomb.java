package game.objects;

import game.MyConst;
import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
/**
 * Bomb is an object that spawns randomly and explodes when the timer goes off.
 * On explosion it makes the area it's on turn black and unplayable. It also flashes between two colors as warning.
 * @author esa
 * 
 * */
public class Bomb extends AbstractObject{
	protected float bombTimer=3.8f;
	protected float flashInterval=0.25f;
	public Bomb(PlayScreen state, Vector2 position, float radius) {
		super(state, position, radius);
		color=Color.BLACK;
	}

	@Override
	public void update(float delta) {
		isOutOfBounds();
		flashInterval-=delta;
		bombTimer-=delta;
		if(flashInterval<=0){
			flashInterval=0.25f;
			flash();
		}
    	if(bombTimer<=0){
    		explode();
    	}
    	if(color.equals(Color.BLACK)){
    		bombTimer-=delta;
    	}
    	
    	if(velocity!=null){
    		position=position.add(velocity.cpy().limit(delta*velocity.len()*0.25f));
    		circle.setPosition(position);
    	}
    	
	}
	public void explode(){
		for(ColorBar box:state.getLevel().getLayout().getBoxes()){
			if(box.getBox().contains(position)){
				
				box.setColor(Color.BLACK);
				box.setBroken(true);
			}
		}
		destroyed=true;
	}
	
	public void flash(){
		if(color.equals(Color.BLACK)){
			color=Color.WHITE;
		}else{
			color=Color.BLACK;
		}
	}

}
