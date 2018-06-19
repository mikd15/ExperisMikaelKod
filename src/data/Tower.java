/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static data.helpers.Artist.DrawQuadTex;
import static data.helpers.Artist.DrawQuadTexRot;
import static data.helpers.Artist.QuickLoad;
import static data.helpers.Artist.TILE_SIZE;
import static data.helpers.Clock.Delta;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.opengl.Texture;

public abstract class Tower implements Entity{
    private float x,y,timeSinceLastShot,fireSpeed,angle;
    private int width, height, dmg,range;
    private Texture[] textures;
    private Enemy target;
    private CopyOnWriteArrayList<Enemy> enemies;
    private boolean targeted;
    private ArrayList<Projectile> projectiles;
    
    public Tower(TowerType type, Tile startTile,CopyOnWriteArrayList<Enemy> enemies)
    {
    this.textures = type.textures;
    this.dmg=type.damage;
    this.x=startTile.getX();
    this.y=startTile.getY();
    this.width=startTile.getWidth();
    this.height=startTile.getHeight();
    this.range=type.range;
    this.enemies=enemies;
    this.projectiles = new ArrayList<Projectile>();
    this.timeSinceLastShot=0;
    this.targeted=false;
    this.fireSpeed=type.fireSpeed;
    this.angle=0f;
    
    }
    private Enemy accTarget(){
        Enemy closest =null;
        float closestDistence= 10000;
        for (Enemy e:enemies){
            if(isInRange(e)&& findDistance(e) < closestDistence && e.isAlive()){
                closestDistence = findDistance(e);
                closest  = e;
            }
        }
        if(closest!= null){
            targeted=true;
        }
        return closest;
    }
    
     public void update(){
        if(!targeted){
            target  = accTarget();
            
        }else 
        
        {
            angle=calcAngle();
            if(timeSinceLastShot>fireSpeed){
            shoot();
        }
        }
        if(target==null || target.isAlive() == false){
            targeted = false;
        }
        timeSinceLastShot+=Delta();
        
        for(Projectile p:projectiles){
            p.update();
        }
        
        
        draw();
    }
private boolean isInRange(Enemy e){
        float xDistance= Math.abs(e.getX() - x);
        float yDistance= Math.abs(e.getY() - y);
        if(xDistance < range && yDistance < range){
            return true;
        }else return false;
    }
    private float findDistance(Enemy e){
        float xDistance= Math.abs(e.getX() - x);
        float yDistance= Math.abs(e.getY() - y);
        return xDistance + yDistance;
    }
            
    private float calcAngle(){
        double angletemp=Math.atan2(target.getY()-y, target.getX()-x);
        return (float) Math.toDegrees(angletemp)- 90;
    }
    public void shoot(){
        timeSinceLastShot=0;
        projectiles.add(new ProjectileIceball(QuickLoad("bullet"),target,x +TILE_SIZE/2 - TILE_SIZE/4,y+TILE_SIZE/2 -TILE_SIZE/4,32,32,975,10));
    }
    
    public void draw() {
        DrawQuadTex(textures[0],x,y,width,height); 
        if(textures.length>1){
        for(int i=1;i<textures.length;i++){
        DrawQuadTexRot(textures[i],x,y,width,height,angle); 
        
            }
        }
    }
    public void updateEnemyList(CopyOnWriteArrayList<Enemy> newList){
        enemies=newList;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }

   
    public int getHeight() {
        return height;
    }

    
    public void setX(float x) {
        this.x=x;
    }

    
    public void setY(float y) {
        this.y=y;
    }

    
    public void setWidth(int width) {
        this.width=width;
    }

    
    public void setHeight(int height) {
        this.height=height;
    }
    public Enemy getTarget(){
        return target;
    }
    
  
    
}
