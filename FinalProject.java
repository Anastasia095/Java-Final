
import java.util.ArrayList;
import java.util.Scanner;

/*
 * - Final Project
 * - Robert Hagerty,Anastasia Tarasenko
 * Optional
 *  */
public class FinalProject {
	
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean t = true;
		System.out.println("\t\t\t\t\tWelcome to my Personal Management Program\n");
		
		while (t = true) {
			
				//Test code goes here 
			System.out.println("Choose one of the options:\n");
			System.out.println("1- Enter the information of a faculty");
			System.out.println("2- Enter the information of a student");
			System.out.println("3- Print tuition invoice for a student");
			System.out.println("4- Print faculty information");
			System.out.println("5- Enter the information of a staff member");
			System.out.println("6- Print the information of a staff member");
			System.out.println("7- Delete a person");
			System.out.println("8- Exit Program");
		
			//get use input
			int select = scanner.nextInt();
		
			switch(select) {
			case 1:
				
				//do something
				break;
			case 2:
				Scanner input = new Scanner(System.in);
				System.out.println("Enter the student info: ");
				System.out.println("\tName of Student: ");
				String name = input.nextLine();
				System.out.println("\tID: ");
				String id = input.nextLine();
				// NEED to TEST FOR ID FORMAT STILL
				System.out.println("\tGpa: ");
				double gpa = input.nextDouble();
				System.out.println("\tCredit hours: ");
				int credit = input.nextInt();
				
				// NEED to add to arraylist ? personList.add()
				
				break;
			case 3:
				//do something
				break;
			case 4:
				//do something
				break;
			case 5:
				//do something
				break;
			case 6:
				//do something
				break;
			case 7:
				//do something
				break;
			case 8:
				//Exit program
				System.exit(0);
			default: 
				System.out.println("Invalid entry- please try again");
			}
		}
	}
}


//------------------
interface Int1 {
	//not sure what methods I need to create, 
	// but I need some other class to implement it
	void displayData();
	int calculate(int x, int y);
}

//___________________
abstract class Person {
	
	public Person(String fullName, String id) {
		super();
		this.fullName = fullName;
		this.id = id;
	}
	
	private ArrayList<Person> personList;
	

	//things common for Student and Employee
	String fullName;
	String id;
		
	public abstract void print(int credits);
}
	
//__________________
//inherits from Person
class Student extends Person {
	
	public Student(String fullName, String id) {
		super(fullName, id);
		// TODO Auto-generated constructor stub
		}
	

	private double gpa;
	private int creditHours;
		
	//Student should get 25% discount if the GPA is higher than 3.85
	@Override
	public void print(int credits) {
		// TODO Auto-generated method stub
			
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	
	public Student(String fullName, String id, double gpa, int creditHours) {
		super(fullName, id);
		this.gpa = gpa;
		this.creditHours = creditHours;
		
		
	}
		
	public void tuitionInvoice() {
		
		
	}
}

//______________________
//abstract class inherits from Person
abstract class Employee extends Person{
	
	public Employee(String fullName, String id) {
		super(fullName, id);
		// TODO Auto-generated constructor stub
	}

	//things common for staff and faculty
	String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
	
//_______________________
class Faculty extends Employee{
	
	public Faculty(String fullName, String id) {
		super(fullName, id);
		// TODO Auto-generated constructor stub
	}

	String rank;

	@Override
	public void print(int credits) {
		// TODO Auto-generated method stub
		
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
		
}

//_________________________
class Staff extends Employee {
	
	public Staff(String fullName, String id) {
		super(fullName, id);
		// TODO Auto-generated constructor stub
	}

	String status;

	@Override
	public void print(int credits) {
		// TODO Auto-generated method stub
			
	}
}

