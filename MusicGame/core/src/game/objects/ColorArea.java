package game.objects;

import game.states.PlayScreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class ColorArea extends AbstractObject{
	Polygon area;
	protected Color orginalColor;
	public ColorArea(PlayScreen state, Vector2 position, float[] vertices, Color color) {
		super(state, position);
		area=new Polygon();
		area.setPosition(0, 0);
		orginalColor=color;
		
		area.setVertices(vertices);
	}

	@Override
	public void update(float delta) {
		
		
	}
	
	public void setVertices(float[] list){
		area.setVertices(list);
	}
	
	@Override
	public void draw(ShapeRenderer renderer) {
		
    	renderer.setColor(orginalColor);
    	float [] f=area.getVertices();
		renderer.triangle(f[0], f[1], f[2], f[3], f[4], f[5]);
	}

}
