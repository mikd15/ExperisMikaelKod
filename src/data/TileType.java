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
public enum TileType {
    
    Grass("grass64",true),Dirt("dirt64",false),Water("water64",false),NULL("water64",false);
    
    String textureName;
    boolean buildable;
    TileType(String textureName, boolean buildable){
    this.textureName = textureName;
    this.buildable = buildable;
}
}
