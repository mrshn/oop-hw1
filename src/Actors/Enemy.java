package Actors;

import java.awt.*;

import Components.AbstractPatrolStrategy;
import Components.SpriteComponent;
import Util.Position2D;


public class Enemy extends AbstractActor
{
    // TODO:


    public Enemy(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        super.movement = null;
        try {
            sprite = new SpriteComponent("./data/img/enemy.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        if(super.movement != null) {super.movement.update(deltaT);}

        sprite.draw(g, this);

    }

    public void setMovement(AbstractPatrolStrategy rhs)
    {
        super.movement = rhs;
    }

}
