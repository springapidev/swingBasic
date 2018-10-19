package com.coderbd;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

class MyCar extends JPanel implements KeyListener, Runnable {

    Image i;
    Toolkit t;
    int j = 550, n = 80, l = 210, d, s = 0, p = 0;

    MyCar() {

        t = Toolkit.getDefaultToolkit();
        URL url = getClass().getResource("car.png");
        i = t.getImage(url);
        setBackground(Color.black);
        Thread tt = new Thread(this);

        tt.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.PINK);
        g.fillRect(0, 0, 100, 700);
        g.setColor(Color.PINK);
        g.fillRect(380, 0, 500, 700);
        g.setColor(Color.red);
        g.drawRect(130, 0, 220, 700);
        g.setColor(Color.white);
        g.fillRect(215, 0 + p, 15, 50);
        g.fillRect(245, 0 + p, 15, 50);
        g.fillRect(215, 100 + p, 15, 50);
        g.fillRect(245, 100 + p, 15, 50);
        g.fillRect(215, 200 + p, 15, 50);
        g.fillRect(245, 200 + p, 15, 50);
        g.fillRect(215, 300 + p, 15, 50);
        g.fillRect(245, 300 + p, 15, 50);
        g.fillRect(215, 400 + p, 15, 50);
        g.fillRect(245, 400 + p, 15, 50);
        g.fillRect(215, 500 + p, 15, 50);
        g.fillRect(245, 500 + p, 15, 50);
        g.fillRect(215, 600 + p, 15, 50);
        g.fillRect(245, 600 + p, 15, 50);
        g.drawImage(i, l, j, this);
    }

    public void keyPressed(KeyEvent d) {
        int code = d.getKeyCode();
        if (code == KeyEvent.VK_UP) {

            n = n - 2;
            j--;

            this.repaint();
        }

        if (code == KeyEvent.VK_DOWN) {

            n = n + 5;

            this.repaint();
        }

        if (code == KeyEvent.VK_LEFT) {

            l = l - 5;

            this.repaint();
        }

        if (code == KeyEvent.VK_RIGHT) {

            l = l + 5;

            this.repaint();
        }

        if (code == KeyEvent.VK_B) {

            j = j + 50;

            this.repaint();
        }

        if (code == KeyEvent.VK_U) {

            j = j - 5;

            this.repaint();
        }

    }

    public void keyReleased(KeyEvent d) {

    }

    public void keyTyped(KeyEvent d) {

    }

    public void run() {
        repaint();
        for (p = 0; p <= 100; p = p + 10) {
            repaint();
            try {

                Thread.sleep(n);
            } catch (Exception e) {
            }
            if (p == 100) {
                p = 0;
            }
        }
    }

    public static void main(String... s) {
        String ss = " Developed By Java";
        JFrame f = new JFrame();
        JLabel ll = new JLabel("Scores");
        ll.setBounds(400, 650, 50, 20);
        ll.setForeground(Color.red);
        f.add(ll);
        f.setTitle("                                                    Need For Speed Game");
        JLabel l = new JLabel(ss);
        l.setBounds(20, 650, 200, 20);
        l.setForeground(Color.PINK);
        f.add(l);
        f.setLayout(null);
        f.setSize(500, 700);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyCar o = new MyCar();
        o.setBounds(0, 0, 500, 650);
        f.add(o);
        f.addKeyListener(o);
    }
}
