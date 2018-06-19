/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import static data.helpers.Artist.*;
import data.helpers.Clock;
import data.helpers.StateManager;
import org.lwjgl.opengl.Display;
/**
 *
 * @author Karlsson
 */
public class boot {
        public boot(){
        BeginSession();
        
        
        while(!Display.isCloseRequested()){
            Clock.update();
            StateManager.update();            
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }    
    public static void main(String [] args){
        new boot();
    }
}
