

## METU-Ceng443 Hw1 

Additional information about classes

- IPatrolStrategy is an interface to define common methods for Patrol Strategy classes
- BulletsInCirculationManager is a singleton class and deals with bullets in circulation
- Constants folder has some constant configurations for the game
  * ActorConfigurations are sizes, lifeTime and speeds of the actors
  * CollisionActorType has actor enums
  * MovementType has directions of the movement
  * PathConstants are the paths to the images


To compile and run on windows, I used Java 13

Compile the java code.
```bash
javac -classpath .\src\ -d .\out\ .\src\Main.java
```

Go to output directory.
```bash
cd out
```

Run the game.
```bash
java Main ..\data\map1.dat
```



