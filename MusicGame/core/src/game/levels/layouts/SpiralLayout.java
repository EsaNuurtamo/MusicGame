package game.levels.layouts;

import game.MyConst;
import game.objects.ColorBar;
import game.states.PlayScreen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class SpiralLayout {
	private int numBoxes=0;
	private PlayScreen state;
	private List<ColorBar> boxes;
	
	public SpiralLayout(PlayScreen state) {
		boxes=new ArrayList<ColorBar>();
		this.state=state;
		createLayout(Color.CYAN,Color.GREEN,Color.MAGENTA);
	}
	
	public void createLayout(Color... colors){
		numBoxes=colors.length;
		float y=0;
		for(Color c:colors){
			boxes.add(new ColorBar(state, c, new Vector2(0,y), MyConst.APP_HEIGHT/numBoxes));
			y+=MyConst.APP_HEIGHT/numBoxes;
		}
	}
	
	public void setBoxes(List<ColorBar> boxes) {
		this.boxes = boxes;
	}
	
	public void draw(ShapeRenderer renderer){
		for(ColorBar b:boxes){
			b.draw(renderer);
		}
	}
	
	public List<ColorBar> getBoxes() {
		return boxes;
	}
}
