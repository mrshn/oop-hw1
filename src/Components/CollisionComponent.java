package Components;

import Actors.AbstractActor;

import java.util.ArrayList;
import java.util.List;

public class CollisionComponent implements IRealTimeComponent
{

    private final List<AbstractActor> allActors = new ArrayList<>();

    private static final CollisionComponent collisionComponent = new CollisionComponent();

    private CollisionComponent(){}

    @Override
    public void update(float deltaT)
    {
        for(AbstractActor leftActor: allActors){
            for(AbstractActor rightActor: allActors){
                if(!leftActor.equals(rightActor) && !leftActor.isDead() && !rightActor.isDead() && leftActor.collides(rightActor)){
                    notifyCollision(leftActor, rightActor);
                }
            }
        }
    }

    /**
     * @return Singleton Object
     */
    public static CollisionComponent getInstance()
    {
        return collisionComponent;
    }

    /**
     * @param
     */
    public void subscribe(AbstractActor actor){
        allActors.add(actor);
    }

    /**
     * @param
     */
    public void unsubscribe(AbstractActor actor){
        allActors.remove(actor);
    }

    /**
     * @param
     * @param
     */
    public void notifyCollision(AbstractActor leftActor, AbstractActor rightActor){
        leftActor.smash(rightActor);
    }

    /**
     *
     */
    public void clearAll()
    {
        allActors.clear();
    }


}
