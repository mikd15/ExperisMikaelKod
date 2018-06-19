
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import org.lwjgl.input.Mouse;

import static data.helpers.Artist.*;
import data.helpers.Clock;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
/**
 *
 * @author Karlsson
 */
public class Player {
    private TileGrid grid;
    private TileType[] types;
    
    private WaveManager waveManager;
    private ArrayList<Tower> towerList;
    private boolean leftMouseButtonDown1, leftMouseButtonDown2;
    public static int Gold,Life;
    
    public Player(TileGrid grid, WaveManager waveManager){
        this.grid = grid;
        this.types = new TileType[3];
        this.types[0]= TileType.Grass;
        this.types[1]= TileType.Dirt;
        this.types[2]= TileType.Water;
        
        this.waveManager = waveManager;
        this.towerList = new ArrayList<Tower>();
        this.leftMouseButtonDown1=false;
        this.leftMouseButtonDown2=false;
        Life=0;
        Gold=0;
    }
    public void setup(){
        Life=10;
        Gold=10;
    }
    public static boolean modGold(int amount){
        if(Gold + amount >=0){
            Gold += amount;
            return true;
        }
        else{
            return false;
        }
    }
    public static void modLife(int a){
    Life +=a;
    }
    public void Update(){
        for(Tower t : towerList){
            t.update();
            t.draw();
            t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
        }
            
        //mouse
        if(Mouse.isButtonDown(0) && !leftMouseButtonDown1){
            if(modGold(-10)){ 
                System.out.println(Gold);
            towerList.add(new TowerCannonBlue(TowerType.CannonBlue,grid.GetTile(Mouse.getX() / Artist.TILE_SIZE, (HEIGHT - Mouse.getY() - 1)/Artist.TILE_SIZE),waveManager.getCurrentWave().getEnemyList()));
            }
        }
        else if(Mouse.isButtonDown(1) && !leftMouseButtonDown2){
            if(modGold(-55)){
             towerList.add(new TowerIce(TowerType.CannonIce,grid.GetTile(Mouse.getX() / Artist.TILE_SIZE, (HEIGHT - Mouse.getY() - 1)/Artist.TILE_SIZE),waveManager.getCurrentWave().getEnemyList()));
            }
        }
        leftMouseButtonDown1 = Mouse.isButtonDown(0);
        leftMouseButtonDown2 = Mouse.isButtonDown(1);
        // keyboard
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT  && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(0.2f);
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_LEFT  && Keyboard.getEventKeyState()){
                Clock.ChangeMultiplier(-0.2f);
            }
            
        }
    }
   
}
