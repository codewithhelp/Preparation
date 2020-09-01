import java.util.ArrayList;
import java.util.List;

/*
 * compose objects into tree structure to represent part-whole hierarchies
 * 
 * example: directory contains directories or files, file is a leaf node, directory is a composite
 * 
 * Ref: https://www.geeksforgeeks.org/composite-design-pattern/?ref=rp
 * */
public class S_Composite {
	public static void main(String[] args) {

		Developer dev1 = new Developer("Bala", "Senior");
		Developer dev2 = new Developer("Satish", "Senior 2");
		CompanyDirectory devDirectory = new CompanyDirectory();
		devDirectory.addEmployee(dev1);
		devDirectory.addEmployee(dev2);

		Manager man1 = new Manager("Bala", "Senior");
		Manager man2 = new Manager("Satish", "Senior 2");
		CompanyDirectory manDirectory = new CompanyDirectory();
		manDirectory.addEmployee(man1);
		manDirectory.addEmployee(man2);

		CompanyDirectory root = new CompanyDirectory();
		root.addEmployee(devDirectory);
		root.addEmployee(manDirectory);

		root.showEmployeeDetails();
	}

	static interface Employee {
		public void showEmployeeDetails();
	}

	// leaf
	static class Developer implements Employee {
		String name, position;

		public Developer(String name, String position) {
			this.name = name;
			this.position = position;
		}

		@Override
		public void showEmployeeDetails() {
			System.out.println("Developer Name: " + name + ", Position: " + position);
		}
	}

	// leaf
	static class Manager implements Employee {
		String name, position;

		public Manager(String name, String position) {
			this.name = name;
			this.position = position;
		}

		@Override
		public void showEmployeeDetails() {
			System.out.println("Manager Name: " + name + ", Position: " + position);
		}
	}

	// non-leaf/composite node
	static class CompanyDirectory implements Employee {

		List<Employee> employees = new ArrayList<Employee>();

		@Override
		public void showEmployeeDetails() {
			for (Employee employee : employees)
				employee.showEmployeeDetails();
		}

		public void addEmployee(Employee emp) {
			employees.add(emp);
		}

		public void removeEmployee(Employee emp) {
			employees.remove(emp);
		}

	}

}
