package game.objects;

import game.MyConst;
import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class SpiralBall extends Ball{
	boolean clockwise=true;
	public SpiralBall(PlayScreen state, Color color, float speed) {
		super(state, color, speed);
	}
	
	@Override
	public void update(float delta){
    	
    	
    	if(isOutOfBounds()){
    		destroyed=true;
    	}
    	
    	
    	Vector2 v=new Vector2(MyConst.getCenter().sub(position)).limit(delta*100);
    	position.add(v.cpy().scl(0.1f));
    	if(clockwise){
    		direction=v.cpy().rotate90(1);
    	}else{
    		direction=v.cpy().rotate90(-1);
    	}
    	
    	moveLogic(delta);
    	circle.setPosition(position);
    	//position.add(v);
    	
    	
    }
	
	@Override
	public boolean isOutOfBounds() {
		if(position.dst(MyConst.getCenter())<this.getWidth()/2){
			return true;
		}
		return false;
	}
	public void setClockwise(boolean clockwise) {
		this.clockwise = clockwise;
	}
}
