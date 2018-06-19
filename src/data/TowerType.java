/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import static data.helpers.Artist.*;
import org.newdawn.slick.opengl.Texture;
/**
 *
 * @author Karlsson
 */
public enum TowerType {
   
    
    CannonRed(new Texture[]{QuickLoad("cannonBase"), QuickLoad("cannonGun")},10,10000,1),
    CannonBlue(new Texture[]{QuickLoad("cannonBlue"), QuickLoad("gunBlue")},1,10000,0.2f),
    CannonIce(new Texture[]{QuickLoad("icebase"), QuickLoad("iceGun")},3,10000,0.4f);
    
    Texture [] textures;
    
    int damage,range;
    float fireSpeed;
    TowerType(Texture[] texture, int damage,int range, float fireSpeed){
        this.textures=texture;
        this.damage=damage;
        this.range=range;
        this.fireSpeed=fireSpeed;
    }
    
    
    
    
    
    
}
