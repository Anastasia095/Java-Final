package final_project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.*;
import java.io.File;
import java.io.PrintWriter;

/*
 * - Final Project
 * - Robert Hagerty, Anastasia Tarasenko, 
 * Optional
 *  */
public class FinalProject {
	
	public static boolean checkId(String id) {
		String regex = "^[a-zA-Z][a-zA-Z][0-9][0-9][0-9][0-9]$";
		
		Pattern p = Pattern.compile(regex);
		
		if (id == null) {
			return false;
		}
		
		Matcher m = p.matcher(id);
		
		return m.matches();
		
	}
	
	
	
	
	
	public static void main (String[] args) {
		//Array list to store our person objects
		ArrayList<Person> personList = new ArrayList<>(100);
		//stuff for file generation
//        File lecturesOnly = new File("lecturesOnly.txt");
//        PrintWriter writer = new PrintWriter(lecturesOnly);
//        Scanner scanner = new Scanner(file);
		
		Scanner scanner = new Scanner(System.in);
		boolean t = true;
		System.out.println("\t\t\t\t\tWelcome to my Personal Management Program\n");
		System.out.println("Choose one of the options:\n");
		
		while (t = true) {
			
				//Test code goes here 
			
			System.out.println("1- Enter the information of a faculty");
			System.out.println("2- Enter the information of a student");
			System.out.println("3- Print tuition invoice for a student");
			System.out.println("4- Print faculty information");
			System.out.println("5- Enter the information of a staff member");
			System.out.println("6- Print the information of a staff member");
			System.out.println("7- Delete a person");
			System.out.println("8- Exit Program");
			System.out.println("\tEnter your selection: ");
		
			//get user input
			String select = scanner.nextLine();
			Scanner input = new Scanner(System.in);
		
			switch(select) {
			
			case "1":
				
				System.out.println("Enter the information of a faculty: ");
				
				System.out.println("\tName of the faculty: ");
				String facultyName = input.nextLine();
				
				String facultyId = null;
				int z = 0;
				boolean f = true;
				while (z == 0) {
					System.out.println("\tID: ");
					facultyId = input.nextLine();
					
					if(checkId(facultyId) == true) {
						for (Person person : personList) {
							if(person.getId().contains(facultyId)) {
								System.out.println("\tDuplicate ID detected.");
								System.out.println("\tTwo people must not have the same ID.\n");
								f = false;
							}
							else {
								f = true;
								z = 1;
							}
						}
						if (f == false) {
							z = 0;
						} else {
							z = 1;
						}
					}
					else {
						System.out.println("\tInvalid ID format.");
						System.out.println("\tMust be LetterLetterDigitDigitDigitDigit\n");
						
					}
				}
				
				Faculty faculty = new Faculty(facultyName, facultyId);
				
				z = 0;
				while (z == 0) {
					System.out.println("\tRank: ");
					String rank = input.nextLine();
					if(rank.compareToIgnoreCase("Professor") == 0) {
						rank = "Professor";
						faculty.setRank(rank);
						z = 1;
						
					} else if (rank.compareToIgnoreCase("Adjunct") == 0) {
						rank = "Adjunct";
						faculty.setRank(rank);
						z = 1;
						
					}
					else {
						System.out.println("\t\"" + rank + "\" is invalid");
					}
				}
				
				z = 0;
				while (z == 0) {
					System.out.println("\tDepartment: ");
					String department = input.nextLine();
					if(department.compareToIgnoreCase("Mathematics") == 0) {
						department = "Mathematics";
						faculty.setDepartment(department);
						z = 1;
					} else if (department.compareToIgnoreCase("Engineering") == 0) {
						department = "Engineering";
						faculty.setDepartment(department);
						z = 1;
					} else if (department.compareToIgnoreCase("English") == 0) {
						department = "English";
						faculty.setDepartment(department);
						z = 1;
					} else {
						System.out.println("\t\"" + department + "\" is invalid");
					}
				}
				
				personList.add(faculty);
				System.out.println("Faculty added! \n\n\n");
				
				break;
				
			case "2":
				
				System.out.println("Enter the information of a student: ");
				
				System.out.println("\tName of Student: ");
				String studentName = input.nextLine();
				
				String studentId = null;
				int y = 0;
				while(y == 0) {
					
					System.out.println("\tID: ");
					studentId = input.nextLine();
					
					if(checkId(studentId) == true) {
						y = 1;
					}
					else {
						System.out.println("\tInvalid ID format.");
						System.out.println("\tMust be LetterLetterDigitDigitDigitDigit");
					}
				}
				
				Student student = new Student(studentName, studentId);
				
				y = 0;
				double gpa = 0.00;
				while(y == 0) {
					
					y = 1;
					
					try {
						System.out.println("\tGpa: ");
						gpa = (new Scanner(System.in)).nextDouble();
						
						if(gpa > 4.00 || gpa < 0.00) throw new genericException();
						
					}
					catch(Exception e) {
						System.out.println("\tInvalid GPA format.");
						System.out.println("\tMust be a value between 0.00 and 4.00 rounded to two decimals.");
						y = 0;
					}
					
					
						
					
				}
				student.setGpa(gpa);
				
				y = 0;
				int credit = 0;
				while(y == 0) {
					
					y = 1;
					
					try {
						System.out.println("\tCredit hours: ");
						credit = (new Scanner(System.in)).nextInt();
					}
					catch(Exception e) {
						System.out.println("\tInvalid Credit Hour format.");
						System.out.println("\tMust be a numerical value.");
						y = 0;
					}
				}
				student.setCreditHours(credit);
				
				personList.add(student);
				System.out.println("Student added! \n\n\n");
				//TEST			
//	            Student firstStudent = (Student) personList.get(0);
//	            firstStudent.print(3);
				
				break;
				
			case "3":
				
				System.out.println("Enter the student's ID:");
				String id = input.nextLine();
		        for (Person person : personList) {
		        	
		            if (person instanceof Student) {
		            	
		                Student studentObj = (Student) person;
		                int fee = 52;
		                double total = studentObj.getCreditHours() * 236.45 + 52;
		                double discount = 0.0;
		                
		                if(studentObj.getGpa() >= 3.85) {
		                	discount = total * 0.25;
		                	total = total - discount;
		                }
		                
		                           
		                if (studentObj.getId().equals(id)) {
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------");
		                	//I think all the fields should be private so we should change that eventually
		                	System.out.println(studentObj.getfullName() + "\t\t" + studentObj.getId());
		                	System.out.println("Credit Hours: " + studentObj.getCreditHours() + " ($236.45/credit hour)");
		                	System.out.println("Fees: $" + fee);
		                	System.out.printf("\nTotal payment (after discount):  %.2f", total);
		                	System.out.printf("\n($%.2f discount applied)\n", discount);
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------\n\n\n");
		                } else {
		                	System.out.println("No Student matched!\n\n\n");
		                }
		            }
		         }
		        
				break;
				
			case "4":
				//I think redeclaring variables might be  a bad practice, need to look into that later.
				//apparently cases don't have their own scope
				System.out.println("Enter the Facultys's ID:");
				id = input.nextLine();
		        for (Person person : personList) {
		            if (person instanceof Faculty) {
		                Faculty facultyObj = (Faculty) person;
		                if (facultyObj.getId().equals(id)) {
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------");
		                	//I think all the fields should be private so we should change that eventually
		                	System.out.println(facultyObj.getfullName() + "\t\t" + facultyObj.getId());
		                	System.out.println(facultyObj.getDepartment() + " Department,  \t" + facultyObj.getRank());
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------\n\n\n");
		                } else {

		                }
		            } else {
		            	System.out.println("No Faculty matched!");
		            }
		         }
		        
				break;
				
			case "5":
				//add staff member
				System.out.println("Enter the information of a stuff member: ");
				System.out.println("\tName of the staff member: ");
				String staffName = input.nextLine();
				
				String staffId = null;
				int x = 0;
				while(x == 0) {
					System.out.println("\tID: ");
					staffId = input.nextLine();
					
					if(checkId(staffId) == true) {
						x = 1;
					}
					else {
						System.out.println("\tInvalid ID format.");
						System.out.println("\tMust be LetterLetterDigitDigitDigitDigit");
					}
				}
				
				Staff staff = new Staff(staffName, staffId);
				
				x = 0;
				while (x == 0) {
					System.out.println("\tDepartment: ");
					String department = input.nextLine();
					if(department.compareToIgnoreCase("Mathematics") == 0) {
						department = "Mathematics";
						staff.setDepartment(department);
						x = 1;
					} else if (department.compareToIgnoreCase("Engineering") == 0) {
						department = "Engineering";
						staff.setDepartment(department);
						x = 1;
					} else if (department.compareToIgnoreCase("English") == 0) {
						department = "English";
						staff.setDepartment(department);
						x = 1;
					} else {
						System.out.println("\t\"" + department + "\" is invalid");
					}
				}
				
				x = 0;
				while (x == 0) {
					System.out.println("\tStatus, Enter P for Part Time, or Enter F for Full Time: ");
					String status = input.nextLine();
					if(status.compareToIgnoreCase("P") == 0) {
						status = "Part Time";
						staff.setStatus(status);
						x = 1;
					} else if (status.compareToIgnoreCase("F") == 0) {
						status = "Full Time";
						staff.setStatus(status);
						x = 1;
					} else {
						System.out.println("\tInvalid Status format.");
					}
				}
				
				personList.add(staff);
				System.out.println("Staff member added! \n\n\n");
				
				break;
				
			case "6":
				//print staff info
				System.out.println("Enter the Staff's ID:");
				id = input.nextLine();
		        for (Person person : personList) {
		            if (person instanceof Staff) {
		                Staff staffObj = (Staff) person;
		                if (staffObj.getId().equals(id)) {
		                    //print staff info
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------");
		                	//I think all the fields should be private so we should change that eventually
		                	System.out.println(staffObj.getfullName() + "\t\t" + staffObj.getId());
		                	System.out.println(staffObj.getDepartment() + " Department, \t" + staffObj.getStatus());
		                	System.out.println("---------------------------------------------------------");
		                	System.out.println("----------------");
		                } else {
		                	System.out.println("No Staff member matched!");
		                }
		            }
		         }
		        
				break;
				
			case "7":
			    // delete entry
			    System.out.println("Enter the id of the person to delete: ");
			    id = input.nextLine();
			    boolean personFound = false;

			    Iterator<Person> iterator = personList.iterator();
			    while (iterator.hasNext()) {
			        Person person = iterator.next();
			        if (person.getId().equals(id)) {
			            iterator.remove();
			            personFound = true;
			            System.out.println("Person with ID " + id + " deleted.");
			            break; // Exit the loop once the person is found and deleted
			        }
			    }

			    if (!personFound) {
			        System.out.println("No person with ID " + id + " found.");
			    }
			    break;

			case "8":
				//Exit program
		        boolean validResponse = false;

		        do {
		            System.out.println("Would you like to create a report? (Y/N)");
		            String response = input.nextLine();

		            if (response.equalsIgnoreCase("Y")) {
		                // do the report
		                input.close();
		                System.exit(0);
		            } else if (response.equalsIgnoreCase("N")) {
		            	input.close();
		                System.exit(0);
		            } else {
		                System.out.println("Invalid entry. Please enter 'Y' or 'N'.");
		            }
		        } while (!validResponse);

			default: 
				System.out.println("\nInvalid entry- please try again\n\n\n");
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
	
//	private ArrayList<Person> personList;
	

	//things common for Student and Employee
	// NEed to be private
	private String fullName;
	private String id;
		
	public abstract void print(int credits);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfullName() {
		return fullName;
	}

	public void setfullName(String fullName) {
		this.fullName = fullName;
	}
	
	
	
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
		System.out.println(creditHours);
		System.out.println("TEST");
		//System.out.println(fullName);
			
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
		//TODO
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
	// Needs to be private.
	private String department;

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

	// Needs to be private
	private String rank;

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

	// Needs to be private.
	private String status;

	@Override
	public void print(int credits) {
		// TODO Auto-generated method stub
			
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

class genericException extends Exception{
	
}