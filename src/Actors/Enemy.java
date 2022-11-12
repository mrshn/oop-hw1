package Actors;

import java.awt.*;

import Components.AbstractPatrolStrategy;
import Components.SpriteComponent;
import Constants.CollisionActorType;
import Constants.PathConstants;
import Util.Position2D;


public class Enemy extends AbstractActor
{
    /**
     * Identifier for the Patrol Strategy Enemy has
     */
    protected AbstractPatrolStrategy movement;

    public Enemy(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        movement = null;
        try {
            sprite = new SpriteComponent(PathConstants.ENEMY_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollisionActorType getActorType()
    {
        return CollisionActorType.ENEMY;
    }

    @Override
    public void update(float deltaT) {
        if(movement != null) {
            movement.update(deltaT);
        }
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        update(deltaT);
        sprite.draw(g, this);
    }

    @Override
    public void aCollisionIsHappened(AbstractActor collidedActor)
    {
        switch (collidedActor.getActorType()) {
            case PLAYER:
                collidedActor.setActorDead();
                break;
            case WALL:
                movement.changeDirection();
                super.moveIfCollide(collidedActor);
                break;
        }
    }

    public void setMovement(AbstractPatrolStrategy rhs)
    {
        movement = rhs;
    }

}
