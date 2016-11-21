package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Created by Houdini on 4/11/2016.
 */
public final class Basquet {

    //Body base, lat1, lat2, goal;


    //private Basquet(){}

    public static Body createBos (World world, float x, float y, float width, float height, boolean isStatic, boolean isSensor){

        Body bBody;

        BodyDef def = new BodyDef();
        if (isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        bBody = world.createBody(def);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 1.0f;
        fDef.isSensor = isSensor;

        bBody.createFixture(fDef).setUserData(bBody);

        shape.dispose();

        return bBody;

        //bBody.getFixtureList().get(0).setUserData();

    }

    public void hit (){
        System.out.println("he sido golpeado");
    }

    public void trigger (){
        System.out.println("TRIGGERED");
        //  triggerBody.applyAngularImpulse(2f, false);

    }
    public void untrigger (){
        System.out.println("UNTRIGGERED");
        //  triggerBody.applyAngularImpulse(2f, false);

    }
    //public void createBasquet (World world, Body[] boxes) {

        //Formar tacho con los obstaculos
        //Body[] canasta = new Body[4];
       // Array<Body> canasta = new Array<Body>(4);
       // boxes[1] = createBos(world, 650, 50, 100, 15, true, false);
       // boxes[2] = createBos(world, 605, 140, 10,  170, true, false);
       // boxes[3] = createBos(world, 695, 140, 10, 170, true, false);
       // boxes[4] = createBos(world, 650, 80, 80, 50, true, true);
        //Basquet.createBos(world,200, 100, 100, 100, true, true);
   // }
    //Formar tacho con los obstaculos
    //base = createBox(world, 650, 50, 100, 15, true, false);
    //lat1 = createBox(world, 605, 140, 10,  170, true, false);
    //lat2 = createBox(world, 695, 140, 10, 170, true, false);
    //goal = createBox(world, 650, 80, 80, 50, true, true);
}
