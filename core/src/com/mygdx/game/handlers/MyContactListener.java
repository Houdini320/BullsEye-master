package com.mygdx.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.utils.B2DBodyBuilder;

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

        System.out.println("A collision happened!");

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null) return;
        if (fa.getUserData() == null || fb.getUserData() == null) return;



    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean isB2DBodyBuilderContact (Fixture a, Fixture b) {

        return (a.getUserData() instanceof B2DBodyBuilder && b.getUserData() instanceof  B2DBodyBuilder);


    }
}