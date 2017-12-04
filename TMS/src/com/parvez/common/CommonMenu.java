package com.parvez.common;

import com.parvez.view.DashBoard;
import com.parvez.view.LogIn;
import com.parvez.view.Products;
import com.parvez.view.ProductsSalesForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Parvez
 */
public class CommonMenu {

    public CommonMenu() {
    }

    public static JMenuBar displayMenu(JFrame f) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");

        JMenuItem itemMain = new JMenuItem("DashBoard");
        itemMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new DashBoard().setVisible(true);
            }
        });

        JMenuItem item1 = new JMenuItem("Purchase Mobile");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new Products().setVisible(true);
            }
        });

        JMenuItem item2 = new JMenuItem("Sales Mobile");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new ProductsSalesForm().setVisible(true);
            }
        });

        JMenuItem item3 = new JMenuItem("Log out");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new LogIn().setVisible(true);
            }
        });
        menu1.add(itemMain);
        menu1.addSeparator();
        menu1.add(item1);
        menu1.addSeparator();
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);

        JMenu menu2 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return menuBar;
    }

}
