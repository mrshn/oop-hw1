package Components;

import Actors.Bullet;
import Actors.Player;
import Constants.MovementType;
import Util.Position2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayerInputComponent implements IRealTimeComponent, KeyListener
{
    // Internal States
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean downPressed;
    private boolean firePressed;
    private Player player;
    private MovementType currentMovementType;

    private static final PlayerInputComponent playerInputComponent = new PlayerInputComponent();

    private PlayerInputComponent()
    {
        currentMovementType = MovementType.STOP;

        leftPressed = false;
        rightPressed = false;
        upPressed = false;
        downPressed = false;
        firePressed = false;
    }

    /**
     * @return
     */
    public static PlayerInputComponent getPlayerInputComponentInstance()
    {
        return playerInputComponent;
    }

    /**
     * @param
     */
    public void setPlayer(Player rhs) {
        this.player = rhs;
    }

    @Override
    public void update(float deltaT)
    {
        float moveX = 0, moveY = 0;
        switch(currentMovementType) {
            case UP:
                moveY -= 110 * deltaT;
                break;
            case DOWN:
                moveY = 110 * deltaT;
                break;
            case LEFT:
                moveX -= 110 * deltaT;
                break;
            case RIGHT:
                moveX = 110 * deltaT;
        }
        if(firePressed) {
            Bullet bullet = new Bullet(new Position2D<Float>(player.getPos().x, player.getPos().y), player.getSizeX(), player.getSizeY());
            //bullet.setDirection(currentMovementType);
            //BulletCollisionHandler.getInstance().addBullet(bullet);
        }
/*
        float moveX = (rightPressed)
                ? 110 * deltaT
                : (leftPressed) ? (-110) * deltaT : 0;
        float moveY = (upPressed)
                ? 110 * deltaT
                : (downPressed) ? (-110) * deltaT : 0;
*/

        player.movePlayer(moveX, moveY);
    }

    @Override
    public void keyTyped(KeyEvent e) { /* Do nothing */ }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftPressed = true;
            currentMovementType = MovementType.LEFT;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
            currentMovementType = MovementType.RIGHT;
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            upPressed = true;
            currentMovementType = MovementType.UP;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downPressed = true;
            currentMovementType = MovementType.DOWN;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            firePressed = true;
        }
    }

    // if user double clicks, this function makes sure
    // Player goes back to previous movement direction
    // when last click is released
    public void checkDoubleClick()
    {
        if(rightPressed) {
            currentMovementType = MovementType.RIGHT;
        } else if(leftPressed) {
            currentMovementType = MovementType.LEFT;
        } else if(upPressed) {
            currentMovementType = MovementType.UP;
        } else if(downPressed) {
            currentMovementType = MovementType.DOWN;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftPressed = false;
            currentMovementType = currentMovementType == MovementType.LEFT
                    ? MovementType.STOP
                    : currentMovementType;
            checkDoubleClick();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightPressed = false;
            currentMovementType = currentMovementType == MovementType.RIGHT
                    ? MovementType.STOP
                    : currentMovementType;
            checkDoubleClick();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            upPressed = false;
            currentMovementType = currentMovementType == MovementType.UP
                    ? MovementType.STOP
                    : currentMovementType;
            checkDoubleClick();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downPressed = false;
            currentMovementType = currentMovementType == MovementType.DOWN
                    ? MovementType.STOP
                    : currentMovementType;
            checkDoubleClick();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            firePressed = false;
        }
    }
}
