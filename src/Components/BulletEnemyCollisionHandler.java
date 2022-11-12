package Components;

import Actors.Bullet;
import Actors.Enemy;
import Actors.Wall;

import java.util.List;


public class BulletEnemyCollisionHandler  extends RealTimeDecorator
{
    BulletsInCirculationManager bulletsInCirculationManager;
    private List<Enemy> enemies;
    private List<Wall> walls;

    public BulletEnemyCollisionHandler(IRealTimeComponent source) {
        super(source);
    }

    public void initializeBulletEnemyCollision( List<Enemy> enemies,List<Bullet> bullets, List<Wall> walls){
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
                if(enemy.collides(bullet)) {
                    enemy.setActorDead(); bullet.setActorDead();
                }
            }
            for(Wall wall : this.walls) {
                if(wall.collides(bullet)) {
                    bullet.setActorDead();
                }
            }
        }
    }

}
