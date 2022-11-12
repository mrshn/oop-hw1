package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Constants.ActorConfigurations;
import Constants.CollisionActorType;
import Constants.MovementType;
import Constants.PathConstants;
import Util.Position2D;


public class Player extends AbstractActor
{
    private MovementType currentMovementType;

    public  Player(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent(PathConstants.PLAYER_PATH);
            currentMovementType = MovementType.STOP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CollisionActorType getRightActorType()
    {
        return CollisionActorType.PLAYER;
    }

    @Override
    public void update(float deltaT) {
        float moveX = 0, moveY = 0;
        float totalMovement = deltaT * ActorConfigurations.PLAYER_SPEED;

        switch(currentMovementType) {
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
        super.moveActor(moveX, moveY);
    }
/*
    @Override
    public void update(float deltaT, Graphics2D g)
    {
        update(deltaT);
        sprite.draw(g, this);
    }
*/
    public void updatePlayerWithMovementType(MovementType lastMovementType, float deltaT) {
        currentMovementType = lastMovementType;
        update(deltaT);
    }

    @Override
    public void aCollisionIsHappened(AbstractActor collidedActor)
    {
        switch (collidedActor.getRightActorType()) {
            case ENEMY:
                super.setActorDead();
                break;
            case POWER_UP:
                collidedActor.setActorDead();
                break;
            case WALL:
                super.moveIfCollide(collidedActor);
                break;
        }
    }

}
