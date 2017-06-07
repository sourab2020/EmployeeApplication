package imcs.employee;


	import java.util.Scanner;

	import javax.swing.text.html.HTMLDocument.Iterator;

import imcsDAO.dao.Employee;
import imcsServices.services.EmployeeNotFound;
import imcsServices.services.EmployeeOp;
import imcsServices.services.EmployeeOperations;
import imcsServices.services.EmployeeUtil;
import imcsServices.services.JDBCEmployeeOperationsImpl;
import imcsServices.services.Sorting;

import java.util.List;
	import java.util.Random;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.io.DataInputStream;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.BufferedOutputStream;
	import java.io.BufferedReader;
	import java.io.InputStream;
	import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;



		
		public class JDBCEmployeeOperationsApp {
			// TODO Auto-generated method stub

			EmployeeOperations employeeOperations;
		public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, ParseException{
			Scanner scan = new Scanner(System.in);
			 JDBCEmployeeOperationsApp collectionEmployeeOperations = new JDBCEmployeeOperationsApp();
			 collectionEmployeeOperations.setNumberOfemployees();
			String keep = "y";
			
			do{
				EmployeeOp selectedDepColumn = getSelectedDepColumn();
			switch (selectedDepColumn) {
				case add:  
					 collectionEmployeeOperations.creatingEmployee();  
					 break;
				case delete:
					  
					  collectionEmployeeOperations.deletingEmployee();
					  break;
				case update :
					collectionEmployeeOperations.updateEmployee();			 
					  break;
				case findbyId:
					
					collectionEmployeeOperations.findById();
					break;

				case sort:
					System.out.println("Sorting");
				    Sorting sortcolumn = getSelectedsortColumn();
				    collectionEmployeeOperations.sortingByColoumn(sortcolumn);
				    
					 break;
				case displayUniqueName:
					collectionEmployeeOperations.displayUniqueNames();
					break;
				case ageWiseCount:
					collectionEmployeeOperations.ageWiseCount();
					break;
				case displayall:
					collectionEmployeeOperations.displayAllEmployees();
					break;
				case displayFive:
					collectionEmployeeOperations.dispalyFive();
					break;
				case exit:
					collectionEmployeeOperations.existFile();
					break;
			}

	        System.out.print ("Continue  (y/n)? ");
	        keep = scan.next();
	        
			
			}while (keep.equals("y"));
			
		}
		
		
		private void setNumberOfemployees() throws IOException, ClassNotFoundException{
			employeeOperations = new JDBCEmployeeOperationsImpl();

		}

		private static EmployeeOp getSelectedDepColumn() {
			EmployeeOp selectedDepColumn;
			
			for(EmployeeOp empCol : EmployeeOp.values()) {
				System.out.println(empCol.operationIndex + " : " + empCol.name());
			}
			
			System.out.println("Select the column: ");
			int selectedIndex = new Scanner(System.in).nextInt();
			
			selectedDepColumn = EmployeeOp.getOpColumn(selectedIndex);
			return selectedDepColumn;
			
		}
		
			private static Sorting getSelectedsortColumn() {
				Sorting selectedDepColumn;
				
				for(Sorting empCol : Sorting.values()) {
					System.out.println(empCol.operationIndex + " : " + empCol.name());
				}
				
				System.out.println("Select the column: ");
				int selectedIndex = new Scanner(System.in).nextInt();
				
				selectedDepColumn = Sorting.getSortColumn(selectedIndex);
				return selectedDepColumn;
			
				
		}
	

		 private void creatingEmployee() throws ParseException{

				Employee employee = EmployeeUtil.infoUpdateEmployee("insert");
				try{
					employeeOperations.addEmployee(employee);
					System.out.println("Employee Details re added into the DataBase");
				}
				catch (Exception e) {
					System.out.println(e);
				}
				
			}
		
		
		private void updateEmployee() throws ParseException{
			boolean x=false;
			try{
				int empId = EmployeeUtil.detailsForUpdatingEmployee();
				Employee employee=employeeOperations.findEmployee(empId);
				x =employeeOperations.updateEmployeeById(empId,employee);
			}

			catch(EmployeeNotFound e){
					System.out.println("Employee ID is not found for Updation");
				}
			if(x==true){
				System.out.println("Employee Details are Updated");
			}
		
		}

		
		private void findById(){
			int displayId = EmployeeUtil.detailsEmployeeById();
			Employee employeeDisplayById = null;
				 employeeDisplayById = employeeOperations.findEmployee(displayId);
				
			if(employeeDisplayById!=null){
			System.out.println("Employee Number: "+employeeDisplayById.getNumber());
			System.out.println("Employee Name: "+employeeDisplayById.getName());
			System.out.println("Employee Age: "+employeeDisplayById.getAge());
			System.out.println("Employee Salary: "+employeeDisplayById.getSalary());
			}
	}
				
			
			
		
		
		private void deletingEmployee() throws SQLException{
		
			int id = EmployeeUtil.detailsForDeletingEmployee();
			boolean x=false;
			x=employeeOperations.deleteEmployee(id);
			
			
			
			if(x==true){
			System.out.println("Employee Details are Deleted");
			}
		}
		
		private void displayUniqueNames(){
			HashSet<String> employeeNames=employeeOperations.displayUniqueNames();
			System.out.println("UNIQUE NAMES");
			java.util.Iterator<String> it = employeeNames.iterator();
			while(it.hasNext()){
				System.out.println(it.next());			
			}
		}
		
		private void dispalyFive(){
			HashSet<String> employeeNames=employeeOperations.displayEmployee();
			System.out.println("FIVE NAMES");
			java.util.Iterator<String> it = employeeNames.iterator();
			while(it.hasNext()){
				System.out.println(it.next());			
			}
		}
		
		private void ageWiseCount() throws SQLException {
			HashMap<Integer, Integer> hashMap=employeeOperations.ageWiseCount();
			for(Integer i: hashMap.keySet()){
				System.out.println("Age: "+i+" number: "+hashMap.get(i));
			}
		}

	    private void sortingByColoumn(Sorting selectedSortColoumn) throws SQLException{
	    	List<Employee> employeeList = employeeOperations.sort(selectedSortColoumn);
	    	for(Employee employee:employeeList){
	    		System.out.println(employee);
	    	}
	    }
	    
	    private void displayAllEmployees() throws SQLException{
	    	List<Employee> employeeDisplay = employeeOperations.displayAllEmployees();
	    	if(employeeDisplay.size()!=0){
	    		
	    		for(int i=0;i<employeeDisplay.size();i++){
	    			
	    			System.out.println("EmpId: "+employeeDisplay.get(i).getNumber()+" ");
	    			System.out.println("EmpName: "+employeeDisplay.get(i).getName()+" ");
	    			System.out.println("EmpSalary: "+employeeDisplay.get(i).getSalary()+" ");
	    			System.out.println("EmpAge: "+employeeDisplay.get(i).getAge()+" ");
	    			System.out.println("\n");
	    		}
	    	}
	    }
	    private void existFile() throws SQLException{
	    	System.out.println("Thank you for using the application");
	    	System.exit(0);
	    }
}
