package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.Viewport;
/**
 * This class has all the static content.
 */
public class MyConst {
	public static final float APP_WIDTH = 480;
    public static final float APP_HEIGHT = 800;
    
    public static Skin skin;
    
    public static void createSkin(){
        skin = new Skin();
	
        //TEXTURES///////
	    Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.PURPLE);
		pixmap.fill();
		skin.add("purple", new Texture(pixmap));
	            
	    pixmap = new Pixmap(10, 100, Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fill();
		skin.add("black", new Texture(pixmap));
		
		pixmap = new Pixmap(10, 100, Format.RGBA8888);
		pixmap.setColor(Color.DARK_GRAY);
		pixmap.fill();
		skin.add("gray", new Texture(pixmap));
		/////////////////////////////
	
		//FONTS/////////////////////
		BitmapFont bfont=new BitmapFont(Gdx.files.internal("mainFont.fnt"));
		bfont.scale(0.02f);
		skin.add("default",bfont);
		
		bfont=new BitmapFont(Gdx.files.internal("mainFont.fnt"));
		bfont.scale(0.3f);
		bfont.setColor(Color.BLACK);
		skin.add("titleFont",bfont);
		
		bfont=new BitmapFont();
		bfont.scale(1.0f);
		skin.add("scoreFont",bfont);
		/////////////////////////////
		
		// STYLES//////////////////////////
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("purple", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("purple", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("purple", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("purple", Color.LIGHT_GRAY);
	    textButtonStyle.font = skin.getFont("default");
	    skin.add("default", textButtonStyle);
        
        LabelStyle label=new LabelStyle();
        label.font=skin.getFont("default");
        skin.add("default", label);
        
        label=new LabelStyle();
        label.font=skin.getFont("scoreFont");
        label.fontColor=Color.MAGENTA;
        skin.add("score", label);
        
        label=new LabelStyle();
        label.font=skin.getFont("titleFont");
        label.fontColor=Color.GREEN;
        skin.add("title", label);
        
        TextFieldStyle fieldStyle=new TextFieldStyle();
        fieldStyle.font=skin.getFont("default");
        fieldStyle.fontColor=Color.MAGENTA;
        fieldStyle.background=skin.getDrawable("gray");
        fieldStyle.cursor=skin.getDrawable("purple");
        fieldStyle.cursor.setMinWidth(2f);
        skin.add("default", fieldStyle);
        /////////////////////////////////////////////
	            
    }
    
    public static Vector2 getWorldPos(Viewport port, Vector2 vect){
		Vector2 v=port.unproject(vect);
		return v;
		
	}
    
    public static Vector2 getCenter(){
    	return new Vector2(APP_WIDTH/2, APP_HEIGHT/2);
    }

}
