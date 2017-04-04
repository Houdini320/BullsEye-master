package com.mygdx.game.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.GameScreen;

import static com.mygdx.game.utils.Constants.PPM;


/**
 * Created by Houdini on 14/12/2016.
 */
public final class KinematicBodies {

    public Body body;

    public KinematicBodies() {
    }

    public void createKine(World world, float x, float y, float height, float width, boolean isSensor) {


        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.KinematicBody;
        bDef.position.set(x / PPM, y / PPM);
        //bDef.position.set(new Vector2 (x/PPM, y/PPM));
        bDef.fixedRotation = true;
        this.body = world.createBody(bDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);


        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.restitution = 1f;
        fDef.density = 1f;
        fDef.friction = 1f;
        fDef.isSensor = isSensor;


        this.body.createFixture(fDef).setUserData(this);

        shape.dispose();




    }

}
/*

 b2Vec2 vel = body->GetLinearVelocity();
    float desiredVel = 0;
    switch ( moveState )
    {
      case MS_LEFT:  desiredVel = -5; break;
      case MS_STOP:  desiredVel =  0; break;
      case MS_RIGHT: desiredVel =  5; break;
    }
    float velChange = desiredVel - vel.x;
    float force = body->GetMass() * velChange / (1/60.0); //f = mv/t
    body->ApplyForce( b2Vec2(force,0), body->GetWorldCenter() );



        m_platform.setType(BodyType.KinematicBody);
        m_platform.setLinearVelocity(tmp.set(-m_speed,0));
        m_platform.setAngularVelocity(0);

        if(m_platform.getType()==BodyType.KinematicBody){
        Vector2 p=m_platform.getTransform().getPosition();
        Vector2 v=m_platform.getLinearVelocity(); */