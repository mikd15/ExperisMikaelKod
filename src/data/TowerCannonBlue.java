/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 *
 * @author Karlsson
 */
public class TowerCannonBlue extends Tower{
    
    public TowerCannonBlue(TowerType type, Tile startTile,CopyOnWriteArrayList<Enemy> enemies){
    super(type,startTile,enemies);
}
    
    
    
}
