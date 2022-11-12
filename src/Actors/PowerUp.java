package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Constants.PathConstants;
import Util.Position2D;

public class PowerUp extends AbstractActor
{

    public PowerUp(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent(PathConstants.POWER_UP_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.POWER_UP;
    }

    @Override
    public void aCollisionIsHappened(AbstractActor collidedActor)
    {
        switch (collidedActor.getRightActorType()) {
            case PLAYER:
                super.setActorDead();
        }
    }

}
