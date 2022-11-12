package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.ActorConfigurations;
import Constants.CollisionActorType;
import Constants.MovementType;
import Constants.PathConstants;
import Util.Position2D;

public class Bullet extends AbstractActor
{
    private double bulletLifeTime;
    private MovementType currentBulletDirection ;

    public Bullet(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        bulletLifeTime = ActorConfigurations.BULLET_LIFE_TIME;
        currentBulletDirection = MovementType.RIGHT;
        try {
            sprite = new SpriteComponent(PathConstants.BULLET_PATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public  CollisionActorType getRightActorType()
    {
        return CollisionActorType.BULLET;
    }

    @Override
    public void update(float deltaT) {
        float moveX = 0, moveY = 0;
        float totalMovement = deltaT * ActorConfigurations.BULLET_SPEED;

        switch(currentBulletDirection) {
            case UP:
                moveY -= totalMovement;
                break;
            case DOWN:
                moveY = totalMovement;
                break;
            case LEFT:
                moveX -= totalMovement;
                break;
            case RIGHT:
                moveX = totalMovement;
        }
        super.moveActor( moveX ,  moveY);
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        if(bulletLifeTime <= 0) {
            super.setActorDead();
            return;
        }
        bulletLifeTime -= deltaT;
        update(deltaT);
        super.update(deltaT,g);
    }

    @Override
    public void aCollisionIsHappened(AbstractActor collidedActor) {}

    public void fireBulletInDirection(MovementType bulletDirection)
    {
        // if no direction is clicked, Player shoots right as default
        bulletDirection =  bulletDirection == MovementType.STOP
                ? MovementType.RIGHT : bulletDirection;
        currentBulletDirection = bulletDirection;
    }

}
