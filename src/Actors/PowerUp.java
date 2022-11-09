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
            sprite = new SpriteComponent(PathConstants.POWERUPPATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.POWERUP;
    }

    public void smash(AbstractActor rightActor)
    {
        switch (rightActor.getRightActorType()) {
            case PLAYER:
                super.setActorDead();
        }
    }

}
