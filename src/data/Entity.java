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
public interface Entity {
    
    
    
    public float getX();
    public float getY();
    public int getWidth();
    public int getHeight();
    public void setX(float x);
    public void setY(float y);
    public void setWidth(int width);
    public void setHeight(int height);
    public void update();
    public void draw();
    
    
    
}
