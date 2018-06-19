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
public class Checkpoint {
    private Tile tile;
    private int xDirection, yDirection;
    
    public Checkpoint(Tile tile, int xDirection, int yDirection){
        this.tile = tile;
        this.xDirection=xDirection;
        this.yDirection=yDirection;
    }

    public Tile getTile() {
        return tile;
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }
    
}
