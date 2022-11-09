package Actors;

import Components.AbstractPatrolStrategy;
import Components.IRealTimeComponent;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Util.AABB;
import Util.Position2D;

import java.awt.*;

public abstract class AbstractActor extends AABB implements IRealTimeComponent
{
    protected SpriteComponent sprite;
    protected AbstractPatrolStrategy movement;
    private boolean isActorDead;

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

    public void setActorDead()
    {
        isActorDead = true;
    }

    public void update(float deltaT)
    {
    }

    public void update(float deltaT, Graphics2D g)
    {
        sprite.draw(g, this);
    }

    public abstract void smash(AbstractActor rightActor);

    public abstract CollisionActorType getRightActorType();

}
