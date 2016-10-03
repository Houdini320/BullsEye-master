package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Screen;

/**
 * Created by Houdini on 30/9/2016.
 */



public class MainMenuScreen implements Screen {

    final BullsEyes game;
    OrthographicCamera camera;
    Music  introMusic;

    public MainMenuScreen(final BullsEyes gam) {
          game = gam;

          camera = new OrthographicCamera();
          camera.setToOrtho(false, 800, 600);

        introMusic = Gdx.audio.newMusic(Gdx.files.internal("intro.wav"));
        introMusic.setLooping(true);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(102, 143, 255.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Bienvenido a BullsEyes ", 150, 450);
        game.font.draw(game.batch, "Toca la pantalla para comenzar", 80, 250);
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
        introMusic.play();
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
       introMusic.dispose();
    }
}