package final_project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.*;


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
	
	
	public static void main (String[] args) throws FileNotFoundException{
		//Array list to store our person objects
		ArrayList<Person> personList = new ArrayList<>(100);
		
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
			boolean found = false;
		
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
				
				break;
				
			case "3":
				System.out.println("Enter the student's ID:");
				String id = input.nextLine();
		        for (Person person : personList) {
		            if (person instanceof Student) {	
		                Student studentObj = (Student) person;
		                if (studentObj.getId().equals(id)) {
		                	found = true;
		                	studentObj.print();
		                	break;
		                } 
		            }
		         }
                if(!found) {
		        	System.out.println("No Student matched!\n\n\n");
                } else {
                	//reset found
                	found = false;
                }
		        
				break;
				
			case "4":
				System.out.println("Enter the Facultys's ID:");
				id = input.nextLine();
		        for (Person person : personList) {
		            if (person instanceof Faculty) {
		                Faculty facultyObj = (Faculty) person;
		                if (facultyObj.getId().equals(id)) {
		                	found = true;
		                	facultyObj.print();
		                } 
		            }
		         }
		        if(!found)
		        {		        
		        	System.out.println("No Faculty matched!\n\n\n");
		        
		        } else {
		        	//reset found
		        	found = false;	
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
		                	found = true;
		                    //print staff info
		                	break;
		                } 
		            }
		         }
		        
		        if(!found)
		        {		        
		        	System.out.println("No Staff matched!\n\n\n");
		        
		        } else {
		        	//reset found
		        	found = false;	
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
			    // Exit program
			    PrintWriter writer = new PrintWriter("report.txt");
			    
			    // Ask if the user wants to create a report
			    System.out.println("\nWould you like to create the report? (Y/N) :");
			    String response = input.nextLine();
			    
			    if (response.compareToIgnoreCase("Y") == 0) {
			    	LocalDate date = LocalDate.now();
			    	
	            	writer.print("Report created on " + date + "\n");
	            	writer.print("***************************");
	            	 writer.println("Faculty Members \n");
	                 writer.println("-----------");
	                 int count1 = 0;
	                 for (Person facultyPrint : personList) {
	                	    if (facultyPrint instanceof Faculty) {
	                	        writer.printf("%d. %s%n", count1, facultyPrint.getfullName());
	                	        writer.printf("ID: %s%n", facultyPrint.getId());

	                	        // Cast facultyPrint to Faculty to access specific methods
	                	        Faculty faculty1 = (Faculty) facultyPrint;
	                	        writer.printf("%s, %s%n", faculty1.getRank(), faculty1.getDepartment());
	                	        writer.println();

	                	        count1++;
	                	    }
	                	}

	            	 writer.println("Staff  \n");
	                 writer.println("-----------");
	                 int count2 = 0;
	                 for (Person staffPrint : personList) {
	                	    if (staffPrint instanceof Staff) {
	                	        writer.printf("%d. %s%n", count1, staffPrint.getfullName());
	                	        writer.printf("ID: %s%n", staffPrint.getId());

	                	        // Cast facultyPrint to Faculty to access specific methods
	                	        Staff staff1 = (Staff) staffPrint;
	                	        writer.printf("%s, %s%n", staff1.getDepartment(), staff1.getStatus());
	                	        writer.println();

	                	        count2++;
	                	    }
	                	}


			        int q = 0;
			        while (q == 0) {
			            // Ask how the user wants to sort students
			            System.out.println("How do you want to sort students?");
			            System.out.println("1. Sort by descending GPA");
			            System.out.println("2. Sort by name");
			            String sort = input.nextLine();

			            if (sort.compareToIgnoreCase("1") == 0) {
			                // Filter out only Student objects
			                ArrayList<Student> studentList = new ArrayList<>();
			                for (Person person : personList) {
			                    if (person instanceof Student) {
			                        studentList.add((Student) person);
			                    }
			                }

			                // Sort the ArrayList of students in descending order based on GPA
			                Collections.sort(studentList, Comparator.comparingDouble(Student::getGpa).reversed());

			                // Clear the original personList of Student objects
			                personList.removeAll(studentList);

			                // Add the sorted studentList back to the original personList
			                personList.addAll(studentList);

			                q = 1;
			            	 writer.println("Students \n");
			                 writer.println("-----------");
			                 int count = 1;
			                 for (Student studentt : studentList) {
			                     writer.printf("%d. %s%n", count, studentt.getfullName());
			                     writer.printf("ID: %s%n", studentt.getId());
			                     writer.printf("Gpa: %.2f%n", studentt.getGpa());
			                     writer.printf("Credit hours: %d%n", studentt.getCreditHours());
			                     writer.println();
			                     count++;
			                 }
			            	
			                input.close();
			                writer.close();
			                System.exit(0);
			                
			            } else if (sort.compareToIgnoreCase("2") == 0) {
			                ArrayList<Student> studentList = new ArrayList<>();
			                for (Person person : personList) {
			                    if (person instanceof Student) {
			                        studentList.add((Student) person);
			                    }
			                }
			                // Sort the entire personList by name
			                Collections.sort(studentList);
			                // Clear the original personList of Student objects
			                personList.removeAll(studentList);

			                // Add the sorted studentList back to the original personList
			                personList.addAll(studentList);
			                q = 1;
			            } else {
			                System.out.println("\tInvalid Entry");
			                System.out.println("\tPlease Enter 1 or 2\n");
			            }
			        }
			    } else if (response.compareToIgnoreCase("N") == 0) {
			    	System.exit(0);
			    } else {
			        System.out.println("\tInvalid Entry");
			        System.out.println("\tPlease Enter \"Y\" or \"N\"\n");
			    }
			    break;

				
			default: 
				System.out.println("\nInvalid entry- please try again\n\n\n");
			}
		}
	}
}


