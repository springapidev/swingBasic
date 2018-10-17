package com.coderbd.common;

import com.coderbd.view.New;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CommonMenu {

    public static void getCommonMenu(JFrame f) {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        jMenuBar.add(file);

        jMenuBar.add(new JMenu("About"));

        JMenu help = new JMenu("Help");
        jMenuBar.add(help);
        JMenuItem item1 = new JMenuItem("New");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               f.setVisible(false);
               new New().setVisible(true);
            }
        });

        file.add(item1);
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Exit"));

        f.setJMenuBar(jMenuBar);
    }
}
