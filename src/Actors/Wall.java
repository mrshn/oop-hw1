package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Util.Position2D;

public class Wall extends AbstractActor
{

    public  Wall(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            // TODO: make the paths constant
            sprite = new SpriteComponent("./data/img/wall.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(float deltaT)
    {
        // TODO: or delete
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO: or delete
        sprite.draw(g, this);
    }

    @Override
    public boolean isDead()
    {
        return false;
    }
}
