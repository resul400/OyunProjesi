package org.example;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


class Ates {

        private int X;
        private  int y;

        public int getX() {
                return X;
        }

        public void setX(int x) {
                X = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

        public Ates(int x, int y) {
                X = x;
                this.y = y;
        }
}
public class Oyun extends JPanel implements KeyListener, ActionListener  {

        Timer timer=new Timer(10,this);


        private int gecen_sure=0;
        private  int harcanan_ates=0;

        private BufferedImage image;

        private ArrayList<Ates> atesler= new ArrayList<Ates>();

        private int atesdirY=1;

        private int topX = 0;

        private int topdirX=2;

        private int uzayGemisiX = 0;

        private int dirUzayX = 20;

        public boolean kontrolEt(){

                for (Ates ates: atesler) {

                        if (new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(topX,0,20,20))){

                                return true;
                        }


                }
                return  false;



        }

        @Override
        public void paint(Graphics g) {
                super.paint(g);
                gecen_sure +=5;

                g.setColor(Color.RED);
                g.fillOval(topX,0,20,20);
                g.drawImage(image,uzayGemisiX,490,image.getWidth()/3,image.getHeight()/3,this);

                for (Ates ates: atesler) {
                        if (ates.getY()<0){
                                atesler.remove(ates);
                        }

                }
                g.setColor(Color.BLUE);

                for (Ates ates: atesler
                     ) {
                        g.fillRect(ates.getX(),ates.getY(),10,20);
                        
                }

                if (kontrolEt()){
                        timer.stop();
                        String message ="Kazandiniz...\n" + "Harcanan Ates :"+ harcanan_ates + "\nGecen Sure :"+gecen_sure / 1000.0+" saniye";


                        JOptionPane.showMessageDialog(this,message);

                        System.exit(0);



                }

        }

        @Override
        public void repaint() {
                super.repaint();
        }


        public Oyun() {

                try {
                        image = ImageIO.read(new FileImageInputStream(new File("Unknown.jpeg")));
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }

                setBackground(Color.BLACK);

                timer.start();



        }

        @Override
        public void actionPerformed(ActionEvent e) {

                for (Ates ates: atesler) {

                        ates.setY(ates.getY()-atesdirY);

                }
                topX += topdirX;
                if (topX>=750){
                        topdirX=-topdirX;

                }
                if (topX<=0){
                        topdirX=-topdirX;

                }

                repaint();

        }

        @Override
        public void keyTyped(KeyEvent e) {



        }

        @Override
        public void keyPressed(KeyEvent e) {
                int c=e.getKeyCode();
                if (c==KeyEvent.VK_LEFT){
                        if (uzayGemisiX<=0){
                                uzayGemisiX=0;
                        }else {
                                uzayGemisiX-=dirUzayX;
                        }

                } else if (c == KeyEvent.VK_RIGHT) {

                        if (uzayGemisiX>=750){
                                uzayGemisiX=750;

                        }else {
                                uzayGemisiX+=dirUzayX;
                        }



                } else if (c== KeyEvent.VK_CONTROL) {
                        atesler.add(new Ates(uzayGemisiX+15,460));
                        harcanan_ates++;

                }

        }

        @Override
        public void keyReleased(KeyEvent e) {



        }
}
