package Actors;

import java.awt.*;
import Util.Position2D;

public class Bullet extends AbstractActor
{
    // TODO:

    private Bullet(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
    }

    @Override
    public void update(float deltaT)
    {
        // TODO: or delete
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
    }

    @Override
    public boolean isDead()
    {
        // TODO:
        return false;
    }

}
