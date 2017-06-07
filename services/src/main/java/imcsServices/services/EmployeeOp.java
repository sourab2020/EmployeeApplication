package imcsServices.services;

public enum EmployeeOp {
		add(1),delete(2),update(3),findbyId(4),sort(5),displayUniqueName(6),ageWiseCount(7),displayall(8),displayFive(9),exit(10);
		
		public int operationIndex;
		EmployeeOp(int operationIndex ){
			this.operationIndex= operationIndex;
		}
		
		public static EmployeeOp getOpColumn(int givenIndex) {
			
			for(EmployeeOp depCol : EmployeeOp.values()) {
				if (depCol.operationIndex == givenIndex) {
					return depCol;
				}
			}
			
			return null;
		}
	}

