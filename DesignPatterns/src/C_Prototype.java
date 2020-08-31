import java.util.HashMap;
import java.util.Map;

/*
 * copy an existing object, instead of create new instance from scratch
 * 
 * shallow copy -> copy not includes nested objects
 * deep copy -> copy includes nested object
 * 
 * Dis: implementing clone method can be challenging because of circular references
 * */
public class C_Prototype {
	public static void main(String[] args) {
		Color blue = new BlueColor();

		Color red = (Color) blue.clone();
		red.setColor("red");

		System.out.println(blue.colorName + " " + red.colorName);
	}

	abstract static class Color implements Cloneable {
		protected String colorName;

		abstract void setColor(String color);

		public Object clone() {
			Object clone = null;
			try {
				clone = super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return clone;
		}
	}

	static class BlueColor extends Color {

		BlueColor() {
			this.colorName = "blue";
		}

		@Override
		void setColor(String color) {
			this.colorName = color;
		}

	}

}
