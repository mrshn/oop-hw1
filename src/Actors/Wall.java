package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Util.Position2D;

public class Wall extends AbstractActor
{
    SpriteComponent sprite;

    public  Wall(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            this.sprite = new SpriteComponent("./data/img/wall.png");


        } catch (Exception e) {
            System.out.println("anana");
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
    }

    @Override
    public boolean isDead()
    {
        return false;
    }
}
