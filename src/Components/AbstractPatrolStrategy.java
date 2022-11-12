package Components;

import Constants.ActorConfigurations;
import Util.Position2D;

public abstract class AbstractPatrolStrategy extends RealTimeDecorator implements IPatrolStrategy
{
    protected  Position2D<Float> currentPosition;
    private boolean goAhead;
    protected float speed;

    public AbstractPatrolStrategy(IRealTimeComponent source)
    {
        super(source);
    }

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

    public void changeDirection()
    {
        goAhead = !goAhead;
    }

    @Override
    public abstract void update(float deltaT);

}
