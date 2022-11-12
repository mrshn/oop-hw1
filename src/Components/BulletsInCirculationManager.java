package Components;

import Actors.Bullet;

import java.util.ArrayList;

public class BulletsInCirculationManager {

    private ArrayList<Bullet> bullets;

    private static final BulletsInCirculationManager instance = new BulletsInCirculationManager();
    private BulletsInCirculationManager() {}
    public static BulletsInCirculationManager GetInstance() { return instance; };

    public void initialize(ArrayList<Bullet> gameBullets)
    {
        bullets =  gameBullets;
    }

    public void addBullet(Bullet bullet)
    {
        this.bullets.add(bullet);
    }

    public void clearBullets()
    {
        if(this.bullets != null) {
            this.bullets.clear();
        }
    }

    ArrayList<Bullet> getBullets()
    {
        return this.bullets;
    }
}
