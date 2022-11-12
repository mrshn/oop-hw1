package Components;

import Util.Position2D;

public class HorizontalPatrolStrategy extends AbstractPatrolStrategy
{

    public HorizontalPatrolStrategy(IRealTimeComponent source)
    {
        super(source);
    }

    @Override
    public void update(float deltaT)
    {
        float mov = deltaT * super.speed;
        if(super.getIsGoAhead()) {
            super.currentPosition.x += mov;
        } else {
            super.currentPosition.x -= mov;
        }
    }
}
