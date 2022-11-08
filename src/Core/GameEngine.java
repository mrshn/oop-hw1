package Core;

import Actors.*;
import Components.*;
import Util.AABB;
import Util.GameMapLoader;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameEngine
{
    private final Dimension screenSize;
    private final String currentMap;
    // Game Objects
    private Player player;
    // Concrete Types of the game
    private ArrayList<Wall> walls;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Bullet> bulletsInCirculation;
    // Add extra components if you like
    private ArrayList<IRealTimeComponent> miscComponents;


    private void subsCollisions()
    {
        CollisionComponent collisionInstance =  CollisionComponent.getInstance();
        collisionInstance.clearAll();
        collisionInstance.subscribe(player);
        enemies.forEach( eachEnemy -> collisionInstance.subscribe(eachEnemy) );
        walls.forEach( eachWall -> collisionInstance.subscribe(eachWall) );
        powerUps.forEach( eachPowerUp -> collisionInstance.subscribe(eachPowerUp) );

        this.miscComponents.add(collisionInstance);
    }

    private void setReadValues(GameMapLoader map )
    {

        map.getLoadedWallAABBs().forEach( eachWall ->  this.walls.add(  new Wall(eachWall.getPos(),eachWall.getSizeX(),eachWall.getSizeY()) ) );
        map.getLoadedEnemyStationaryAABBs().forEach( eachEnemy ->  this.enemies.add(  new Enemy(eachEnemy.getPos(),eachEnemy.getSizeX(),eachEnemy.getSizeY()) ) );

        map.getLoadedEnemyXAABBs().forEach(
                eachEnemy ->
                        {
                            Enemy enemy = new Enemy(eachEnemy.getPos(), eachEnemy.getSizeX(), eachEnemy.getSizeY());
                            enemy.setMovement( new HorizontalPatrolStrategy(eachEnemy.getPos(),120) );
                            this.enemies.add(enemy);
                        }
        );

        map.getLoadedEnemyYAABBs().forEach(
                eachEnemy ->
                        {
                            Enemy enemy = new Enemy(eachEnemy.getPos(), eachEnemy.getSizeX(), eachEnemy.getSizeY());
                            enemy.setMovement( new VerticalPatrolStrategy(eachEnemy.getPos(),120) );
                            this.enemies.add(enemy);
                        }
        );
        map.getLoadedPowerUpAABBs().forEach( eachPowerUp ->  this.powerUps.add(  new PowerUp(eachPowerUp.getPos(),eachPowerUp.getSizeX(),eachPowerUp.getSizeY()) ) );
        map.getLoadedWallAABBs().forEach( eachBullet ->  this.bulletsInCirculation.add(  new Bullet(eachBullet.getPos(),eachBullet.getSizeX(),eachBullet.getSizeY()) ) );

        AABB AABBplayer = map.getLoadedPlayerAABB();
        this.player = new Player(AABBplayer.getPos(),AABBplayer.getSizeX(),AABBplayer.getSizeY());
        PlayerInputComponent playerInputInstance = PlayerInputComponent.getPlayerInputComponentInstance();
        playerInputInstance.setPlayer(this.player);

        subsCollisions();
        this.miscComponents.add(playerInputInstance);
        GameWindow.GetInstance().attachKeyListener(playerInputInstance);


    }

    private void ResetGame()
    {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();
        miscComponents.clear(); // TODO: check

        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);

        setReadValues(map);

        if(!mapOK)
        {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

        // TODO: Add code if your design requires so

    }

    public GameEngine(String mapFilePath, Dimension screenSize)
    {
        this.currentMap = mapFilePath;
        this.screenSize = screenSize;

        this.walls = new ArrayList<Wall>();
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUp>();
        this.bulletsInCirculation = new ArrayList<Bullet>();
        this.miscComponents = new ArrayList<IRealTimeComponent>();

        // TODO: Add code if your design requires so

        ResetGame();
    }

    public synchronized void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        // ==================================== //
        // YOU SHOULD NOT CHANGE THIS FUNCTION  //
        // ============================================= //
        // THIS SHOULD ALREADY DOES EVERYTHING YOU NEED  //
        // ============================================= //
        // You can still change it though with a penalty.

        // Do update first
        walls.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        enemies.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        powerUps.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        bulletsInCirculation.forEach(actor-> actor.update(deltaT, currentDrawBuffer));
        player.update(deltaT, currentDrawBuffer);
        miscComponents.forEach(c -> c.update(deltaT));
        // Now stuff would die etc. check the states and delete
        enemies.removeIf(actor -> actor.isDead());
        powerUps.removeIf(actor -> actor.isDead());
        bulletsInCirculation.removeIf(actor -> actor.isDead());
        // If player dies game is over reset the system
        if(player.isDead())
        {
            ResetGame();
        }
        // If there are no power-ups left,
        // Player won the game!, still reset..
        if(powerUps.isEmpty())
        {
            ResetGame();
        }
        // And the game goes on forever...
    }
}
