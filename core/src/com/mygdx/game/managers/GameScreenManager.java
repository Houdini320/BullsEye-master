package com.mygdx.game.managers;

import com.mygdx.game.BullsEyes;
import com.mygdx.game.screens.AbstractScreen;
import com.mygdx.game.screens.GameOverScreen;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainMenuScreen;
import java.util.HashMap;


/**
 * Created by Houdini on 7/10/2016.
 */
public class GameScreenManager {

    public final BullsEyes app;
    private HashMap <STATE, AbstractScreen> gameScreens;

    //Enumera las pantalla por posicion no por nombre
    public enum STATE {
        MAIN_MENU,
        PLAY,
        GAMEOVER

    }

    public GameScreenManager(final BullsEyes app) {
        this.app = app;

        initGameScreens();
        setScreen(STATE.MAIN_MENU);
        setScreen(STATE.PLAY);
        // crear pantalla GAMEOVER
        setScreen(STATE.GAMEOVER);

    }

    private void initGameScreens(){
        this.gameScreens = new HashMap<STATE, AbstractScreen>();
        this.gameScreens.put(STATE.MAIN_MENU, new MainMenuScreen(app));
        this.gameScreens.put(STATE.PLAY, new GameScreen(app));
        this.gameScreens.put(STATE.GAMEOVER, new GameOverScreen(app));
    }

    public void setScreen (STATE nextScreen) {
        app.setScreen(gameScreens.get(nextScreen));
    }

    public void dispose(){
        for (AbstractScreen screen : gameScreens.values()){
            if (screen != null){
                screen.dispose();
            }
        }
    }

}
