package com.coderbd;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Test {

    public Test() {
        final JFrame frame = new JFrame("Screen Saver");
        frame.validate();
        frame.setVisible(true);

        KeyAdapter listener = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                frame.dispose();
            }
        };

        frame.addKeyListener(listener);
    }

    public static void main(String[] args) {
        new Test();
    }

}