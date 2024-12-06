import java.awt.Polygon;
import java.awt.geom.Area;

public class Bullet extends SpaceObject{

    protected int fuel;
    protected SpaceShip ship;

    public Bullet(SpaceShip ship) {//spaceship da cui è sparato
        super(ship.universe);
        this.ship = ship;
        this.fuel = 100;

        this.shape = new Area(
                new Polygon(
                        new int[] { -5, 0, 5, 0, -5 },
                        new int[] { 3, 3, 0, -3, -3},
                        5
                ));
        this.setX(ship.getX());
        this.setY(ship.getY());
        this.setR(ship.getR()); //cosi i proiettili non sono storti
        this.setSpeed(ship.getVx(), ship.getVy(), 0f); //non ruota obv
        this.accelerate(5f);

    }

        @Override
        public void stepNext(){
            super.stepNext();
            if(--fuel <= 0)
                isAlive = false;

        }

        @Override
        public boolean checkCollision(SpaceObject o) {
            return (o instanceof Bullet || o instanceof Bullet) ? false : super.checkCollision(o);
        }

    

    
}