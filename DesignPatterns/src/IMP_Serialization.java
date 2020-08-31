import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// by default we can not store object in file, as java is secured language to save object in file we 
// need to implement the class with serializable
public class IMP_Serialization {
	public static void main(String[] args) throws Exception {

		SaveObj obj1 = SaveObj.getInstance();

		File file = new File("saveObj.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		oos.writeObject(obj1);
//		oos.close();
//
		obj1.setI(20);

		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		SaveObj obj2 = (SaveObj) ois.readObject();
		ois.close();

		System.out.println(obj1.getI());
		System.out.println(obj2.getI());
	}
}

class SaveObj implements Serializable {
	private static final long serialVersionUID = 1L;

	private SaveObj() {
		System.out.println("I am called");
	}

	private static volatile SaveObj INSTANCE = null;

	public static SaveObj getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SaveObj();
		return INSTANCE;
	}

	private int i = 10, e = 20, j = 1;

	public void setI(int i) {
		this.i = i;
	}

	public int getI() {
		return i;
	}

	protected Object readResolve() {
		return INSTANCE;
	}
}

class SaveObj1 implements Serializable {
	String name = "bala";
}