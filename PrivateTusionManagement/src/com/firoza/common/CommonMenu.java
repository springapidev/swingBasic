package com.firoza.common;

import com.firoza.view.DashBoard;
import com.firoza.view.FeeSummaryForms;
import com.firoza.view.GenerateFee;
import com.firoza.view.LogIn;
import com.firoza.view.PaymentForm;
import com.firoza.view.StudentBilDetailslForm;
import com.firoza.view.StudentForm;
import com.firoza.view.StudentPaymentDetailsForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Firoza Akter
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

        JMenuItem item1 = new JMenuItem("Addmission");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new StudentForm().setVisible(true);
            }
        });

        JMenuItem item2 = new JMenuItem("Bill Generate");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new GenerateFee().setVisible(true);
            }
        });

        JMenuItem item3 = new JMenuItem("Payment");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new PaymentForm().setVisible(true);
            }
        });

        JMenuItem item4 = new JMenuItem("Bill | Payment Summary");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new FeeSummaryForms().setVisible(true);
            }
        });

        JMenuItem item5 = new JMenuItem("Bill Details");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new StudentBilDetailslForm().setVisible(true);
            }
        });
        JMenuItem item6 = new JMenuItem("Payment Details");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new StudentPaymentDetailsForm().setVisible(true);
            }
        });

        JMenuItem item7 = new JMenuItem("Payment Details");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(false);
                new LogIn().setVisible(true);
            }
        });

        menu1.add(item1);
        menu1.addSeparator();
        menu1.add(item2);
        menu1.addSeparator();
        menu1.add(item3);
        menu1.addSeparator();
        menu1.add(item4);
        menu1.addSeparator();
        menu1.add(item5);
        menu1.addSeparator();
        menu1.add(item6);
        menu1.addSeparator();
        menu1.add(item7);
        menu1.addSeparator();
        menu1.add(itemMain);
        JMenu menu2 = new JMenu("Help");

        menuBar.add(menu1);
        menuBar.add(menu2);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        return menuBar;
    }

}
