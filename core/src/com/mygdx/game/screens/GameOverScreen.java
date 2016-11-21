package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.game.BullsEyes;

/**
 * Created by Houdini on 15/11/2016.
 */
public class GameOverScreen extends AbstractScreen {

    OrthographicCamera camera;
    Box2DDebugRenderer b2dr;

    SpriteBatch batch;
    BitmapFont font, font2;


    public GameOverScreen(final BullsEyes app) {
        super(app);


    }

    @Override
    public void render(float delta) {

        //Muestra el color de la Patalla del MainMenu
        Gdx.gl.glClearColor(0, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Dibuja las letras de la pantalla MainMenu
        app.batch.begin();
        font.draw(app.batch, "Game Over", 220, 350);
        font2.draw(app.batch, "Toca la pantalla para volver al Menu", 120, 100);
        app.batch.end();

        if (Gdx.input.isTouched()) {
            app.setScreen(new MainMenuScreen(app));
            dispose();
        }

    }

    @Override
    public void update(float delta) {

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.setToOrtho(false, BullsEyes.V_WIDTH, BullsEyes.V_HEIGHT);
        b2dr = new Box2DDebugRenderer();

        //Dice donde se encuentran los archivos de la Fuente de GameOVer
//        font = new BitmapFont(Gdx.files.internal("gameover.fnt"), Gdx.files.internal("gameover.png"), false);
//        font2 = new BitmapFont(Gdx.files.internal("gameover25.fnt"), Gdx.files.internal("gameover25.png"), false);
        batch = new SpriteBatch();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        batch.dispose();
        font.dispose();
        b2dr.dispose();

    }
}
