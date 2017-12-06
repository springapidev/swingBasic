package com.coderbd.mobile.common;

import com.coderbd.inventory.view.InventoryDashBoardForm;
import com.coderbd.inventory.view.OrderItemsListStatusForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Rajaul Islam
 */
public class CommonMenuForUser {

    public CommonMenuForUser() {
    }

    public static JMenuBar displayMenu(JFrame f) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");

        
        JMenuItem item7 = new JMenuItem("Inventory Dashboard");
        item7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new InventoryDashBoardForm().setVisible(true);
            }
        });

       
        menu1.add(item7);
        menu1.addSeparator();
              
        JMenu menu2 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return menuBar;
    }

}
