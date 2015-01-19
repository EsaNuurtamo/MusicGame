package game.objects;

import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class ColorArea extends AbstractObject{
	Polygon area;
	public ColorArea(PlayScreen state, Vector2 position, Vector2[] vertices) {
		super(state, position);
		area=new Polygon();
		float[] vert=new float[vertices.length*2];
	}

	@Override
	public void update(float delta) {
		
		
	}
	
	public void setVertices(float[] list){
		area.setVertices(list);
	}
	
	@Override
	public void draw(ShapeRenderer renderer) {
		
    	renderer.setColor(color);
		renderer.polygon(area.getVertices());
	}

}
