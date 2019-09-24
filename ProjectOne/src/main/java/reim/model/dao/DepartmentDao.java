package reim.model.dao;

import java.util.List;

import reim.model.Department;



public interface DepartmentDao {
	
	public List<Department> getDepartment();
	
	public Department getDepartmentById(int id);
	
	public int createDepartment(Department d);
	//public Department createDepartmentWithFunction(Department d);
	
	public int updateDepartment(Department d);
	
	//public void increaseDepartmentBudgetWithFunction(Department d, double increase);
	
	public int deleteDepartment(int id);

}
