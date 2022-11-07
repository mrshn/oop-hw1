package Components;

import Util.Position2D;

public class HorizontalPatrolStrategy extends AbstractPatrolStrategy
{

    public HorizontalPatrolStrategy(Position2D<Float> initialPos, int movementSpeed){ super(initialPos,movementSpeed); }


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
