/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import org.newdawn.slick.opengl.Texture;
import static data.helpers.Artist.*;

/**
 *
 * @author Karlsson
 */
public class Tile {
    
    private float x,y;
    private int width, height;
    private Texture texture;
    private TileType type;
    
    public Tile(float x, float y, int width, int height, TileType type){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.type = type;
        this.texture=QuickLoad(type.textureName);
    }
    public void draw(){
        DrawQuadTex(texture,x,y,width,height);
    }
    public float getX() {
        return x;
    }
    public int getXplace(){
        return (int) x/TILE_SIZE;
    }

    public float getY() {
        return y;
    }
    public int getYplace(){
        return (int) y/TILE_SIZE;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Texture getTexture() {
        return texture;
    }

    public TileType getType() {
        return type;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setType(TileType type) {
        this.type = type;
    }
    
    
}
