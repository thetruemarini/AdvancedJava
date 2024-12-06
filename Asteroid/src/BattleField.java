import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BattleField {
	
	protected Rectangle2D.Float borders = new Rectangle2D.Float(-500, -500, 1000, 1000);
	protected SpaceShip spaceShip;
	protected ArrayList<SpaceObject> objectList = new ArrayList<>();


	public BattleField() {
		this.spaceShip = new SpaceShip(this);
		objectList.add(spaceShip);

		objectList.add(new Asteroid(this, 50, -450, 450, 1f, -1f, 0.01f));
		objectList.add(new Asteroid(this, 50, -450, -450, 1f, 1f, 0.01f));
		objectList.add(new Asteroid(this, 50, 450, 450, -1f, -1f, 0.01f));
		objectList.add(new Asteroid(this, 50, 450, -450, -1f, +1f, 0.01f));
	}

	public SpaceShip getSpaceShip() { return spaceShip; }

	public void stepNext() {
		for(SpaceObject o : objectList){
			o.stepNext();
			applyClosedUniverse(o);

		}
		detectedCollisions();
		removeDead();
	}

	protected void detectedCollisions() {
		
		int nobjs = objectList.size();
		if(nobjs < 2)
			return;

		SpaceObject[] objs = new SpaceObject[nobjs];
		objectList.toArray(objs); //cosi accesso all'array è piu veloce

		for(int i = 0; i < nobjs; i++){
			for(int j = i + 1; j< nobjs; j++){
				if(objs[i].checkCollision(objs[j])){
					objs[i].collisionDetected();
					objs[j].collisionDetected();

				}
			}
		}		

	}

	public void add(SpaceObject o){
		objectList.add(o);
	}

	public ArrayList<SpaceObject> listSpaceObjects() { return objectList; }

	private void applyClosedUniverse(SpaceObject o){
		
		if(o.getY() > borders.getMaxY()){
			o.setY((float)borders.getMinY());
		}
		else if(o.getY() < borders.getMinY()){
			o.setY((float)borders.getMaxY());
		}

		if(o.getX() > borders.getMaxX()){
			o.setX((float)borders.getMinX());
		}
		else if(o.getX() < borders.getMinX()){
			o.setX((float)borders.getMaxX());
		}
	}

    private void removeDead() {
		objectList.removeIf(o -> !o.isAlive());
    }
	

}