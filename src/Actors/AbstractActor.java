package Actors;

import Components.AbstractPatrolStrategy;
import Components.IRealTimeComponent;

import Components.SpriteComponent;
import Util.AABB;
import Util.Position2D;

import java.awt.*;


public abstract class AbstractActor extends AABB implements IRealTimeComponent
{
    /**
     * Every Actor has a drawer SpriteComponent
     */
    protected SpriteComponent sprite;

    /**
     * Holds if the actor is dead or not
     */
    private boolean isActorDead;

    protected AbstractPatrolStrategy movement;


    public AbstractActor(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        isActorDead = false;
        sprite = null;
    }

    public boolean isDead()
    {
        return isActorDead;
    }

    public void update(float deltaT)
    {
        // TODO: or delete
    }

    // Called in the
    public void update(float deltaT, Graphics2D g)
    {
        // TODO:
    }

    public void smash(AbstractActor rhs){
       if(movement != null) {
           movement.changeDirection();
       }
    }



}
