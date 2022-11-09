package Components;

import Actors.Bullet;
import Actors.Enemy;
import Actors.Wall;

import java.util.List;


public class BulletEnemyCollisionHandler implements IRealTimeComponent
{
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Wall> walls;

    private BulletEnemyCollisionHandler() {}

    private static final BulletEnemyCollisionHandler handler = new BulletEnemyCollisionHandler();

    public void initializeBulletEnemyCollision( List<Enemy> enemies,List<Bullet> bullets, List<Wall> walls){
        this.enemies = enemies;
        this.bullets = bullets;
        this.walls = walls;
    }

    public static BulletEnemyCollisionHandler GetInstance()
    {
        return handler;
    }

    public void addBullet(Bullet bullet)
    {
        this.bullets.add(bullet);
    }

    @Override
    public void update(float deltaT)
    {
        for(Bullet bullet : this.bullets) {
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
