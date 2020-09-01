/*
 * decouple/separate the abstraction from its implementation. so that the two can vary independently. 
 * 
 * Example:
 * 	without bridge: if you want to change blue rectangle, you may end up change red rectangle
 * 							
 * 							blue_rectangle
 * 			 rectangle ->
 * 							red_rectangle
 * 	shape ->
 * 							blue_circle
 * 			 circle   ->
 * 							red_circle
 * 
 * 	with bridge:
 * 
 * 			 rectangle(color)
 * 	shape ->
 * 			 circle(color)
 * 
 * 			 blue
 * 	color ->
 * 			 red
 * 
 * Adv: loose coupling/ platform independence
 * 
 * Adapter vs Bridge: adapter pattern is usually applied after a system is designed whereas 
 * the bridge pattern is intentionally applied as part of the design process to decouple the
 * two layers.
 * 
 * Ref: https://howtodoinjava.com/design-patterns/structural/bridge-design-pattern/
 * */
public class S_Bridge {
	public static void main(String[] args) {

		Shape rectangle = new Rectangle(new Red());
		rectangle.colorIt();

		Shape circle = new Circle(new Blue());
		circle.colorIt();

	}

	abstract static class Shape {
		protected Color color;

		abstract public void colorIt();
	}

	static class Rectangle extends Shape {
		public Rectangle(Color color) {
			this.color = color;
		}

		@Override
		public void colorIt() {
			color.color();
			System.out.println(" rectangle");
		}
	}

	static class Circle extends Shape {
		public Circle(Color color) {
			this.color = color;
		}

		@Override
		public void colorIt() {
			color.color();
			System.out.println(" circle");
		}
	}

	interface Color {
		abstract public void color();
	}

	static class Blue implements Color {
		@Override
		public void color() {
			System.out.print("Blue color");
		}

	}

	static class Red implements Color {
		@Override
		public void color() {
			System.out.print("Red color");
		}

	}
}
