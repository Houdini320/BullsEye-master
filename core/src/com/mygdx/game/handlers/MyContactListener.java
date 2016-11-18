package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.B2DBodyBuilder;
import com.mygdx.game.utils.Ball;

/**
 * Created by Houdini on 2/11/2016.
 */
public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

                if (fa == null || fb == null) return;
                if (fa.getUserData() == null || fb.getUserData() == null) return;


        if (isContact (fa, fb)) {
            B2DBodyBuilder tba = (B2DBodyBuilder) fa.getUserData();
            Ball tbb = (Ball) fb.getUserData();

            tba.hit();
        }

        if (isSensorContact(fa, fb)){
            B2DBodyBuilder sensor;
            Ball ball2;
            if(fa.getUserData()instanceof B2DBodyBuilder){
                sensor = (B2DBodyBuilder) fa.getUserData();
                ball2 = (Ball) fb.getUserData();
            }
            else {
                sensor = (B2DBodyBuilder) fb.getUserData();
                ball2 = (Ball) fa.getUserData();
            }
            sensor.hit();
            sensor.trigger();

        }

        System.out.println("A collision happened!");

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) return;
        if (fa.getUserData() == null || fb.getUserData() == null) return;

        System.out.println("A collision stoped!");


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isContact (Fixture a, Fixture b) {
        //Para diferenciar entre dos Bodies distintos
        if (a.getUserData() instanceof B2DBodyBuilder || b.getUserData() instanceof Ball){
            if (a.getUserData()instanceof Ball || b.getUserData() instanceof B2DBodyBuilder){
                return true;
            }
        }
        return false;
        //return (a.getUserData() instanceof B2DBodyBuilder && b.getUserData() instanceof  Ball);


    }
    private boolean isSensorContact (Fixture a, Fixture b){
        if (a.getUserData() instanceof  B2DBodyBuilder || b.getUserData() instanceof  B2DBodyBuilder ){
            if (a.getUserData() instanceof  Ball || b.getUserData() instanceof  Ball ){
                return true;
            }
        }
        return false;
    }
}
