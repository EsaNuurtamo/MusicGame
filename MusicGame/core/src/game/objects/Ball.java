package game.objects;

import game.Content;
import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
/**
 * Ball is the main object in the game and it yields score when tapped
 * @author esa
 */
public class Ball extends AbstractObject{
	protected float bombTimer=3f;
	protected boolean goingUp;
	protected float imgW=130;
	protected Sprite sprite;
	public Ball(PlayScreen state, Color color, float speed) {
		super(state);
		this.color=color;
        this.speed=speed;
        this.destroyed=false;
        this.sprite=new Sprite(Content.textures.get("Barrel"));
        sprite.setColor(color);
        sprite.setSize(imgW, imgW);
	}
	
	public Ball(PlayScreen state, Color color, Vector2 position){
		super(state, position);
		this.color=color;
		
	}
	
	public void draw(SpriteBatch batch){
    	/*renderer.setColor(Color.BLACK);
		renderer.circle(circle.x, circle.y, circle.radius);
    	renderer.setColor(color);
    	//renderer.cone(circle.x, circle.y, 0, 10, 10);
		renderer.circle(circle.x, circle.y, circle.radius-10);*/
		sprite.draw(batch);
    }
	
	
    
    public void update(float delta){
    	if(isOutOfBounds()){
    		//state.getLevel().decreaseHealth();
    		destroyed=true;
    	}
    	moveLogic(delta);
    	circle.setPosition(position);
    	sprite.setPosition(position.x-imgW/2,position.y-imgW/2);
    	
    }
}
