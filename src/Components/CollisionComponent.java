package Components;

import Actors.AbstractActor;

import java.util.ArrayList;
import java.util.List;

public class CollisionComponent implements IRealTimeComponent
{

    private final List<AbstractActor> actors = new ArrayList<>();

    private static final CollisionComponent collisionComponent = new CollisionComponent();

    private CollisionComponent(){}


    @Override
    public void update(float deltaT)
    {
        for(AbstractActor actor: actors){
            for(AbstractActor actor2: actors){
                if(!actor.equals(actor2) && !actor.isDead() && !actor2.isDead() && actor.collides(actor2)){
                    notifyCollision(actor, actor2);
                }
            }
        }
    }

    /**
     * @return Singleton Object
     */
    public static CollisionComponent getInstance(){
        return collisionComponent;
    }

    /**
     * @param actor Add subscription list
     */
    public void subscribe(AbstractActor actor){
        actors.add(actor);
    }

    /**
     * @param actor Remove from subscription list
     */
    public void unsubscribe(AbstractActor actor){
        actors.remove(actor);
    }

    /**
     * @param actor Main collided actor
     * @param actor2 Main actor collided with actor2
     */
    public void notifyCollision(AbstractActor actor, AbstractActor actor2){
        actor.smash(actor2);
    }

    /**
     * Clear all subscription list.
     */
    public void clearAll(){ actors.clear(); }


}
