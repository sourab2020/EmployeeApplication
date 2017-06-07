package imcsServices.services;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import imcsDAO.dao.Employee;

public interface EmployeeOperations {
    void addEmployee(Employee e) throws SQLException;	

 
    
//    Employee findEmployeesByName(String name);

    boolean deleteEmployee(int empId) throws SQLException;

    boolean updateEmployeeById(int empId,Employee employee) throws ParseException;
    
	List<Employee> displayAllEmployees() throws SQLException;



	Employee findEmployee(int empId);



	HashSet<String> displayUniqueNames();



	HashMap<Integer, Integer> ageWiseCount() throws SQLException;



	//List<Employee> sort(Sorting selectedSortColoumn) ;



	HashSet<String> displayEmployee();



	//List<Employee> sort(Sorting selectedSortColoumn) throws SQLException;



	List<Employee> sort(Sorting selectedSortColoumn) throws SQLException;



//	List<Employee> sortingEmployeeByAge();


//	List<Employee> sortingEmployee(Sorting selection);

}
