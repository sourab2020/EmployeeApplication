package imcsServices.services;

import java.io.PrintStream;
public class EmployeeNotFound extends  RuntimeException{

	public EmployeeNotFound(String message) {
		super(message);
	}

	public EmployeeNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(String message, Throwable arg1) {
		super(message, arg1);
		// TODO Auto-generated constructor stub
	}

}
