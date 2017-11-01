package com.coderbd;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

public class FrontInfo {

   

    public static void main(String[] args) {
        Toolkit theKit = Toolkit.getDefaultToolkit();
        int wndResolution=theKit.getScreenResolution();
        System.out.println("theKit.getScreenResolution(): "+wndResolution);
        Dimension screenDim = theKit.getScreenSize();
        System.out.println("Screen Size: "+screenDim.width+" by "+screenDim.height+" pixels");
        
        GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontnames=e.getAvailableFontFamilyNames();
        System.out.println("Fonts Available on this Platforms");
        int count=0;
        for(String fontname : fontnames){
            System.out.printf("%-30s",fontname);
            if(++count % 3 == 0){
                System.out.println(" cccc");
            }
        }
        return;
     
    }
}
