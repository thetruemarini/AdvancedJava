import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class Asteroid extends SpaceObject{
    public Asteroid(BattleField f, int radius)  {
         this(f, radius, 0, 0, 0, 0, 0);
    }

    public Asteroid(BattleField f, int radius, float x, float y, float vx, float vy, float vr)  {
        super(f);

        this.shape = createRandomAsteroid(radius);

        this.setX(x);
        this.setY(y);
        this.setSpeed(vx, vy, vr);
    }

    private Shape createRandomAsteroid(int radius) {
       Random rnd = new Random();
       GeneralPath s = new GeneralPath();
       
       int nvertex = 10;

       float dt = (float) (Math.PI * 2 / nvertex);
       float t = 0;

       for (int i = 0; i < nvertex; i++, t+=dt) {
           float r = (float) (radius * (1 + rnd.nextFloat() / 10.));
           float x = r * (float) Math.cos(t);
           float y = r * (float) Math.sin(t);

           if(i == 0) {
               s.moveTo(x, y);
               continue;
           }

           switch (rnd.nextInt(3)) {
            case 0 : s.lineTo(x, y);                
                break;
            case 1 : s.curveTo(
                           x* (1+(2-rnd.nextDouble())/5),
                           y* (1+(2-rnd.nextDouble())/5),
                           x* (1+(2-rnd.nextDouble())/5),
                           y* (1+(2-rnd.nextDouble())/5),
                            x, y);               
                break;
            case 2 : s.quadTo(
                x* (1+(2-rnd.nextDouble())/5),
                y* (1+(2-rnd.nextDouble())/5),
                x, y
            );              
                break;
           
            default:
                break;
           }
        }
        s.closePath();
        return new Area(s);
    }
}
