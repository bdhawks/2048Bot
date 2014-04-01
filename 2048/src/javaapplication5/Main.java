/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication5;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Main {

    /**
     * blank tile= 204:192:179
     * 2 tile= r=238,g=228,b=218
        4 tile= r=237,g=224,b=200
        8 tile= [r=242,g=177,b=121]
        16 tile= 245:149:99
        32 tile= 246:124:95
        64 tile= 246:94:59
        128 tile= 237:207:114
        256 tile= 237:204:97
     * @param args
     * @throws java.awt.AWTException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws AWTException, InterruptedException {
        ArrayList<Color> colors=new ArrayList();
        ArrayList<String> tiles=new ArrayList();
        int[][][] grid=new int[4][4][1];
        colors.add(new Color(204,192,179)); //empty tile
        colors.add(new Color(238,228,218)); //2
        colors.add(new Color(237,224,200)); //4
        colors.add(new Color(242,177,121)); //8
        colors.add(new Color(245,149,99)); //16
        colors.add(new Color(246,124,95)); //32
        colors.add(new Color(246,94,59)); //64
        colors.add(new Color(237,207,114)); //128
        colors.add(new Color(237,204,97)); //256
        Thread.sleep(2000);
        Robot r=new Robot();
        Rectangle rect=new Rectangle(700, 310, 505, 505);
        int y=((int)rect.getY()+40);
        int x=((int)rect.getX()+30);
        for(int i=0;i<4;i++)
        {
            for(int b=0;b<4;b++)
            {
                Color c=r.getPixelColor(x,y);
                grid[i][b][0]=colors.indexOf(c);
                if(colors.indexOf(c)>0)
                {
                    tiles.add(i+","+b+","+colors.indexOf(c));
                }
                x+=120;
            }
            x=((int)rect.getX()+30);
            y+=120;
        }
        for(int i=0;i<4;i++)
        {
            for(int b=0;b<4;b++)
            {
                System.out.println(grid[i][b][0]+" tile @"+ "("+i+","+b+")");
            }
        }
        for(int i=0;i<tiles.size();i++)
        {
            System.out.println(tiles.get(i));
        }
        makeMove(tiles);
        pause();
    }
    static void makeMove(ArrayList<String> pieces)
    {
        //use first 2 lower value pieces
        //compare x and y differences, move piece according to least difference
        int atileX=Integer.parseInt(pieces.get(i).substring(0, 1));
        int btileX=Integer.parseInt(pieces.get(i+1).substring(0,1));
        int atileY=Integer.parseInt(pieces.get(i).substring(2,3));
        int btileY=Integer.parseInt(pieces.get(i+1).substring(2,3));
        if(Integer.compare(atileY, btileY)<0)
        {
            try {
                Robot r=new Robot();
                r.keyPress(KeyEvent.VK_LEFT);
                r.keyRelease(KeyEvent.VK_LEFT);
            } catch (AWTException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    static void pause()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Done");
        String b=scan.next();
    }
}
