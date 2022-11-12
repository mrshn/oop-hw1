package Components;

import Constants.ActorConfigurations;
import Util.Position2D;

public abstract class AbstractPatrolStrategy extends RealTimeDecorator implements IPatrolStrategy
{
    /**
     * Identifier for the direction of the enemy is reversed or not
     */
    private boolean goAhead;
    protected  Position2D<Float> currentPosition;
    protected float speed;

    public AbstractPatrolStrategy(IRealTimeComponent source)
    {
        super(source);
    }

    @Override
    public void  initialize(Position2D<Float> initialPos)
    {
          currentPosition = initialPos;
          speed = ActorConfigurations.ENEMY_SPEED;
          goAhead = false;
    }

    public boolean  getIsGoAhead()
    {
        return goAhead;
    }

    @Override
    public void changeDirection()
    {
        goAhead = !goAhead;
    }

    @Override
    public abstract void update(float deltaT);

}
