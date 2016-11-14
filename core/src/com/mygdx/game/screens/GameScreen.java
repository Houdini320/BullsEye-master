package com.mygdx.game.screens;

/**
 * Created by Houdini on 2/10/2016.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.BullsEyes;
import com.mygdx.game.handlers.MyContactListener;
import com.mygdx.game.utils.B2DBodyBuilder;
import com.mygdx.game.utils.Ball;
import com.mygdx.game.utils.Basquet;

import java.util.Random;

import static com.mygdx.game.utils.Constants.PPM;


public class GameScreen extends AbstractScreen {


    private final float SCALE = 2.0F;


    //Camera
    OrthographicCamera camera;

    //Box2D
    World world;
    Box2DDebugRenderer b2dr;

    //Game Bodies
    Body ball, ball2;
    Body obstacle, obstacle2, obstacle3, obstacle4, obstacle5;
    Body goal1, goal2, goal3, goal4;


    //Batch
    SpriteBatch batch;
    Texture tex, tacho;

    //Musica
    Music fondoMusic;

    //Random
    Random rnd;

    //private OrthogonalTiledMapRenderer tmr;
    // private TiledMap map;


    public GameScreen(final BullsEyes app) {

        super(app);


        // carga la musica de fondo y hace que se repita indefinidamente
        fondoMusic = Gdx.audio.newMusic(Gdx.files.internal("fondo.wav"));
        fondoMusic.setLooping(true);


        //float w = Gdx.graphics.getWidth();
        //float h = Gdx.graphics.getHeight();


        // create the camera and the SpriteBatch
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, BullsEyes.V_WIDTH, BullsEyes.V_HEIGHT);
        this.b2dr = new Box2DDebugRenderer();

        //Setup the world
        world = new World(new Vector2(0, -9.8f), false);
        world.setContactListener(new MyContactListener());

        //


        //map = new TmxMapLoader().load("Maps/map test2.tmx");
        //tmr = new OrthogonalTiledMapRenderer(map);


    }


    @Override
    public void show() {

        initArena();
        batch = new SpriteBatch();
        tex = new Texture("ball.png");
        //tacho = new Texture("tachox64.png");


        // start the playback of the background music
        // when the screen is shown


    }


    public void update(float delta) {

        world.step(1f / BullsEyes.APP_FPS, 6, 2);

        //Get Mouse Position - Move Ball
        //float mousePosToWorld = -(Gdx.input.getY() - Gdx.input.getX()) / PPM;
        //ball.setTransform(ball.getPosition().x, mousePosToWorld,ball.getAngle());

        //Cada vez que introducimos un actor al stage se actualice la pantalla
        stage.act(delta);

        inputUpdate(delta);


        //cameraUpdate(delta);

        //tmr.setView(camera);

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        //Renderiza el Mundo y la camara escalados en el Box2D
        b2dr.render(world, camera.combined.cpy().scl(PPM));
        stage.draw();

        //Coordina el Batch y el shape con las coordenadas de la camara
        app.batch.setProjectionMatrix(camera.combined);
        app.shapeBatch.setProjectionMatrix(camera.combined);

        //Inicia la musica en la Pantalla de juego
        fondoMusic.play();

        //
        //update(Gdx.graphics.getDeltaTime());

        // tell the camera to update its matrices.
        // camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //app.batch.setProjectionMatrix(camera.combined);


        batch.begin();
        batch.draw(tex, ball.getPosition().x * PPM - (tex.getWidth() / 2), ball.getPosition().y * PPM - (tex.getHeight() / 2));
        //batch.draw(tacho, createBasquet().getPosition().x * PPM - (tacho.getWidth() / 2), tacho.getPosition().y * PPM - (tacho.getHeight() / 2));
        batch.end();

        //tmr.render();


        //ApplicationAdapter.render();


    }

    @Override
    public void resize(int width, int height) {
        //camera.setToOrtho(false, width / SCALE, height / SCALE);


    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        super.dispose();
        b2dr.dispose();
        world.dispose();
        tex.dispose();

        fondoMusic.dispose();
        batch.dispose();
        //tmr.dispose();
        //map.dispose();


    }

    private void initArena() {
        //Crea las paredes alrededor de la pantalla
        createWalls();
        //crea el canasto desde el array
        createBasquet(300,110);


        //Crea los Objetos "Obstaculos" Estaticos o Dinamicos de la Clase B2DBodyBuilder
        //nombre = Clase y nombre del Metodo (Inserta en el world, posicion x, posicion y, Tamaño ancho, tamaño alto, Estatico/Dinamico, Sensor de contacto)
        obstacle = B2DBodyBuilder.createBox(world, camera.viewportWidth / 2, camera.viewportHeight / 2, 28, 28, true, true);
        obstacle2 = B2DBodyBuilder.createBox(world, 399, 519, 10, 80, true, false);
        obstacle3 = B2DBodyBuilder.createBox(world, 700, 350, 100, 50, true, false);
        obstacle3.getFixtureList().get(0).setUserData(obstacle3);
        obstacle4 = B2DBodyBuilder.createBox(world, 200, 550, 120, 20, true, false);

        //Formar tacho con los obstaculos
        /**  goal1 = B2DBodyBuilder.createBox(world, 650, 50, 100, 15, true, false);
         goal2 = B2DBodyBuilder.createBox(world, 605, 140, 10,  170, true, false);
         goal3 = B2DBodyBuilder.createBox(world, 695, 140, 10, 170, true, false);
         goal4 = B2DBodyBuilder.createBox(world, 650, 80, 70, 40, true, true);
         */
        //
        ball = B2DBodyBuilder.createCircle(world, 50, 100, 38, false, false);

        //Crea la Pelota del Player de la clase Ball
        ball2 = Ball.createCircle(world, 100, 200, 50, false, false);
        //ball2.getFixtureList().get(0).setUserData(ball2);

    }

    //Metodo con las indicaciones de los vectores de las paredes
    private void createWalls() {

        Vector2[] verts = new Vector2[5];
        verts[0] = new Vector2(0 / PPM, 0);
        verts[1] = new Vector2(camera.viewportWidth / PPM, 0);
        verts[2] = new Vector2(camera.viewportWidth / PPM, 600 / PPM);
        verts[3] = new Vector2(0 / PPM, 600 / PPM);
        verts[4] = new Vector2(0 / PPM, 0 / PPM);
        B2DBodyBuilder.createChainShape(world, verts);

    }

    private void createBasquet(float posX, float posY) {
        //Formar tacho con los obstaculos
        float x, y;
        Body[] boxes  = new Body[4];
// posX y posY es el centro del tacho el tacho tiene una altura de 185 y un ancho de 100
        x = posX/PPM;
        y = posY/PPM;
        //Array<Body> boxes = new Array<Body>(4);
        boxes[0] = Basquet.createBos(world, x * PPM, y * PPM - 85, 100, 15, true, false);
        boxes[1] = Basquet.createBos(world, x * PPM - 45, y * PPM + 7.5f, 10, 170, true, false);
        boxes[2] = Basquet.createBos(world, x * PPM + 45, y * PPM + 7.5f, 10, 170, true, false);
        boxes[3] = Basquet.createBos(world, x * PPM, y * PPM - 52, 80, 50, true, true);


    }


    public void inputUpdate(float delta) {
        int horizontalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1;

        }

        if (Gdx.input.isKeyJustPressed((Input.Keys.DOWN))) {
            ball.getPosition();

        }
        if (Gdx.input.isKeyJustPressed((Input.Keys.UP))) {
            ball.applyForceToCenter(0, 500, false);
        }

        ball.setLinearVelocity(horizontalForce * 5, ball.getLinearVelocity().y);

        // Input para que salga del juego en android con la tecla BackKey
        if (Gdx.input.isCatchBackKey()) {
            Gdx.app.exit();
        }
        //Input para que cuando toques la pantalla del celular  la Pelota salte
        if (Gdx.input.isTouched()) {
            ball.applyForceToCenter(0, 500, false);


            //Gdx.input.setInputProcessor(new SimplerTouchTest());

            //SimplerTouchTest inputProcessor = new SimplerTouchTest();
            //Gdx.input.setInputProcessor(inputProcessor);


        }


    }

/**
 //Metodo para que la camara siga algun objeto o quede fija en alguna posicion
 public void cameraUpdate(float delta) {
 float w = Gdx.graphics.getWidth();
 float h = Gdx.graphics.getHeight();
 Vector3 position = camera.position;
 // hacer que la camara se demore un ratito en seguir al perdonaje
 //a + (b - a) * lerp
 // b = target
 // a = current camera position
 //position.x = camera.position.x + (platform.getPosition().x * PPM - camera.position.x) * .1f;
 //position.y = camera.position.y + (platform.getPosition().y * PPM - camera.position.y) * .1f;
 Vector2 p = obstacle.getPosition();
 position.x = w / SCALE / 2;
 position.y = h / SCALE / 2;
 camera.position.set(position);


 camera.update();


 }
 */


}



