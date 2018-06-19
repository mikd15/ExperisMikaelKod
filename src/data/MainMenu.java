/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import UI.UI;
import static data.helpers.Artist.*;
import data.helpers.StateManager;
import org.lwjgl.input.Mouse;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Karlsson
 */
public class MainMenu {
    private Texture background;
    private UI menuUI;
    public MainMenu(){
        background = QuickLoad("mainmenu");
        menuUI=new UI();
        menuUI.addButton("Play", "play", WIDTH/ 2 - 128,(int) (HEIGHT*0.75f));
        menuUI.addButton("Editor", "edit", WIDTH/2-128, (int)(HEIGHT*0.82f));
        menuUI.addButton("QUIT", "quit", WIDTH/2-128, (int)(HEIGHT*0.89f));
    }
    private void updateButtons(){
        boolean leftMouseButtonDown=false;
        if(Mouse.isButtonDown(0)&& !leftMouseButtonDown){
            if(menuUI.isButtonClicked("Play")){
                StateManager.setState(StateManager.GameState.GAME);
            }
            if(menuUI.isButtonClicked("Editor")){
                StateManager.setState(StateManager.GameState.EDITOR);
            }
            if(menuUI.isButtonClicked("QUIT")){
                System.exit(5);
            }
            
        }
        leftMouseButtonDown = Mouse.isButtonDown(0);
    }
    public void update(){
        DrawQuadTex(background,0,0,2048,1024);
        menuUI.draw();
        updateButtons();
    }
}
