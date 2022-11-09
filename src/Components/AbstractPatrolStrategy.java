package Components;

import Util.Position2D;

public abstract class AbstractPatrolStrategy implements IRealTimeComponent
{
    protected  Position2D<Float> currentPosition;
    private boolean goAhead;
    protected int speed;

    /**
     * Creates an Abstract Patrol Strategy
     */
    public AbstractPatrolStrategy(Position2D<Float> initialPos, int movementSpeed )
    {
        currentPosition = initialPos;
        goAhead = false;
        speed = movementSpeed;
    }

    public boolean  getIsGoAhead()
    {
        return goAhead;
    }

    public void changeDirection()
    {
        goAhead = !goAhead;
    }

    @Override
    public abstract void update(float deltaT);

}
