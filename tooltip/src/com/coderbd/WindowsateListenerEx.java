package com.coderbd;

import java.awt.*;
import java.awt.event.*;

public class WindowsateListenerEx extends Frame implements WindowStateListener {

    Label l;

    WindowsateListenerEx() {
        addWindowStateListener(this);
        l = new Label();
        l.setBounds(20, 50, 100, 20);
        add(l);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
        
     
    }

    
 
    @Override
    public void windowStateChanged(WindowEvent e) {
        System.out.println("Window present state: "+this.getExtendedState());
    }
    
       public static void main(String[] args) {
        new WindowsateListenerEx();
    }
       
       // Some window managers don't support all window states.
 

}
