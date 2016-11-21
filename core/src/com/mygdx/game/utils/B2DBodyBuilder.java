package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Created by Houdini on 28/10/2016.
 */
public final class B2DBodyBuilder {

    private B2DBodyBuilder(){}

    public static Body createBox (World world, float x, float y, float width, float height, boolean isStatic, boolean isSensor){

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
        fDef.friction = .25f;
        fDef.restitution = 1f;
        fDef.isSensor = isSensor;
        //bBody.createFixture(fDef);

        bBody.createFixture(fDef).setUserData(bBody);

        //world.createBody(def).createFixture(fDef);

        shape.dispose();

        return bBody;

        //bBody.getFixtureList().get(0).setUserData();



    }

    public static Body createCircle(World world, float x, float y, float rad, boolean isStatic, boolean isSensor) {

        Body cBody;
        BodyDef def = new BodyDef();

        if (isStatic)
            def.type = BodyDef.BodyType.StaticBody;
        else
            def.type = BodyDef.BodyType.DynamicBody;
        def.position.set(x / PPM, y / PPM);
        def.fixedRotation = false;
        cBody = world.createBody(def);

        CircleShape shape = new CircleShape();
        shape.setRadius(rad / 2 / PPM);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 2f;
        fDef.restitution = 1f;
        fDef.friction = 0f;
        fDef.isSensor = isSensor;
        
        cBody.createFixture(fDef).setUserData(cBody);


       // world.createBody(def).createFixture(fDef);

        //this.body = world.createBody(def);
        //this.body.createFixture(fDef).setUserData(this);
        shape.dispose();

        return cBody;
    }


    public static Body createChainShape (World world, Vector2[] verts) {
        Body wbody;
        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        wbody = world.createBody(bDef);

        ChainShape shape = new ChainShape();
        shape.createChain(verts);

        wbody.createFixture(shape, 1.0f);
        shape.dispose();
        return wbody;

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
}
