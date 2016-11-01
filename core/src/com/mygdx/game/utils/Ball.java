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


    public final class Ball  {


        private Ball (){}


    public static Body createCircle (World world, int x, int y, int rad, boolean isStatic, boolean isSensor){

        Body bBody;
        BodyDef def = new BodyDef();
        if (isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = true;
        bBody = world.createBody(def);

        CircleShape shape = new CircleShape();
        shape.setRadius(rad  / 2 / PPM);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 1.0f;
        fDef.restitution = 1.0f;
        // you can use like a contact world listener for a body goes to a goal
        fDef.isSensor = isSensor;

        bBody.createFixture(shape, 1.0f);
        shape.dispose();

        return bBody;
    }


}
