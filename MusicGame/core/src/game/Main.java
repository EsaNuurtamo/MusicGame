package game;

import game.states.MenuScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
/**
 * This class acts as state handler
 */
public class Main extends Game {

	    

		@Override
		public void create() {
			
			MyConst.createSkin();
			setScreen(new MenuScreen(this));
		    
		}

	    
}

