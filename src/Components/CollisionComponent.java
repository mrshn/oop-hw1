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
        for(AbstractActor leftActor: allActors) {
            for(AbstractActor rightActor: allActors) {
                if(!leftActor.equals(rightActor) && !leftActor.isDead() && !rightActor.isDead() && leftActor.collides(rightActor)){
                    notifyCollision(leftActor, rightActor);
                }
            }
        }
    }

    public static CollisionComponent GetInstance()
    {
        return collisionComponent;
    }

    public void subscribe(AbstractActor actor){
        allActors.add(actor);
    }


    public void unsubscribe(AbstractActor actor){
        allActors.remove(actor);
    }


    public void notifyCollision(AbstractActor leftActor, AbstractActor rightActor){
        leftActor.aCollisionIsHappened(rightActor);
    }

    public void clearAll()
    {
        allActors.clear();
    }

}
