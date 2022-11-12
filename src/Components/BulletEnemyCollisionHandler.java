package Components;

import Actors.Bullet;
import Actors.Enemy;
import Actors.Wall;

import java.util.List;


public class BulletEnemyCollisionHandler  extends RealTimeDecorator
{
    /**
     * An Aggregation relationship with a singleton class which manages bullets in circulation
     */
    BulletsInCirculationManager bulletsInCirculationManager;

    private List<Enemy> enemies;
    private List<Wall> walls;

    public BulletEnemyCollisionHandler(IRealTimeComponent source) {
        super(source);
    }

    public void initialize( List<Enemy> enemies, List<Wall> walls)
    {
        bulletsInCirculationManager = BulletsInCirculationManager.GetInstance();
        bulletsInCirculationManager.clearBullets();

        this.enemies = enemies;
        this.walls = walls;
    }

    @Override
    public void update(float deltaT)
    {
        for(Bullet bullet : this.bulletsInCirculationManager.getBullets()) {
            for(Enemy enemy : this.enemies) {
                if(bullet.collides(enemy)) {
                    bullet.setActorDead();
                    enemy.setActorDead();
                    break;
                }
            }
            for(Wall wall : this.walls) {
                if(bullet.collides(wall)) {
                    bullet.setActorDead();
                    break;
                }
            }
        }
    }

}