//___________________
abstract class Person {
	
	public Person(String fullName, String id) {
		super();
		this.fullName = fullName;
		this.id = id;
	}
	
	//things common for Student and Employee
	private String fullName;
	private String id;
		
	public abstract void print();

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
class Student extends Person implements Comparable<Student>{
	
	public Student(String fullName, String id) {
		super(fullName, id);
		}
	

	private double gpa;
	private int creditHours;
	private static int fee = 52;
	private static double creditPrice = 236.45;
	
	private double discount() {
		double discount = 0.00; 
		if(getGpa() >= 3.85) {
			discount = 0.25 * (creditPrice * getCreditHours() + fee);
		}
		
		return discount;
	}
//		
	//Student should get 25% discount if the GPA is higher than 3.85
	@Override
	public void print() {
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------");
    	//I think all the fields should be private so we should change that eventually
    	System.out.println(getfullName() + "\t\t" + getId());
    	System.out.println("Credit Hours: " + getCreditHours() + " ($236.45/credit hour)");
    	System.out.println("Fees: $" + fee);
    	System.out.printf("\nTotal payment (after discount):  %.2f", (getCreditHours() * creditPrice) + fee - discount());
    	System.out.printf("\n($%.2f discount applied)\n", discount());
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------\n\n\n");
			
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
	
	@Override
    public int compareTo(Student otherStudent) {
        // Compare students based on GPA
        return Double.compare(otherStudent.getGpa(), this.getGpa());
    }
		
}

//______________________
//abstract class inherits from Person
abstract class Employee extends Person{
	
	public Employee(String fullName, String id) {
		super(fullName, id);
	}

	//things common for staff and faculty
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
	}

	// Needs to be private
	private String rank;

    @Override
    public void print() {
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------");
    	//I think all the fields should be private so we should change that eventually
    	System.out.println(getfullName() + "\t\t" + getId());
    	System.out.println(getDepartment() + " Department,  \t" + getRank());
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------\n\n\n");
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
	}

	// Needs to be private.
	private String status;

	@Override
	public void print() {
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------");
    	System.out.println(getfullName() + "\t\t" + getId());
    	System.out.println(getDepartment() + " Department, \t" + getStatus());
    	System.out.println("---------------------------------------------------------");
    	System.out.println("----------------");
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