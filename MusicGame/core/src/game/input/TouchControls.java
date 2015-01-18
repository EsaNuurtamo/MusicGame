package game.input;

import game.MyConst;
import game.levels.AbstractLevel;
import game.objects.Bomb;
import game.objects.AbstractObject;
import game.states.PlayScreen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

/**
 * Class has the implementation of touch controls.
 * @author esa
 * 
 */
public class TouchControls implements GestureListener{
    private PlayScreen state;
    private AbstractLevel level;
    private boolean flingReady=false;
    private AbstractObject toFling;
    public TouchControls(PlayScreen state) {
        this.state=state;
        this.level=state.getLevel();
    }
    
    /**
     * Sets the fling ready if hit on the flingable object
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
    	for(AbstractObject obj:level.getObjects()){
			if(obj.getCircle().contains(MyConst.getWorldPos(state.getViewport(), new Vector2(x,y))))
			{
				flingReady=true;
				toFling=obj;
				
				
			}
		}
		return true;
        
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(flingReady){
        	level.flingObject(toFling, velocityX, -velocityY);
        }
        return true;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

	public void setFlingReady(boolean flingReady) {
		this.flingReady = flingReady;
	}
    
}
