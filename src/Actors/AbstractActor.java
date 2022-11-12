package Actors;

import Components.AbstractPatrolStrategy;
import Components.ICollisionListener;
import Components.IRealTimeComponent;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Util.AABB;
import Util.Position2D;

import java.awt.*;

public abstract class AbstractActor extends AABB implements IRealTimeComponent, ICollisionListener
{
    protected SpriteComponent sprite;
    private boolean isActorDead;

    public AbstractActor(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        isActorDead = false;
        sprite = null;
    }

    /**
     * Returs the type of the actor
     **/
    public abstract CollisionActorType getRightActorType();

    /**
     * Overrided according to the Actors movement behaviour
     * @param deltaT is the time passed since last update
     **/
    public void update(float deltaT) {}

    /**
     * Overrided according to the Actors movement behaviour
     * @param g is used for sprite
     * @param deltaT is the time passed since last update
     **/
    public void update(float deltaT, Graphics2D g)
    {
        sprite.draw(g, this);
    }

    /**
     * Simply updates the position of the AABB
     **/
    public void moveActor(float moveX, float moveY)
    {
        super.getPos().x += moveX ;
        super.getPos().y += moveY ;
    }

    /**
     * Returns if actor is dead or not
     **/
    public boolean isDead()
    {
        return isActorDead;
    }

    /**
     * Called when actor is dead
     **/
    public void setActorDead()
    {
        isActorDead = true;
    }

    /**
     * Called when an AABB collides other
     * @param collidedActor is the collided actor
     **/
    public abstract void aCollisionIsHappened(AbstractActor collidedActor);

}
