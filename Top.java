package game;

import java.awt.Graphics;

public class Top {
    double x=0;
    double y=0;
    double fx=0;
    double fy=0;
    double g=0.3;
    int gen=10;
    public void Guncelle(double Katsayi)
    {
        x=x+fx*Katsayi;
        y=y+fy*Katsayi;
        fy=fy+g*Katsayi;
        /*if(y>400)
        {
            y=400;
            fy=-fy;
        }*/
        if(x>640-gen)
        {
            x=640-gen;
            fx=-fx;
        }
        if(x<0)
        {
            x=0;
            fx=-fx;
        }
        if(y>480-gen)
        {
            y=480-gen;
            fy=-fy;
        }
    }
    public void EkranaCiz(Graphics g)
    {
        g.drawOval((int)x,(int) y,(int) gen, (int)gen);
    }
}
