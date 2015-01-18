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
			
		}
	}
	
	public void setBoxes(List<ColorArea> boxes) {
		this.areas = boxes;
	}
	
	public void draw(ShapeRenderer renderer){
		for(ColorArea b:areas){
			b.draw(renderer);
		}
	}
	
	public List<ColorArea> getAreas() {
		return areas;
	}
}
