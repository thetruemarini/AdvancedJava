package it.unibs.pajc;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MandelbrotCntrl {
    private MandelbrotModel model;

    public MandelbrotCntrl(MandelbrotModel model) {
        this.model = model;
    }
    
    private ExecutorService exec = Executors.newFixedThreadPool(6);
    public void update(Rectangle2D.Double viewport, int resolution) {

        int[] resolutions = new int[] {50, 100, 250, 500, 1000, resolution};

        for(int res: resolutions){

            exec.submit(() -> 
                model.eval(new Complex(viewport.getMinX(), viewport.getMinY()),
                new Complex(viewport.getMaxX(), viewport.getMaxY()),
                resolution)
            );

            if (res>= resolution)
            break;
        }
    }
}
