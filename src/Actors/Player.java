package Actors;

import java.awt.*;

import Components.SpriteComponent;
import Util.Position2D;


public class Player extends AbstractActor
{
    SpriteComponent sprite;
    // TODO:
    public  Player(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try {
            this.sprite = new SpriteComponent("./data/img/player.png");

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

        this.getPos().x += 1;
        sprite.draw(g, this);

    }

    @Override
    public boolean isDead()
    {
        // TODO:
        // if collision happened with the enemy
        return false;

    }
}
