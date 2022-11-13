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
            sprite = new SpriteComponent(PathConstants.WALL_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollisionActorType getActorType()
    {
        return CollisionActorType.WALL;
    }

    @Override
    public void aCollisionIsHappened(AbstractActor collidedActor) {}

}
