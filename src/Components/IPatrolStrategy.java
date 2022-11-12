package Components;

import Util.Position2D;

public interface IPatrolStrategy
{
    /**
     * @param initialPos is the initial Position of the moving object
     * Initializes a moving strategy to the object
     **/
    public void  initialize(Position2D<Float> initialPos);

    /**
     * Reverts the direction of the moving object according to the Strategy
     **/
    public void changeDirection();

}
