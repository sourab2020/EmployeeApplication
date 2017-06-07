package imcsServices.services;

import java.util.Scanner;

public enum Sorting {
	sortbyId(1),sortbySalary(2);
	public int operationIndex;
	Sorting(int operationIndex ){
		this.operationIndex= operationIndex;
	}
	
	public static Sorting getSortColumn(int givenIndex) {
		
		for(Sorting depCol : Sorting.values()) {
			if (depCol.operationIndex == givenIndex) {
				return depCol;
			}
		}
		
		return null;
	}

}
