package com.mygdx.game.utils;


import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Created by Houdini on 6/10/2016.
 */


public final class Ball {

    public Body body;
    //private Fixture fixture;
    //private World world;
    //public final float RADIUS = .2f;


    public Ball() {
    }


    public void createCircle(World world, int x, int y, int rad, boolean isStatic, boolean isSensor) {

        BodyDef def = new BodyDef();
        if (isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = false;
        this.body = world.createBody(def);

        CircleShape shape = new CircleShape();
        shape.setRadius(rad / 2f / PPM);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = .25f;
        fDef.friction = .25f;
        fDef.restitution = 0.9f;
        // you can use like a contact world listener for a body goes to a goal
        fDef.isSensor = isSensor;

        //  bBody.createFixture(shape, 1.0f);
        //utilizando esta sentencia tiene Fixture pero la textura no sigue la pelota
        //world.createBody(def).createFixture(fDef);
        //Utilizando esta sentencia la pelota rebota y la tectura la sigue pero crashea el joint :/
        this.body.createFixture(fDef).setUserData(this);
        //this.bBody = world.createBody(def);
        //this.bBody.createFixture(fDef).setUserData(this);
        shape.dispose();
    }

    public void hit() {
        System.out.println("he sido golpeado");
    }

    public void trigger() {
        System.out.println("TRIGGERED BALL");
        //  triggerBody.applyAngularImpulse(2f, false);

    }

    public void untrigger() {
        System.out.println("UNTRIGGERED BALL");
        //  triggerBody.applyAngularImpulse(2f, false);

    }


    /**  public Body getBody(){
     return body;
     }

     public Fixture getFixture(){
     return fixture;

     */
}
