package game.input;

import game.objects.ColorBar;
import game.objects.AbstractObject;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
/**
 * Offering some conversion methods for mouse coordinates.
 * @author esa
 */
public class Mouse {
	
	public static Vector2 getScreenPos(){
		
		return new Vector2(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
	}
	public static float getX(){
		return getScreenPos().x;
	}
	public static float getY(){
		return getScreenPos().y;
	}
	
	
	public static Vector2 getWorldPos(Viewport port){
		Vector2 v=port.unproject(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
		return v;
		
	}
	
	

}
