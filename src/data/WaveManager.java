/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author Karlsson
 */
public class WaveManager {
    
    private float timeSinceLastWave, timeBetweenEnemies;
    private int waveNumber, enemiesPerWave;
    private Enemy enemyType;
    private Wave currentWave;
    public WaveManager(Enemy enemyType,float timeBetweenEnemies, int enemiesPerWave){
        this.enemyType = enemyType;
        this.enemiesPerWave=enemiesPerWave;
        this.timeBetweenEnemies=timeBetweenEnemies;
        this.timeSinceLastWave=0;
        this.waveNumber=0;
        this.currentWave=null;
        
        newWave();
    }
    public void update(){
        if(!currentWave.isCompleted()){
            currentWave.update();
                    
        }else{
            this.enemyType.setHealth(this.enemyType.getHealth()*3);
            newWave();
        }
    }
    public void newWave(){
        currentWave = new Wave(enemyType,timeBetweenEnemies,enemiesPerWave);
        waveNumber++;
        
        System.out.println("Begining " +waveNumber);
        
    }
    public Wave getCurrentWave(){
        return currentWave;
    }
}
