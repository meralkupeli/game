package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Oyun extends JFrame {
    int boyX=640;
    int boyY=480;
    Insets ickisim;
    BufferedImage gorselBuffer;
    int x=10;
    int y=10;
    int hedefFPS=30;
    boolean oyundevam=true;
    long FPSyazdirma=0;
    int mevcutFPS=0;
   // Top top=null;
    Top[] top=null;
    int Topsayisi=100;

    void Dongu() {
        baslangic();
        long donguBitis=System.nanoTime();
        long hedefDonguZamani=1000000000/hedefFPS;
        while(oyundevam)
        {
            long dongubslangic=System.nanoTime();
            long DonguZamani=dongubslangic-donguBitis;
            //System.out.println(DonguZamani);
            donguBitis=dongubslangic;
            double Zamandegiskeni=(double)DonguZamani/
                    (double)hedefDonguZamani;
            mevcutFPS++;
            FPSyazdirma+=DonguZamani;
            if(FPSyazdirma>1000000000)
            {
                System.out.println("FPS: "+mevcutFPS);
                FPSyazdirma=0;
                mevcutFPS=0;       
            }
            
            OyunMantiginiGuncelle(Zamandegiskeni);
            
            OyunGrafiginiGuncelle();
            long artanSure=hedefDonguZamani-
                    (System.nanoTime()-dongubslangic);
            if(artanSure>0)
            {
                try {
                    Thread.sleep(artanSure/1000000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void baslangic() {
        setTitle("Ziplayan Toplar");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        ickisim=getInsets();
       // System.out.println(ickisim.bottom+ "  "+ickisim.top);      
        setSize(boyX+ickisim.left+ickisim.right,
                boyY+ickisim.top+ickisim.bottom);
        gorselBuffer=new BufferedImage(boyX, boyY,TYPE_INT_RGB);
       /* top=new Top();
        top.x=30;
        top.y=20;*/
       top=new Top[Topsayisi];
       for(int i=0;i<Topsayisi;i++)
       {
           top[i]=new Top();
           top[i].x=Math.random()*640;
           top[i].y=Math.random()*480;
           top[i].fx=Math.random()*10;
           top[i].fy=Math.random()*10;
           top[i].g=(Math.random()*29)+1;
       }
        
    }

    private void OyunMantiginiGuncelle(double katSayi) {
       /* x++;
        y++;*/
       for(int i=0;i<Topsayisi;i++)
       {
           top[i].Guncelle(katSayi);
       }
        
    }

    private void OyunGrafiginiGuncelle() {
        
        Graphics frameGrafi=getGraphics();
        Graphics bufferGrafi=gorselBuffer.getGraphics();
        
        bufferGrafi.setColor(Color.WHITE);
        bufferGrafi.fillRect(0, 0, boyX, boyY);
        
        bufferGrafi.setColor(Color.BLACK);
       // bufferGrafi.fillRect(x, y, 30, 30);
       for(int i=0;i<Topsayisi;i++)
       {
           top[i].EkranaCiz(bufferGrafi);
       }
        
        frameGrafi.drawImage(gorselBuffer, ickisim.left, 
                ickisim.top, this);
    }

   
    
}
