package Actors;

import Components.IRealTimeComponent;

import Util.AABB;
import Util.Position2D;

import java.awt.*;

// Meta Actor Class
// Everything in the game is an actor
public abstract class AbstractActor extends AABB implements IRealTimeComponent
{
    // TODO:

    public AbstractActor(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
    }

    public void update(float deltaT)
    {
        // TODO: or delete
    }

    public void update(float deltaT, Graphics2D g)
    {
        // TODO:
    }

    public abstract boolean isDead();
}
