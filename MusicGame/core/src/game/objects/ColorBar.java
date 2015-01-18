package game.objects;

import game.MyConst;
import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ColorBar extends AbstractObject{
	protected final Color orginalColor;
	protected Rectangle box;
	
	protected PlayScreen state;
	protected float width;
	protected float height;
	protected float blackTime=5f;
	protected boolean broken=false;
	
	public ColorBar(PlayScreen state,Color color, Vector2 position, float height) {
		this(state,color,position, MyConst.APP_WIDTH, height);
		
		
	}
	
	public ColorBar(PlayScreen state,Color color, Vector2 position, float width, float height) {
		super(state, position);
		orginalColor=color;
		this.color=color;
		
		this.state=state;
		if(position==null){
			box=new Rectangle(0,0, width, height);
    	}else{
    		box=new Rectangle(position.x,position.y, width, height);
    	}
		radius=width/2;
		this.height=height;
		this.width=width;
		
	}
	
	public void update(float delta){
		//destroyOutOfBounds();
		if(color.equals(Color.BLACK)){
			blackTime-=delta;
			if(blackTime<=0){
				blackTime=3f;
				color=orginalColor;
				
			}
		}
		

    	
    	
    	moveLogic(delta);
    	box.setPosition(position);
		
	}
	
	
	public void draw(ShapeRenderer renderer){
		renderer.setColor(color);
		renderer.rect(box.x, box.y, box.width, box.height);
	}
	public Rectangle getBox() {
		return box;
	}
     public Color getColor() {
		return color;
	}
     
     public void setColor(Color color) {
		this.color = color;
	}
     
    public void setBroken(boolean broken) {
		this.broken = broken;
	}
    @Override
    public float getWidth() {
		return width;
    }
    @Override
    public float getHeight() {
		return height;
	}
    
}
