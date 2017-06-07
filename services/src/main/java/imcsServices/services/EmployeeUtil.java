package imcsServices.services;

import java.util.Scanner;

import imcsDAO.dao.Employee;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class EmployeeUtil {

	public static void EmployeeInfoDisplay(Employee e1) {
		System.out.println("The Employee Number is:"+e1.getNumber());
		System.out.println("The Employee Name is:"+e1.getName());
		System.out.println("The Employee Salary is"+e1.getSalary());	   
	  }

	 public static Double compareEmployee(Employee e1, Employee e2){
		  
		 return e1.getSalary()>=e2.getSalary()?e1.getSalary():e2.getSalary();
	 }
	 
	 public static Integer comapreAge(Employee e1, Employee e2){
		  return e1.getAge()>= e2.getAge()?e1.getAge():e2.getAge();
	 }
	 
	 public static void UpdateSalary(Employee e1){
	 
		 if (e1.getSalary()< 10000 && e1.getAge()>35){
		     e1.setSalary((0.15*e1.getSalary())+e1.getSalary());
		 }
		 else if(e1.getSalary()< 15000 && e1.getAge()>45){
			  e1.setSalary((0.20*e1.getSalary())+e1.getSalary());
		 }
		 else if(e1.getSalary()< 20000 && e1.getAge()>55){
			   e1.setSalary((0.20*e1.getSalary())+e1.getSalary());
		 }
		
			 
	   }
	 
	 	public static double calculateDaHra(Employee e1){
		 Double da,hra,grossSalary;
		 if(e1.getSalary()<10000){
			 da = 0.08 *e1.getSalary();
			 hra = 0.15 * e1.getSalary();
			 grossSalary = e1.getSalary()+da+hra;
		 }
		 else if(e1.getSalary()<20000){
			 da = 0.1 *e1.getSalary();
			 hra = 0.20 * e1.getSalary();
			 grossSalary =e1.getSalary()+da+hra; 
		 }
		 else if(e1.getSalary()<30000 && e1.getAge()>=40){
			 da = 0.15 *e1.getSalary();
			 hra = 0.27 * e1.getSalary();
			 grossSalary=e1.getSalary()+da+hra; 
		 }
		 else if(e1.getSalary()<30000 && e1.getAge()<40){
			 da = 0.13 *e1.getSalary();
			 hra = 0.25 * e1.getSalary();
			 grossSalary=e1.getSalary()+da+hra; 
		 }
		 else{
			 da = 0.17 *e1.getSalary();
			 hra = 0.30 * e1.getSalary();
			 grossSalary = e1.getSalary()+da+hra;
		 }
		return grossSalary;	 
	 }
	 
		public static int readNumberofEmployessFromUser(){
			Scanner scn = new Scanner(System.in);
			System.out.println("Please enter the number of Employees you want to create");
			return scn.nextInt();
		}
		
		public static Employee addingEmployeeDetails() throws ParseException{
			Scanner scn = new Scanner(System.in);
			Employee employee = null;
			try{
			System.out.println("Please enter the employee name:");
			String name = scn.next();
			System.out.println("Please enter the employee Number:");
			int number = scn.nextInt();
			System.out.println("Please enter the employee Age:");
			int age = scn.nextInt();
			System.out.println("Please enter the employee Salary:");
			Double salary = scn.nextDouble();
			System.out.println("Please enter the employee Join Date:");
			String date = scn.next();
			String dateFormat = "dd-MM-yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			java.util.Date utildate=sdf.parse(date);
			long ms=utildate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(ms);
		    employee = new Employee(number, name, salary, age,sqlDate);
			
			}
			catch(InputMismatchException e){
				System.out.println("Not valid datatype"+e);
			}
			return employee;
		
		}
	 public static int detailsEmployeeById(){
		 Scanner scn = new Scanner(System.in);
			System.out.println("Please enter the id of the employee you want to display");
			return scn.nextInt();
	 }
	 
	 public static String detailsEmployeeByName(){
		 Scanner scn = new Scanner(System.in);
			System.out.println("Please enter the name of the employee you want to display");
			return scn.nextLine();
	 }
	 
	 public static Employee infoUpdateEmployee(String option) throws ParseException{
		 Employee employee=null;
		
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Enter the employee Salary");
			double upSalary = scan.nextDouble();
			
			System.out.println("Enter the employee name");
			String upName = scan.next();
			
			System.out.println("Enter the age");
			int upAge = scan.nextInt();
			
			System.out.println("Please enter the employee Join Date:");
			String date = scan.next();
			String dateFormat = "dd-MM-yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			java.util.Date utildate=sdf.parse(date);
			long ms=utildate.getTime();
			java.sql.Date sqlDate = new java.sql.Date(ms);
			
			if(option.equals("insert")){
				
				System.out.println("Please enter the employee Number:");
				int number = scan.nextInt();
				 employee = new Employee(number,upName,upSalary,upAge,sqlDate);
			}else{
				employee = new Employee(upName,upSalary,upAge);
			}

			
			
			
			return employee;
	 }
		
	 public static int detailsForUpdatingEmployee(){
			Scanner scn = new Scanner(System.in);
			System.out.println("Please enter the id of the employee you want to update");
			return scn.nextInt();
		}
	 
		public static int detailsForDeletingEmployee(){
			Scanner scn = new Scanner(System.in);
			System.out.println("Please enter the id of the employee you want to delete");
			return scn.nextInt();
		}
		
	 public static Employee readinfo(){
		    Employee employee = new Employee();
			Integer empNo;
			String eName;
			Double eSalary;
			Integer eAge;
		 Scanner scan = new Scanner(System.in);
			System.out.println("Enter the Employee Number");
			empNo = scan.nextInt();
			
			System.out.println("Enter the employee Salary");
			eSalary = scan.nextDouble();
			
			System.out.println("Enter the employee name");
			eName = scan.next();
			
			System.out.println("Enter the age");
			eAge = scan.nextInt();
			
			employee.setNumber(empNo);
			employee.setName(eName);
			employee.setSalary(eSalary);
			employee.setAge(eAge);
			
            return employee;
	 }
}

