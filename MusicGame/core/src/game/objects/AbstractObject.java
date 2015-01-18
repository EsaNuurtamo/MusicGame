package game.objects;



import game.MyConst;
import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;


/**
 * Class has the common properties of all the objects in game. All the objects are round.
 * Has the basic vector logic for moving to certain direction andlogic for drawing the image.
 * @author esa
 */
public abstract class AbstractObject{
	protected PlayScreen state;
	
	protected Vector2 position;
	protected Vector2 direction;
	protected Vector2 velocity;
	protected float speed;
	protected float radius;
	protected Circle circle;
	protected Color color;
	protected boolean destroyed;
	protected boolean outOfBounds;
	
    public AbstractObject(PlayScreen state) {
    	this(state, null);
    }
    
    public AbstractObject(PlayScreen state, Vector2 position){
    	this(state,position, 40f);
    }
    /**
     * Creates object with location and radius
     * @param state is the PlayScreen class
     * @param position of the object
     * @param radius of the object
     */
    public AbstractObject(PlayScreen state, Vector2 position, float radius){
    	this.radius=radius;
    	this.state=state;
    	this.position=position;
    	
    	if(position==null){
    		this.circle=new Circle(0,0,radius);
    	}else{
    		this.circle=new Circle(position.x,position.y,radius);
    	}
    	
    	this.destroyed=false;
    	this.outOfBounds=false;
    }
    /**
     * Draws the graphics of the object.
     * <p>
     * !!CHANGE: implement pixel graphics and remove the shape graphics
     * @param renderer
     */
    public void draw(ShapeRenderer renderer){
    	renderer.setColor(Color.BLACK);
		renderer.circle(circle.x, circle.y, circle.radius);
    	renderer.setColor(color);
		renderer.circle(circle.x, circle.y, circle.radius-10);
    }
    
    /**
     * Object is moved to the direction of its direction vector with the distance defined by the speed variable.
     * @param delta is the time between the frames got from PlayScreen 
     */
    public void moveLogic(float delta){
    	if(position!=null&&direction!=null){
    		position=position.add(direction.cpy().limit(speed*delta));
    	}
    }
    
    /**
     * Tells if the object is out of the area of the game. And then it can be for example destroyed.
     * <p>
     * !!Repair--Now the 150 is just a guess; exact value must be figured out.
     * @return boolean 
     */
    public boolean isOutOfBounds(){
    	if(position.y<-150||position.y>MyConst.APP_HEIGHT+150||
    	   position.x<-150||position.x>MyConst.APP_WIDTH+150)
    	{
    		return true;
    	}
    	return false;
    }
    
    /**
     * Each object does different things in update method.
     * All the magic happens here ;)
     * @param delta is the time between the frames got from PlayScreen 
     */
    public abstract void update(float delta);
    
    //////Getters and setters//////////
    public PlayScreen getState() {
		return state;
	}
    public void setState(PlayScreen state) {
		this.state = state;
	}
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public Circle getCircle() {
		return circle;
	}
	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isDestroyed(){
    	return destroyed;
    }
    public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}	
	public float getWidth() {
		return radius*2;
    }
    public float getHeight() {
		return radius*2;
	}
}
