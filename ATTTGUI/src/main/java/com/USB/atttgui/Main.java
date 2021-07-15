package com.USB.atttgui;

import java.awt.*;
 
public class Main {
    public static void main(String[] args){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        
        // width will store the width of the screen
        int width = (int)size.getWidth();
        
        // height will store the height of the screen
        int height = (int)size.getHeight();
        
        Login lg = new Login();
        
        lg.setVisible(true);
        lg.setResizable(false);
        lg.setLocation(width/2 - lg.getWidth()/2, height/2 - lg.getHeight()/2);
    }
}
