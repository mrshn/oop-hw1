package Components;

import Actors.AbstractActor;
import Constants.CollisionActorType;

public interface ICollisionListener
{
    /**
     * Notify AbstractActor when a collision is detected
     * @param collidedActor is the other AbstractActor collided
     */
    public void aCollisionIsHappened(AbstractActor collidedActor);

    /**
     * Returns the type of the actor
     */
    public CollisionActorType getActorType();
    
}
