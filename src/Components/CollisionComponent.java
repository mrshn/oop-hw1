package Components;

import Actors.AbstractActor;

import java.util.ArrayList;
import java.util.List;

public class CollisionComponent extends RealTimeDecorator
{
    /**
     * All actors can collide with each other and currently subscribed
     */
    private final List<AbstractActor> allActors = new ArrayList<>();

    public CollisionComponent(IRealTimeComponent source)
    {
        super(source);
    }

    @Override
    public void update(float deltaT)
    {
        for(AbstractActor leftActor: new ArrayList<>(allActors)) {
            if(leftActor.isDead()) {
                // unsubscribe it from the allActors when an actor is dead
                unsubscribe(leftActor);
                continue;
            }
            for(AbstractActor rightActor: allActors) {
                if(!leftActor.equals(rightActor) && !leftActor.isDead() && !rightActor.isDead() && leftActor.collides(rightActor)) {
                    notify(leftActor, rightActor);
                }
            }
        }
    }

    /**
     * adds to allActors (subscription list)
     */
    public void subscribe(AbstractActor actor)
    {
        allActors.add(actor);
    }

    /**
     * removes from allActors (subscription list)
     */
    public void unsubscribe(AbstractActor actor)
    {
        allActors.remove(actor);
    }

    /**
     * @param leftActor is notified about a collision with rightActor
     */
    public void notify(AbstractActor leftActor, AbstractActor rightActor)
    {
        leftActor.aCollisionIsHappened(rightActor);
    }

    public void clearAll()
    {
        allActors.clear();
    }

}
