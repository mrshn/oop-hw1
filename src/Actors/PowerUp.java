package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Util.Position2D;


public class PowerUp extends AbstractActor
{
    // TODO:

    public PowerUp(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent("./data/img/scroll.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.POWERUP;
    }


    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO or delete
    }

    @Override
    public boolean isDead()
    {
        // TODO:
        return false;
    }

}
