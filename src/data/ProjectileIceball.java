/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Karlsson
 */
public class ProjectileIceball extends Projectile{

    public ProjectileIceball(Texture texture, Enemy target, float x, float y, int width, int height, float speed, int damage) {
        super(texture, target, x, y, width, height, speed, damage);
    }
    @Override
    public void hitTarget(){
        super.getTarget().setSpeed(super.getTarget().getSpeed() * 0.999f);
        super.hitTarget();
    }
    
    
    
    
}
