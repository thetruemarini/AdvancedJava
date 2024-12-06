import java.awt.Polygon;
import java.awt.geom.Area;

public class SpaceShip extends SpaceObject{

    public SpaceShip(BattleField universe) {
        super(universe);

        this.shape = new Area(new Polygon(
            new int[] { -30, 30, -30}, 
            new int[] { 15, 0, -15 }, 
            3
        ));

        this.setR((float) Math.PI/2);
    }

    public void fire() {   
          Bullet b = new Bullet(this);
          universe.add(b);
    }
    @Override
    public boolean checkCollision(SpaceObject o) {
    
        return (o instanceof Bullet) ? false : super.checkCollision(o);
    }

}