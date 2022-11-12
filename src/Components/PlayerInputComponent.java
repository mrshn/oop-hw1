package Components;

import Actors.Bullet;
import Actors.Player;
import Constants.ActorConfigurations;
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
    private MovementType lastPressedDirection;

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

    public static PlayerInputComponent GetInstance()
    {
        return playerInputComponent;
    }

    public void setPlayer(Player rhs) {
        this.player = rhs;
    }

    public void checlIfBulletIsFired(){
        if(firePressed) {
            firePressed = false;
            Bullet bullet = new Bullet(new Position2D<Float>(player.getPos().x, player.getPos().y), player.getSizeX(), player.getSizeY());
            bullet.fireBulletInDirection( currentMovementType ==MovementType.STOP ? lastPressedDirection :currentMovementType  );
            BulletEnemyCollisionHandler.GetInstance().addBullet(bullet);
        }
    }

    @Override
    public void update(float deltaT)
    {
        player.updatePlayerWithMovementType(currentMovementType,deltaT);
        checlIfBulletIsFired();
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
            lastPressedDirection = MovementType.LEFT;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
            currentMovementType = MovementType.RIGHT;
            lastPressedDirection = MovementType.RIGHT;

        }
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            upPressed = true;
            currentMovementType = MovementType.UP;
            lastPressedDirection = MovementType.UP;

        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downPressed = true;
            currentMovementType = MovementType.DOWN;
            lastPressedDirection = MovementType.DOWN;
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
    }
}
