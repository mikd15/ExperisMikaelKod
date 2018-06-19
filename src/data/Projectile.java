/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.helpers.Artist;
import static data.helpers.Clock.Delta;
import org.newdawn.slick.opengl.Texture;
import static data.helpers.Artist.*;

/**
 *
 * @author Karlsson
 */
public abstract class Projectile implements Entity{
    private float x,y,speed,xVel,yVel;
    private Texture texture;
    private int damage, width, height;
    private Enemy target;
    private boolean alive;
    
    
    public Projectile(Texture texture,Enemy target,float x, float y,int width, int height, float speed, int damage){
        this.texture=texture;
        this.x=x;
        this.y=y;
        this.width= width;
        this.height=height;
        this.speed=speed;
        this.damage=damage;
        this.target=target;
        this.alive=true;
        this.xVel=0f;
        this.yVel=0f;
        aim();
    }
    private void aim(){
         float totalAllowedMovement = 1.0f;
         float xDistenceFromTarget = Math.abs(target.getX()-x -TILE_SIZE/4+ TILE_SIZE/2);
         float yDistenceFromTarget = Math.abs(target.getY()-y -TILE_SIZE/4+ TILE_SIZE/2);
         float totalDistensFromTarget=xDistenceFromTarget+yDistenceFromTarget;
         float xPrecentOfMovement =xDistenceFromTarget/totalDistensFromTarget;
         xVel=xPrecentOfMovement;
         yVel=totalAllowedMovement-xPrecentOfMovement;
         if(target.getX() < x){
             xVel *= -1;
             
         }
         if(target.getY() < y){
             yVel *=-1;
         }
    }
    public void hitTarget(){
        target.damage(damage);
        alive=false;
           
    }
    
    public void update(){
        if(alive){
        x+=xVel *speed* Delta();
        y+=yVel *speed* Delta();
       if( CheckCollision(x,y,width,height,target.getX(),target.getY(),target.getWidth(),target.getHeight())){
          hitTarget();
           
       }
        
    
        draw();
        }
    }
    public void draw(){
        DrawQuadTex(texture,x,y,Artist.TILE_SIZE/2,Artist.TILE_SIZE/2);
    }

    
    public float getX() {
        return x;
    }

    
    public float getY() {
       return y;
    }

    
    public int getWidth() {
        return this.width;
    }

    
    public int getHeight() {
        return this.height;
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
    public void setAlive(boolean s){
        alive=s;
    }
}
