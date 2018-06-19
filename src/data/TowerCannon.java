/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import static data.helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
import static data.helpers.Clock.*;
import java.util.ArrayList;


/**
 *
 * @author Karlsson
 */
public class TowerCannon {
    private float x,y,timeSinceLastShot,fireSpeed,angel;
    private int width, height, damage,range;
    private Texture baseTexture, cannonTexture;
    private Tile startTile;
    private Enemy target;
    private boolean targeted;
    
   private ArrayList<Projectile> Projectiles;
   private ArrayList<Enemy> enemies;
   public TowerCannon(Texture baseTexture,Tile startTile, int damage,int range, ArrayList<Enemy> enemies){
        this.baseTexture=baseTexture;
        this.cannonTexture= QuickLoad("cannonGun");
        this.startTile=startTile;
        this.damage=damage;
        this.range=range;
        this.x=startTile.getX();
        this.y=startTile.getY();
        this.width=(int)startTile.getWidth();
        this.height=(int)startTile.getHeight();
        this.timeSinceLastShot=0;
        this.fireSpeed=2;
        this.targeted = false;
        this.Projectiles= new ArrayList<Projectile>();
        this.enemies=enemies;
        
    }
    private Enemy accTarget(){
        Enemy closest =null;
        float closestDistence= 10000;
        for (Enemy e:enemies){
            if(isInRange(e)&& findDistance(e) < closestDistence){
                closestDistence = findDistance(e);
                closest  = e;
            }
        }
        if(closest!= null){
            targeted=true;
        }
        return closest;
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
    private void shoot(){
        timeSinceLastShot=0;
        Projectiles.add(new ProjectileIceball(QuickLoad("bullet"),target,x +TILE_SIZE/2 - TILE_SIZE/4,y+TILE_SIZE/2 -TILE_SIZE/4,32,32,975,10));
    }
    public void updateEnemyList(ArrayList<Enemy> newList){
        enemies=newList;
    }
    public void update(){
        if(!targeted){
            target  = accTarget();
        }
        if(target==null || target.isAlive() == false){
            targeted = false;
        }
        
        
        
        timeSinceLastShot+=Delta();
        if(timeSinceLastShot>fireSpeed){
            shoot();
        }
        for(Projectile p:Projectiles){
            p.update();
        }
        angel=calcAngle();
        Draw();
    }
    public void Draw(){
        DrawQuadTex(baseTexture,x,y,width,height);
        DrawQuadTexRot(cannonTexture,x,y,width,height,angel);
    }
    
}
