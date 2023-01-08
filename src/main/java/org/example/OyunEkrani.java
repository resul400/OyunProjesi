package org.example;

import javax.swing.*;
import java.awt.*;

public class OyunEkrani extends JFrame {

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {

        OyunEkrani ekran = new OyunEkrani("Uzay oyunu");

        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Oyun oyun= new Oyun();

        oyun.requestFocus();

        oyun.addKeyListener(oyun);

        oyun.setFocusable(true);

        oyun.setFocusable(true);

        oyun.setFocusTraversalKeysEnabled(false);

        ekran.add(oyun);

        ekran.setVisible(true);




















    }
}