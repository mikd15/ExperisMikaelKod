/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import static data.helpers.Artist.TILE_SIZE;
import static data.helpers.Clock.Delta;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Karlsson
 */
public class Wave {
    private float timeSinceLastSpawn, spawnTime;
    private int enemiesPerWaves, enemiesSpawn;
    private Enemy enemyType;
    private CopyOnWriteArrayList<Enemy> enemyList;
    private boolean waveCompleted;
    
    public Wave(Enemy enemyType,float spawnTime, int enemiesPerWaves){
        this.enemyType=enemyType;
        this.spawnTime = spawnTime;
        this.enemiesPerWaves= enemiesPerWaves;
        this.enemiesSpawn=0;
        this.timeSinceLastSpawn=0;
        this.waveCompleted=false;
        this.enemyList= new CopyOnWriteArrayList<Enemy>();
        spawn();
    }
    public void update(){
        boolean allEnemisDead=true;
        if(enemiesSpawn < enemiesPerWaves){
        timeSinceLastSpawn += Delta();
        if(timeSinceLastSpawn > spawnTime){
            spawn();
            timeSinceLastSpawn=0;
            }
        }
        for(Enemy e: enemyList){
            if(e.isAlive()){
            e.update();
            e.draw();
            allEnemisDead=false;
            }else{
                enemyList.remove(e);
            }
        }
        if(allEnemisDead){
            waveCompleted=true;
            
        }
    }
    private void spawn(){
        enemyList.add(new Enemy(enemyType.getTexture(),enemyType.getStartTile(),enemyType.getTileGrid(),TILE_SIZE,TILE_SIZE,enemyType.getSpeed(),enemyType.getHealth()));
        enemiesSpawn++;
    }
    public boolean isCompleted(){
        return waveCompleted;
    }
    public CopyOnWriteArrayList<Enemy> getEnemyList(){
        return enemyList;
    }
}
