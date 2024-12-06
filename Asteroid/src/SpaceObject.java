import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class SpaceObject {

    private float[] position = {0, 0, 0} ; //x, y, r
    private float[] speed = {0, 0, 0} ; //vx, vy, vr
    protected boolean isAlive = true;
    protected BattleField universe;

    public SpaceObject(BattleField universe) { this.universe = universe; }

    public float getX() { return position[0]; }

    public float getY() { return position[1];}  

    public float getR() { return position[2]; }

    public void setX(float x) { position[0] = x; }    

    public void setY(float y) { position[1] = y; }    

    public void setR(float r) { 
        position[2] = r;    
    }      

    public float getVx() {
        return speed[0];
    }

    public float getVy() {
        return speed[1];        
    }    

    public float getVr() {          
        return speed[2];        
    }

    public void setSpeed(float x, float y, float r) {
        speed[0] = x;
        speed[1] = y;
        speed[2] = r;   
    }

    public boolean isAlive() {
        return isAlive;
    }  

    //forma
    protected Shape shape;
    
    public Shape getShape() { //sfrutta le trasformate affini per restituire l'oggetto nello spazio e ruotato di r
        AffineTransform t = new AffineTransform();
        t.translate(getX(), getY());
        t.rotate(getR());
        return t.createTransformedShape(shape); 
    }

    public void accelerate(float a){
        //deve accellerare lungo la direzione verso cui è ruotata, trignonometria
        speed[0] += a * Math.cos(position[2]);
        speed[1] += a * Math.sin(position[2]);
        //speed[2] = speed[2];
    }

    public void turn(float dr){
        position[2] += dr;
    }

    public void stepNext() {
        position[0] += speed[0]; //x = x + vt con t = 1 quantizzato
        position[1] += speed[1];
        position[2] += speed[2];
    }

    public boolean checkCollision(SpaceObject o) {//con la posizione
        Area o1 = new Area(this.getShape());
        Area o2 = new Area(o.getShape());

        o1.intersect(o2); //modifica o1 come l'intersezione di o1 e o2

        return !o1.isEmpty();
        
    }

    public void collisionDetected(){
        isAlive = false;
    }
    


}