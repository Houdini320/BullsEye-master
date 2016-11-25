package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utils.B2DBodyBuilder;
import com.mygdx.game.utils.Ball;
import com.mygdx.game.utils.Basquet;
import com.mygdx.game.utils.Tacho;

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


        if (isContact(fa, fb)) {
            B2DBodyBuilder cuerpo;
            Ball ball2;
            if (fa.getUserData() instanceof Basquet) {
                cuerpo = (B2DBodyBuilder) fa.getUserData();
                ball2 = (Ball) fb.getUserData();
            } else {
                cuerpo = (B2DBodyBuilder) fb.getUserData();
                ball2 = (Ball) fa.getUserData();
            }


            cuerpo.hit();
            ball2.hit();
        }

        if (isSensorContact(fa, fb)) {
            Basquet sensor;
            Ball ball2;
            if (fa.getUserData() instanceof Basquet) {
                sensor = (Basquet) fa.getUserData();
                ball2 = (Ball) fb.getUserData();
            } else {
                sensor = (Basquet) fb.getUserData();
                ball2 = (Ball) fa.getUserData();
            }
            sensor.hit();
            sensor.trigger();
            ball2.trigger();

        }
        if (isContacTacho(fa, fb)) {
            Tacho sensor;
            Ball ball2;
            if (fa.getUserData() instanceof Tacho) {
                sensor = (Tacho) fa.getUserData();
                ball2 = (Ball) fb.getUserData();
            } else {
                sensor = (Tacho) fb.getUserData();
                ball2 = (Ball) fa.getUserData();
            }
            sensor.hit();
            ball2.trigger();

        }

         System.out.println("A collision happened!");

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) return;
        if (fa.getUserData() == null || fb.getUserData() == null) return;

        if (isSensorContact(fa, fb)) {
            Basquet sensor;
            Ball ball2;
            if (fa.getUserData() instanceof Basquet) {
                sensor = (Basquet) fa.getUserData();
                ball2 = (Ball) fb.getUserData();
            } else {
                sensor = (Basquet) fb.getUserData();
                ball2 = (Ball) fa.getUserData();
            }
            sensor.hit();
            sensor.untrigger();
            ball2.untrigger();
        }

        System.out.println("A collision stoped!");


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isContact(Fixture a, Fixture b) {
        //Para diferenciar entre dos Bodies distintos
        if (a.getUserData() instanceof B2DBodyBuilder || b.getUserData() instanceof B2DBodyBuilder) {
            if(a.getUserData() instanceof Ball|| b.getUserData()instanceof Ball){
                return true;
            }

        }
        return false;
        //return (a.getUserData() instanceof B2DBodyBuilder && b.getUserData() instanceof  Ball);


    }

    private boolean isSensorContact(Fixture a, Fixture b) {
        if (a.getUserData() instanceof Basquet || b.getUserData() instanceof Basquet) {
            if(a.getUserData() instanceof Ball|| b.getUserData()instanceof Ball){
                return true;
            }

        }
        return false;
    }
    private boolean isContacTacho(Fixture a, Fixture b) {
        if (a.getUserData() instanceof Tacho || b.getUserData() instanceof Tacho) {
            if(a.getUserData() instanceof Ball|| b.getUserData()instanceof Ball){
                return true;
            }
            }

        return false;
    }
}
