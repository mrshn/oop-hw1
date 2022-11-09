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
            sprite = new SpriteComponent(PathConstants.BULLETPATH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fireBulletInDirection(MovementType bulletDirection)
    {
        // if no direction is clicked, Player shoots right as default
        bulletDirection =  bulletDirection == MovementType.STOP
                ? MovementType.RIGHT : bulletDirection;
        currentBulletDirection = bulletDirection;
    }

    public void moveBullet(float moveX, float moveY)
    {
        super.getPos().x += moveX ;
        super.getPos().y += moveY ;
    }

    public void update(float deltaT, Graphics2D g)
    {
        if(bulletLifeTime <= 0) {
            super.setActorDead();
        }
        bulletLifeTime -= deltaT;

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
        moveBullet( moveX,  moveY);
        super.update(deltaT,g);
    }

    public  CollisionActorType getRightActorType()
    {
        return CollisionActorType.BULLET;
    }

    public void smash(AbstractActor rightActor)
    {

    }

}
