import Code.Map;
import Graphics.ColorMatrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //preset je matrica sastavljena od vektora u kome na prvoj koordinati pise koje su sanse da se
        //pored njega generise trava,sledeci je za pesak i poslednji je za vodu
        int[][] preset={{90,10,0},{40,0,40},{0,10,90}};
        //int[][] preset = {{70, 30, 0}, {40, 10, 30}, {0, 40, 80}};

        //int rows=500,cols=1000;
        int rows = 400, cols = 400;
        ColorMatrix colorMatrix = new ColorMatrix(rows, cols, 1);
        Map map = new Map(rows, cols, preset, colorMatrix);

        int currentEnthropy = map.getEntropy();
        int lastEnthropy = 0;
        while (currentEnthropy != lastEnthropy) {
            map.updateMap();
            lastEnthropy = currentEnthropy;
            currentEnthropy = map.getEntropy();
            Thread.sleep(1);
        }
        System.out.println("Done");
        colorMatrix.dispose();

        /*for (int i = 0; i < 5; i++) {
            int[][] preset={{90,10,0},{50,0,50},{0,1,90}};
            //int rows=500,cols=1000;
            int rows=1,cols=3;
            ColorMatrix colorMatrix=new ColorMatrix(rows,cols,100);
            Map map=new Map(rows,cols,preset,colorMatrix);

            int currentEnthropy= map.getEntropy();
            int lastEnthropy=0;
            while(currentEnthropy!=lastEnthropy){
                //System.out.println(map.getEntropy());
                map.updateMap();
                lastEnthropy=currentEnthropy;
                currentEnthropy=map.getEntropy();
                Thread.sleep(100);
            }
            System.out.println("Done");
            Thread.sleep(3000);
            colorMatrix.dispose();
        }*/
    }
}
