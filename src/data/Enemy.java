/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.helpers.Artist.*;
import static data.helpers.Clock.*;
import java.util.ArrayList;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Karlsson
 */
public class Enemy implements Entity{
    private int width,height,currentCheckpoint;
    private float speed,x,y,health,starthealth;
    private Texture texture, hpbg, hpfg,hpb;
    private Tile startTile;
    private boolean first = true, alive =true;
    private TileGrid grid;
    private ArrayList<Checkpoint>checkpoints;
    private int[] directions;
    public Enemy(Texture texture, Tile startTile,TileGrid grid,int width,int height,float speed, float health){
        this.texture = texture;
        this.hpbg=QuickLoad("hpbg");
        this.hpfg=QuickLoad("hpfg");
        this.hpb=QuickLoad("hpb");
        this.startTile=startTile;
        this.x=startTile.getX();
        this.y=startTile.getY();
        this.width=width;
        this.height=height;
        this.health=health;
        this.starthealth=health;
        this.speed=speed;
        this.grid = grid;
        this.checkpoints = new ArrayList<Checkpoint>();
        this.directions = new int[2];
        this.directions[0]=0;
        this.directions[1]=0;
        directions = findNextD(startTile);
        this.currentCheckpoint=0;
        populateCheckpointList();
     }
    public void update(){
        if(first){
            first=false;            
        }else{
            if(checkpoiuntReached()){
                if(currentCheckpoint+1==checkpoints.size()){
                    endReached();
                }else{
                currentCheckpoint++;
                }
            }else{
                x+= Delta() *checkpoints.get(currentCheckpoint).getxDirection()* speed;
                y+= Delta() *checkpoints.get(currentCheckpoint).getyDirection()* speed;
            }
            
        }
    }
    private boolean checkpoiuntReached(){
        boolean reached=false;
        
        Tile t = checkpoints.get(currentCheckpoint).getTile();
        if(x > t.getX() -3 && x < t.getX() +3 && y > t.getY() -3 && y < t.getY() +3){
            reached = true;
            x=t.getX();
            y=t.getY();
        }        
        return reached;
    }
    private void populateCheckpointList(){
        checkpoints.add(findNextC(startTile,directions=findNextD(startTile)));
        int counter=0;
        boolean cont = true;
        while(cont){
            int[] currentD = findNextD(checkpoints.get(counter).getTile());
            if(currentD[0]==2 || counter == 20){
                cont =false;
            }else{
                checkpoints.add(findNextC(checkpoints.get(counter).getTile(),directions=findNextD(checkpoints.get(counter).getTile())));
            }
            counter++;
        }
    }
    private Checkpoint findNextC(Tile s, int [] dir){
        Tile next =null;
        Checkpoint c = null;
        
        boolean found = false;
        int counter =1;
        
        while(!found){
            if(s.getXplace()+ dir[0]* counter == grid.getTilesWide() || s.getYplace() + dir[1] *counter == grid.getTilesHigh() || s.getType() != grid.GetTile(s.getXplace()+ dir[0]* counter, s.getYplace() + dir[1] *counter).getType()){
                found=true;
                counter-=1;
                next = grid.GetTile(s.getXplace()+ dir[0]* counter, s.getYplace() + dir[1] *counter);
            }
            
            counter++;
        }
        c = new Checkpoint(next,dir[0],dir[1]);
        return c;
    }
    private int[] findNextD(Tile s){
        int [] dir = new int[2];
        Tile u = grid.GetTile(s.getXplace(), s.getYplace()-1);
        Tile r = grid.GetTile(s.getXplace()+1, s.getYplace());
        Tile d = grid.GetTile(s.getXplace(), s.getYplace()+1);
        Tile l = grid.GetTile(s.getXplace()-1, s.getYplace());
        
        if(s.getType()==u.getType()&& directions[1]!=1){
            dir[0]=0;
            dir[1]= -1;
        }else if(s.getType()==r.getType()&& directions[0]!=-1){
            dir[0]=1;
            dir[1]=0;
        }else if(s.getType()==d.getType()&& directions[1]!=-1){
            dir[0]=0;
            dir[1]=1;
        }else if(s.getType()==l.getType()&& directions[0]!=1){
            dir[0]=-1;
            dir[1]=0;
        }else{
            dir[0] =2;
            dir[1] =2;
        }
        return dir;
    }
    public void damage(int amount){
        health -= amount;
        if(health <=0){
            Die();
            Player.modGold(5);
            
        }
    }
    private void Die(){
        alive=false;
        
        
    }
    private void endReached(){
        Player.modLife(-1);
        Die();
    }
    public void draw(){
        float hpPer = health / starthealth;
        DrawQuadTex(texture,x,y,width,height);
        DrawQuadTex(hpbg,x,y - TILE_SIZE/8,width,TILE_SIZE/8);
        DrawQuadTex(hpfg,x,y - TILE_SIZE/8,TILE_SIZE *hpPer,TILE_SIZE/8);
        DrawQuadTex(hpb,x,y- TILE_SIZE/8,width,TILE_SIZE/8);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getHealth() {
        return health;
    }

    public float getSpeed() {
        return speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public boolean isFirst() {
        return first;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
    public TileGrid getTileGrid(){
        return grid;
    }
    public boolean isAlive(){
        return alive;
    }
}
