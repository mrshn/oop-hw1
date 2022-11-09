package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.CollisionActorType;
import Constants.PathConstants;
import Util.Position2D;


public class Player extends AbstractActor
{

    public  Player(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent(PathConstants.PLAYERPATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.PLAYER;
    }

    public void movePlayer(float moveX, float moveY)
    {
        super.getPos().x += moveX ;
        super.getPos().y += moveY ;
    }

    public void smash(AbstractActor rightActor)
    {
        switch (rightActor.getRightActorType()) {
            case ENEMY:
                super.setActorDead();
                break;
            case POWERUP:
                rightActor.setActorDead();
                break;
            case WALL:
                super.moveIfCollide(rightActor);
                break;
        }
    }

}
