package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.managers.GameScreenManager;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import com.mygdx.game.utils.Ball;


public class BullsEyes extends Game implements InputProcessor {

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


	// we will use 32px/unit in world
	public final static float SCALE = 32f;
	public final static float INV_SCALE = 1.f/SCALE;
	// this is our "target" resolution, not that the window can be any size, it is not bound to this one
	public final static float VP_WIDTH = 800 * INV_SCALE;
	public final static float VP_HEIGHT = 600 * INV_SCALE;

	private OrthographicCamera camera;
	private ExtendViewport viewport;
	private ShapeRenderer shapes;


	MouseJointDef jointDef;
	MouseJoint joint;


	@Override
	public void create () {

		//Setup Managers
		gsm = new GameScreenManager(this);
		assets = new AssetManager();


		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();

		camera = new OrthographicCamera();
		// pick a viewport that suits your thing, ExtendViewport is a good start
		viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
		// ShapeRenderer so we can see our touch point
		shapes = new ShapeRenderer();
		//Gdx.input.setInputProcessor(this);



		this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
		super.render();



		shapes.setProjectionMatrix(camera.combined);
		shapes.begin(ShapeRenderer.ShapeType.Filled);
		shapes.circle(tp.x, tp.y, 0.25f, 16);
		shapes.end();
		//mouse joint
		jointDef = new MouseJointDef();
		jointDef.bodyA = GameScreen.ball;
		jointDef.collideConnected = true;
		jointDef.maxForce = 5000;


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

	Vector3 tp = new Vector3();
	Vector2 tp2 = new Vector2();
	//boolean dragging;

	QueryCallback queryCallback = new QueryCallback() {
		@Override
		public boolean reportFixture(Fixture fixture) {
			if (!fixture.testPoint(tp.x, tp.y))
				return true;
			jointDef.bodyB = GameScreen.ball2;
			//jointDef.bodyB = fixture.getBody();
			jointDef.target.set(tp.x, tp.y);
			joint = (MouseJoint) GameScreen.world.createJoint(jointDef);

			return false;
		}
	};



	@Override public boolean mouseMoved (int screenX, int screenY) {
		// we can also handle mouse movement without anything pressed
		camera.unproject(tp.set(screenX, screenY, 0));
		return false;
	}

	@Override public boolean touchDown (int screenX, int screenY, int pointer, int button) {

		// ignore if its not left mouse button or first touch pointer
		//if (button != Input.Buttons.LEFT || pointer > 0) return false;
		camera.unproject(tp.set(screenX, screenY, 0));
		GameScreen.world.QueryAABB(queryCallback, tp.x , tp.y , tp.x, tp.y);

		//dragging = true;

		return false;
	}

	@Override public boolean touchDragged (int screenX, int screenY, int pointer) {
		//if (!dragging) return false;
		if (joint == null)
			return false;

		//to check for a specific button.
		// Gdx.input.isButtonPressed();
		camera.unproject(tp.set(screenX, screenY, 0));
		joint.setTarget(tp2.set(tp.x, tp.y));
		return true;
	}

	@Override public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		//if (button != Input.Buttons.LEFT || pointer > 0) return false;
		if (joint == null)
			return false;

		GameScreen.world.destroyJoint(joint);
		joint = null;

		//camera.unproject(tp.set(screenX, screenY, 0));
		//camera.unproject(tp.set(Gdx.input.getX(), Gdx.input.getY(), 0));
		//dragging = true;
		return true;
	}

	@Override public void resize (int width, int height) {
		// viewport must be updated for it to work properly
		viewport.update(width, height, true);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.useHDPI = true;
		new LwjglApplication(new SimplerTouchTest(), config);
	}

}
