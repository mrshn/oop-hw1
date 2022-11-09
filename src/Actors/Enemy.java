package Actors;

import java.awt.*;

import Components.AbstractPatrolStrategy;
import Components.SpriteComponent;
import Constants.CollisionActorType;
import Constants.PathConstants;
import Util.Position2D;


public class Enemy extends AbstractActor
{
    public Enemy(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        super.movement = null;
        try {
            sprite = new SpriteComponent(PathConstants.ENEMYPATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.ENEMY;
    }

    public void smash(AbstractActor rightActor)
    {
        switch (rightActor.getRightActorType()) {
            case PLAYER:
                rightActor.setActorDead();
                break;
            case WALL:
                super.movement.changeDirection();
                super.moveIfCollide(rightActor);
                break;
        }
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        if(super.movement != null) {super.movement.update(deltaT);}
        sprite.draw(g, this);
    }

    public void setMovement(AbstractPatrolStrategy rhs)
    {
        super.movement = rhs;
    }

}
