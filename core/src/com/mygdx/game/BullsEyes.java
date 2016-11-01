package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.GameScreenManager;
import com.mygdx.game.screens.MainMenuScreen;


public class BullsEyes extends Game {

	//Variables de la Application
	public static String APP_TITLE = "BullsEyes";
	public static double APP_VERSION = 0.1;
	public static int APP_DESKTOP_WIDTH = 800;
	public static int APP_DESKTOP_HEIGHT = 600;
	public static int APP_FPS = 60;



	//Variables del Game
	public static int V_WIDTH = 800;
	public static int V_HEIGHT = 600;

	//Variables de los Managers
	public GameScreenManager gsm;
	public AssetManager assets;

	//Batches
	public SpriteBatch batch;
	public ShapeRenderer shapeBatch;



	@Override
	public void create () {

		//Setup Managers
		gsm = new GameScreenManager(this);
		assets = new AssetManager();


		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();


		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();


		//Input para que salga de la Pantalla con la tecla Escape
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
	}
	
	@Override
	public void dispose () {
		assets.dispose();
		shapeBatch.dispose();
		batch.dispose();
		//font.dispose();


	}
}
