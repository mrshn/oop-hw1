package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Constants.PathConstants;
import Util.Position2D;

public class Wall extends AbstractActor
{

    public  Wall(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent(PathConstants.WALLPATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.WALL;
    }

    public void smash(AbstractActor rightActor)
    {
        switch (rightActor.getRightActorType()) {
    /*        case BULLET:
                rightActor.setActorDead();
                break; */
            case ENEMY:
            case PLAYER:
                super.moveIfCollide(rightActor);
                break;
        }
    }

}
