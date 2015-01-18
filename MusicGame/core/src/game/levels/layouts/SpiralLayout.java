package game.levels.layouts;

import game.MyConst;
import game.objects.ColorArea;
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
	private List<ColorArea> areas;
	
	public SpiralLayout(PlayScreen state) {
		areas=new ArrayList<ColorArea>();
		this.state=state;
		createLayout(Color.CYAN,Color.GREEN,Color.MAGENTA);
	}
	
	public void createLayout(Color... colors){
		numBoxes=colors.length;
		float y=0;
		for(Color c:colors){
			areas.add(new ColorBar(state, c, new Vector2(0,y), MyConst.APP_HEIGHT/numBoxes));
			y+=MyConst.APP_HEIGHT/numBoxes;
		}
	}
	
	public void setBoxes(List<ColorBar> boxes) {
		this.areas = boxes;
	}
	
	public void draw(ShapeRenderer renderer){
		for(ColorBar b:areas){
			b.draw(renderer);
		}
	}
	
	public List<ColorBar> getBoxes() {
		return areas;
	}
}
