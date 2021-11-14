package sample;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class Flock {

	private final Bird[] birds = new Bird[20];
	private final double maxV = 10;


	private final double awareness = 45;
	private final double cohesiveness = 1;
	private final double willtosteer = 1;
	private final double divisiveness = 35;

	
	public Flock() {for(int i = 0;i<birds.length;i++) {birds[i] = new Bird();}}
	
	public void update(boolean s) {
		for(Bird boid : birds) {
			
			if(!s) {

				boid.cohesion(birds,awareness,cohesiveness);
				boid.steering(birds,awareness,willtosteer);
				boid.separation(birds,awareness,divisiveness);
			}else {

				boid.cohesion(birds);
				boid.steering(birds);
				boid.separation(birds);
			}
			

			boid.velocity = boid.velocity.add(boid.acceleration);
			if(boid.velocity.magnitude() > maxV) {	boid.velocity = boid.velocity.multiply(maxV/boid.velocity.magnitude());}
			

			boid.center = boid.center.add(boid.velocity);
			if(boid.center.getX() < 0) {				boid.center = boid.center.add(new Point2D(Sky.width,0));}
			if(boid.center.getX() > Sky.width) {	boid.center = boid.center.add(new Point2D(-Sky.width,0));}
			if(boid.center.getY() < 0) {				boid.center = boid.center.add(new Point2D(0,Sky.height));}
			if(boid.center.getY() > Sky.height) {	boid.center = boid.center.add(new Point2D(0,-Sky.height));}
			boid.body.relocate(boid.center.getX(),boid.center.getY());
			

			boid.acceleration = new Point2D(0,0);

		}
	}
	
	public void draw(Group root) {
		for(Bird boid : birds) {
			boid.body = new Circle(boid.center.getX(),boid.center.getY(),5);
			boid.body.setFill(boid.type.color);
			boid.body.setStrokeWidth(1.0);

			root.getChildren().add(boid.body);
		}
	}
}
