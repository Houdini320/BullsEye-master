package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.xpath.internal.operations.String;


/**
 * Created by sebas on 21/11/2016.
 */

public class Tacho {
    public Body body;
   // public String id;

    public Tacho(World world, float x, float y) {
      //  this.id = id;
        createBoxBody(world, x, y);

    }

    private void createBoxBody(World world, float x, float y) {
        BodyDef def = new BodyDef();
        def.fixedRotation = true;
        def.type = BodyDef.BodyType.StaticBody;
        def.position.set(x / Constants.PPM, y / Constants.PPM);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32 / Constants.PPM / 2, 32 / Constants.PPM / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        this.body = world.createBody(def);
        this.body.createFixture(fixtureDef).setUserData(this);

    }

    public void hit() {
        System.out.println(" ME GOLPEARON");
    }
}
