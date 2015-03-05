package game.levels.layouts;

import game.MyConst;
import game.objects.ColorArea;
import game.states.PlayScreen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class SpiralLayout {
	private int numAreas=0;
	private PlayScreen state;
	private List<ColorArea> areas;
	
	public SpiralLayout(PlayScreen state) {
		
		this.state=state;
		areas=new ArrayList<ColorArea>();
		
		this.state=state;
		
	}
	
	public void createLayout(Color... colors){
		
		Vector2 middle=new Vector2(MyConst.APP_WIDTH/2, MyConst.APP_HEIGHT/2);
		Vector2 leftDown=new Vector2(0,0), 
				leftUp=new Vector2(0,MyConst.APP_HEIGHT),
				rightUp=new Vector2(MyConst.APP_WIDTH, MyConst.APP_HEIGHT),
				rightDown=new Vector2(MyConst.APP_WIDTH,0);
		
		float[] vertices1={middle.x,middle.y,leftDown.x,leftDown.y,leftUp.x,leftUp.y};
		areas.add(new ColorArea(state, new Vector2(),vertices1, colors[0]));
		
		float[] vertices2={middle.x,middle.y,leftDown.x,leftDown.y,rightDown.x,rightDown.y};
		areas.add(new ColorArea(state, new Vector2(),vertices2, colors[1]));
		
		float[] vertices3={middle.x,middle.y,rightUp.x,rightUp.y,leftUp.x,leftUp.y};
		areas.add(new ColorArea(state, new Vector2(),vertices3, colors[2]));
		
		float[] vertices4={middle.x,middle.y,rightUp.x,rightUp.y,rightDown.x,rightDown.y};
		areas.add(new ColorArea(state, new Vector2(),vertices4, colors[3]));
		
		
	}
	
	public void setAreas(List<ColorArea> boxes) {
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
