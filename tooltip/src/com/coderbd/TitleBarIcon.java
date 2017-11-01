package com.coderbd;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class TitleBarIcon {
  TitleBarIcon(){     
JFrame f=new JFrame();   
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/coderbd/image/New.gif"));    
f.setIconImage(icon);    
f.setLayout(null);     
f.setSize(400,400);  

f.setVisible(true);     
}     
public static void main(String args[]){     
new TitleBarIcon();     
}       
}
