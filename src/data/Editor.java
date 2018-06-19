/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.helpers.Artist.*;
import static data.helpers.Maper.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Karlsson
 */
public class Editor  {
    private TileType[] types;
    private int index;
    private TileGrid grid;
    public Editor(){
        grid = new TileGrid();
        this.types = new TileType[3];
        this.types[0]= TileType.Grass;
        this.types[1]= TileType.Dirt;
        this.types[2]= TileType.Water;
        this.index=0;
    }
    public void update(){
        this.grid.draw();
        //mouse
        if(Mouse.isButtonDown(0)){
              SetTile();
        }        
        // keyboard
        while(Keyboard.next()){
            if(Keyboard.getEventKey() == Keyboard.KEY_L  && Keyboard.getEventKeyState()){
                grid = loadMap("mapTest");
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_S  && Keyboard.getEventKeyState()){
                saveMap("mapTest",grid);
            }
            if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState()){
               MoveIndex();
            }
        }
    }
    private void SetTile(){
        grid.SetTile((int)Math.floor(Mouse.getX() / 64), (int)Math.floor((HEIGHT - Mouse.getY() - 1)/64), types[index]);
    }
     private void MoveIndex(){
        index++;
        if(index>types.length - 1){
            index =0;
        }
    }
}
