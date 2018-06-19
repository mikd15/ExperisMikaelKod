/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import static data.helpers.Artist.QuickLoad;
import static data.helpers.Artist.TILE_SIZE;

/**
 *
 * @author Karlsson
 */
public class Game {
    private TileGrid grid;
    private Player player;
    private WaveManager waveManager;
  
           
    
    //temp ver:
    
    
    public Game(TileGrid grid){
        this.grid = grid;
        
        
        waveManager = new WaveManager(new Enemy(QuickLoad("UFO64"),grid.GetTile(0, 7),grid,Artist.TILE_SIZE,Artist.TILE_SIZE,150,250),2,3);
        player= new Player(grid,waveManager);
        player.setup();
        
    }
    public void update(){
        grid.draw();
        waveManager.update();
        player.Update();
        
        
        
    }
}
