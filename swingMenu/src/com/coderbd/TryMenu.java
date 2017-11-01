package com.coderbd;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author J2EE-33
 */
public class TryMenu extends JFrame {

    JMenu menu1, menu2, submenu, exit;
    JMenuItem item1, item2, item3, h1, h2;
   
    
    TryMenu() {
        JFrame f = new JFrame("Try Menu");
        //setp-1
        JMenuBar mb = new JMenuBar();
        //setp-2
        menu1 = new JMenu("File");
        item1 = new JMenuItem("New");
        item2 = new JMenuItem("Open");
        item3 = new JMenuItem("Exit");
        menu1.add(item1);
        menu1.addSeparator();
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);
        menu2 = new JMenu("Help");
        submenu = new JMenu("Submenu");
        h1 = new JMenuItem("Live Support");
        h2 = new JMenuItem("Email Support");
       
        submenu.add(h1);
        submenu.addSeparator();
        submenu.add(h2);
        menu2.add(submenu);
        //exit
        exit=new JMenu("Exit");
        exit.setMnemonic('E');
        //setp-3
        mb.add(menu1);
        mb.add(menu2);
         mb.add(exit);
        //setp-4
        f.setJMenuBar(mb);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new TryMenu();
    }
}
