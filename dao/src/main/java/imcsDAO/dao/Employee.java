package imcsDAO.dao;

import java.sql.Date;

public class Employee {
	private int number;
	private int age;
	private String name;
	private double salary;
	private Date datestring;
	
	
	public Date getDatestring() {
		return datestring;
	}
	public void setDatestring(Date datestring) {
		this.datestring = datestring;
	}

	@Override
	public String toString() {
		return "Employee [number=" + number + ", age=" + age + ", name=" + name + ", salary=" + salary + ", datestring="
				+ datestring + "]";
	}
	public Employee(){}
	public Employee(int number,String name,double salary,int age, Date sqlDate){
		this.number=number;
		this.name=name;
		this.salary=salary;
		this.age=age;
		this.datestring = sqlDate;
	}
	public Employee(String name, Double salary, Integer age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}
	

}
