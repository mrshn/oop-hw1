package Components;

import Util.Position2D;

public class VerticalPatrolStrategy extends AbstractPatrolStrategy
{

    public VerticalPatrolStrategy(IRealTimeComponent source)
    {
        super(source);
    }

    @Override
    public void update(float deltaT)
    {
        float mov = deltaT * super.speed;
        if(super.getIsGoAhead()) {
            super.currentPosition.y += mov;
        } else {
            super.currentPosition.y -= mov;
        }
    }
}
