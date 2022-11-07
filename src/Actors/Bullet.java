package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Util.Position2D;

public class Bullet extends AbstractActor
{
    // TODO:

    public Bullet(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            sprite = new SpriteComponent("./data/img/bullet.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
