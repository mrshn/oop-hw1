package Actors;

import java.awt.*;
import Util.Position2D;


public class Enemy extends AbstractActor
{
    // TODO:

    private Enemy(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
    }

    public void update(float deltaT)
    {
        // TODO: or delete
    }

    @Override
    public boolean isDead()
    {
        // TODO:
        return false;

    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete

    }
}
