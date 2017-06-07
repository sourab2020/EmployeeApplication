package imcsServices.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.omg.CORBA.portable.InputStream;

import imcsDAO.dao.Employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class JDBCEmployeeOperationsImpl implements EmployeeOperations {

	//private List<Employee> employeeList = null;
	Connection con;
	PreparedStatement pStatement;

	//private static JDBCEmployeeOperationsImpl connectionFactory;
    

	
	public JDBCEmployeeOperationsImpl() throws ClassNotFoundException, IOException {
		Properties credentialsProps = null;
		credentialsProps = new Properties();
		java.io.InputStream stream = ClassLoader.getSystemResourceAsStream("./credentials.properties");
	    if(stream ==null){
	    	System.out.println("connection");
	    }
		/*	Class.forName("com.mysql.jdbc.Driver");
		try {
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		credentialsProps.load(stream);
		String url = credentialsProps.getProperty("connectionUrl");
		String user = credentialsProps.getProperty("userName");
		String password = credentialsProps.getProperty("password");
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	public void addEmployee(Employee e) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inserting Record...");
		
		ResultSet rs =	null;

		try {
			
			pStatement = con.prepareStatement("insert into employee (empid, empName, empSalary,empAge,empDate) values (?, ?, ?,?,?)");
			
			pStatement.setInt(1,e.getNumber());
			pStatement.setString(2,e.getName());
			pStatement.setDouble(3, e.getSalary());
			pStatement.setInt(4,e.getAge());
			pStatement.setDate(5, e.getDatestring());
			pStatement.executeUpdate();

		} catch (SQLException p) {
			p.printStackTrace();
		} finally {			
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		}
	
	public Employee findEmployee(int empId) {
		
		Employee e = null;
		ResultSet rs =	null;
		try {
			pStatement = con.prepareStatement("select * from employee where empid=?");
			pStatement.setInt(1, empId);
			rs = pStatement.executeQuery();
			while(rs.next()){
				e=new Employee(Integer.parseInt(rs.getString("empid")), rs.getString("empName"), Double.parseDouble(rs.getString("empSalary")), Integer.parseInt(rs.getString("empAge")), rs.getDate("empDate"));
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		if(e == null){
			throw new EmployeeNotFound("Employee ID not found Exception");
		}
		return e;
	}

//	@Override
/*	public Employee displayEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}*/
    
	
	public boolean deleteEmployee(int empId) {
		// TODO Auto-generated method stub
		boolean x = false;
		ResultSet rs =	null;
		int y;
		try {
			pStatement = con.prepareStatement("delete from employee where empId = ? ");
			
			pStatement.setInt(1, empId);
			y=pStatement.executeUpdate();
			if(y>0){
				x=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}	
		if(x==false){
			throw new EmployeeNotFound("Employee is not found for deletion Exception");
		}
		return x;
		
	}

	public boolean updateEmployeeById(int empid, Employee employee) throws ParseException {
		// TODO Auto-generated method stub
		boolean x=false;
		
		Employee e = EmployeeUtil.infoUpdateEmployee("update");
	
		try {
			pStatement = con.prepareStatement("update employee set empName = ?,empAge=?,empSalary=? where empId = ?");
			
			pStatement.setString(1, e.getName());
			pStatement.setInt(2, e.getAge());
			pStatement.setDouble(3, e.getSalary());
			pStatement.setInt(4, empid);
			pStatement.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		x=true;
	
	return x;

	}
	
	public HashSet<String> displayEmployee(){
	
			// TODO Auto-generated method stub
			HashSet<String> employeeNames = new HashSet<String>();
			ResultSet rs = null;
			
			try{
				pStatement = con.prepareStatement("select empName,empDate from employee where NOW()-empDate>5*365");
				rs = pStatement.executeQuery();
				while(rs.next()){
					employeeNames.add(rs.getString("empName"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return employeeNames;
		}
	

	public HashSet<String> displayUniqueNames() {
		// TODO Auto-generated method stub
		HashSet<String> employeeNames = new HashSet<String>();
		ResultSet rs = null;
		
		try{
			pStatement = con.prepareStatement("select distinct(empName) from employee");
			rs = pStatement.executeQuery();
			while(rs.next()){
				employeeNames.add(rs.getString("empName"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return employeeNames;
	}

	public HashMap<Integer, Integer> ageWiseCount() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		ResultSet rs = null;
		try{
			pStatement = con.prepareStatement("select empAge, count(empAge) As agecount from employee group by empAge");
			
			rs = pStatement.executeQuery();
			while(rs.next()){
				hashMap.put(Integer.parseInt(rs.getString("empAge")), Integer.parseInt(rs.getString("agecount")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rs.close();
		}
		return hashMap;
	}

	public List<Employee> displayAllEmployees() throws SQLException {
		// TODO Auto-generated method stub
		
		System.out.println("Displaying Records...");
		ResultSet rs =	null;
		ArrayList<Employee> jdbcEmployeeDisplay = new ArrayList<Employee>();
		
		try {
			pStatement = con.prepareStatement("select * from employee");
			rs =	pStatement.executeQuery();
			
			while(rs.next()) {
				jdbcEmployeeDisplay.add(new Employee(Integer.parseInt(rs.getString("empid")), rs.getString("empName"), Double.parseDouble(rs.getString("empSalary")),Integer.parseInt(rs.getString("empAge")), rs.getDate("empDate")));
			}
			
			//System.gc();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return jdbcEmployeeDisplay;
	}

	public List<Employee> sort(Sorting selectedSortColumn) throws SQLException{
		List<Employee> sortedEmployeeList = null;
		switch(selectedSortColumn){
			case sortbyId:
				sortedEmployeeList=sortBySalary();
				break;				
			case sortbySalary:
				sortedEmployeeList=sortByID();
				break;
		}
		return sortedEmployeeList;
	}
	
	private ArrayList<Employee> sortByID() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ResultSet rs = null;
		try{
			pStatement = con.prepareStatement("select * from employee order by empid asc");
			rs = pStatement.executeQuery();
			while(rs.next()){
				Employee e=new Employee(Integer.parseInt(rs.getString("empid")), rs.getString("empName"), Double.parseDouble(rs.getString("empSalary")), Integer.parseInt(rs.getString("empAge")), rs.getDate("empDate"));
				employees.add(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rs.close();
		}
		return employees;
		
	}

	private ArrayList<Employee> sortBySalary() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ResultSet rs = null;
		try{
			pStatement = con.prepareStatement("select * from employee order by empSalary asc");
			rs = pStatement.executeQuery();
			while(rs.next()){
				Employee e=new Employee(Integer.parseInt(rs.getString("empid")), rs.getString("empName"), Double.parseDouble(rs.getString("empSalary")), Integer.parseInt(rs.getString("empAge")), rs.getDate("empDate"));
				employees.add(e);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			rs.close();
		}
		return employees;
	}
	protected void finalize() throws Throwable {
		System.out.printf("Inside finalyze");
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.finalize();
	}


}
