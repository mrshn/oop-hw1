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

    // Used to create new player
    private Player getNewPlayerFromMap(GameMapLoader map)
    {
        AABB AABBplayer = map.getLoadedPlayerAABB();
        return new Player(AABBplayer.getPos(),AABBplayer.getSizeX(),AABBplayer.getSizeY());
    }

    private void setCollisions(GameMapLoader map )
    {
        CollisionComponent collisionInstance = new CollisionComponent( getNewPlayerFromMap(map) );
        collisionInstance.clearAll();
        collisionInstance.subscribe(player);
        enemies.forEach( eachEnemy -> collisionInstance.subscribe(eachEnemy) );
        walls.forEach( eachWall -> collisionInstance.subscribe(eachWall) );
        powerUps.forEach( eachPowerUp -> collisionInstance.subscribe(eachPowerUp) );

        this.miscComponents.add(collisionInstance);
    }

    private void setEnemies(GameMapLoader map)
    {
        map.getLoadedEnemyStationaryAABBs().forEach( eachEnemy ->  this.enemies.add(  new Enemy(eachEnemy.getPos(),eachEnemy.getSizeX(),eachEnemy.getSizeY()) ) );
        map.getLoadedEnemyXAABBs().forEach(
                eachEnemy ->
                {
                    Enemy enemy = new Enemy(eachEnemy.getPos(), eachEnemy.getSizeX(), eachEnemy.getSizeY());

                    HorizontalPatrolStrategy horizontalPatrolStrategy =  new HorizontalPatrolStrategy( getNewPlayerFromMap(map) );
                    horizontalPatrolStrategy.initialize(eachEnemy.getPos());
                    enemy.setMovement(horizontalPatrolStrategy );

                    enemy.setMovement( horizontalPatrolStrategy);
                    this.enemies.add(enemy);
                }
        );
        map.getLoadedEnemyYAABBs().forEach(
                eachEnemy ->
                {
                    Enemy enemy = new Enemy(eachEnemy.getPos(), eachEnemy.getSizeX(), eachEnemy.getSizeY());
                    VerticalPatrolStrategy verticalPatrolStrategy =  new VerticalPatrolStrategy( getNewPlayerFromMap(map) );
                    verticalPatrolStrategy.initialize(eachEnemy.getPos());
                    enemy.setMovement(verticalPatrolStrategy );
                    this.enemies.add(enemy);
                }
        );
    }

    private void setPlayerInput(GameMapLoader map)
    {
        PlayerInputComponent playerInputInstance = new PlayerInputComponent( getNewPlayerFromMap(map) );
        playerInputInstance.defaultConfiguration();
        playerInputInstance.setPlayer( getNewPlayerFromMap(map) );
        GameWindow.GetInstance().attachKeyListener(playerInputInstance);
        this.miscComponents.add(playerInputInstance);

    }

    private void setBulletEnemyCollisions(GameMapLoader map)
    {

        BulletEnemyCollisionHandler  bulletEnemyCollisionInstance = new BulletEnemyCollisionHandler( getNewPlayerFromMap(map) );
        bulletEnemyCollisionInstance.initialize( this.enemies, this.walls);
        this.miscComponents.add(bulletEnemyCollisionInstance);
    }

    private void setReadValues(GameMapLoader map )
    {
        this.player = getNewPlayerFromMap(map);

        map.getLoadedWallAABBs().forEach( eachWall ->  this.walls.add(  new Wall(eachWall.getPos(),eachWall.getSizeX(),eachWall.getSizeY()) ) );
        map.getLoadedPowerUpAABBs().forEach( eachPowerUp ->  this.powerUps.add(  new PowerUp(eachPowerUp.getPos(),eachPowerUp.getSizeX(),eachPowerUp.getSizeY()) ) );

        //Initialize singleton class for bullets in circulation
        BulletsInCirculationManager.GetInstance().initialize(this.bulletsInCirculation);

        setEnemies(map);
        setPlayerInput(map);
        setCollisions(map);
        setBulletEnemyCollisions(map);
    }

    private void ResetGame()
    {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();
        miscComponents.clear();

        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);

        setReadValues(map);

        if(!mapOK)
        {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

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
