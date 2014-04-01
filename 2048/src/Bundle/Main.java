package Bundle;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bruno
 */
public class Main 
{

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
        **/
    public static void main(String[] args) throws InterruptedException 
    {
        Thread.sleep(2000);
        pause();
    }
    static void updateGrid() throws AWTException
    {
        ArrayList<Color> colors=new ArrayList();
        ArrayList<String> tiles=new ArrayList();
        int[][][] grid=new int[4][4][1];
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
                    tiles.add(i+","+b+","+colors.indexOf(c)); //add tiles that are not empty to tiles arraylist
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
                System.out.println(grid[i][b][0]+" tile @"+ "("+i+","+b+")"); //prints detected tiles and values
            }
        }
        for(int i=0;i<tiles.size();i++)
        {
            System.out.println(tiles.get(i)); //prints tiles that are not empty
        }
    }
    static void makeMove(ArrayList<String> pieces)
    {
        //use first 2 lower value pieces
        //compare x and y differences, move piece according to least difference
        int atileX=Integer.parseInt(pieces.get(0).substring(0, 1));
        int btileX=Integer.parseInt(pieces.get(0+1).substring(0,1));
        int atileY=Integer.parseInt(pieces.get(0).substring(2,3));
        int btileY=Integer.parseInt(pieces.get(0+1).substring(2,3));
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
