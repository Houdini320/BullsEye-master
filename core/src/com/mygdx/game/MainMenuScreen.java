package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Screen;

/**
 * Created by Houdini on 30/9/2016.
 */

public class MainMenuScreen implements Screen {

    final BullsEyes game;
    OrthographicCamera camera;

    public MainMenuScreen(final BullsEyes gam) {
          game = gam;

          camera = new OrthographicCamera();
          camera.setToOrtho(false, 800, 600);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Bienvenido a BullsEye!!! ", 170, 350);
        game.font.draw(game.batch, "Toca la pantalla para comenzar!", 50, 250);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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
    }
}