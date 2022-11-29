package com.epam.rd.autocode.service;

import com.epam.rd.autocode.dao.EmployeeDaoImpl;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;

import java.util.Comparator;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
	EmployeeDaoImpl dao;
	
	public EmployeeServiceImpl() {
		this.dao = new EmployeeDaoImpl();
	}
	
	@Override
	public List<Employee> getAllSortByHireDate(Paging paging) {
		List<Employee> list = dao.getAll();
		list.sort(Comparator.comparing(Employee::getHired));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getAllSortByLastname(Paging paging) {
		List<Employee> list = dao.getAll();
		list.sort(Comparator.comparing(o -> o.getFullName().getLastName()));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getAllSortBySalary(Paging paging) {
		List<Employee> list = dao.getAll();
		list.sort(Comparator.comparing(Employee::getSalary));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getAllSortByDepartmentNameAndLastname(Paging paging) {
		List<Employee> list = dao.getAll();
		
		list.sort((o1, o2) -> {
			
			if (o1.getDepartment() == null) {
				return -1;
			}
			
			if (o2.getDepartment() == null) {
				return 1;
			}
			
			if (o1.getDepartment().getName().compareTo(o2.getDepartment().getName()) < 0) {
				return -1;
			} else if (o1.getDepartment().getName().compareTo(o2.getDepartment().getName()) > 0) {
				return 1;
			} else return Integer.compare(o1.getFullName().getLastName().compareTo(o2.getFullName().getLastName()), 0);
		});
		
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByDepartmentSortByHireDate(Department department, Paging paging) {
		List<Employee> list = dao.getByDepartment(department);
		list.sort(Comparator.comparing(Employee::getHired));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByDepartmentSortBySalary(Department department, Paging paging) {
		List<Employee> list = dao.getByDepartment(department);
		list.sort((o1, o2) -> {
			if (o1.getSalary().intValue() < o2.getSalary().intValue()) {
				return -1;
			} else if (o1.getSalary().intValue() > o2.getSalary().intValue()) {
				return 1;
			} else return o2.getFullName().getLastName().compareTo(o1.getFullName().getLastName());
		});
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByDepartmentSortByLastname(Department department, Paging paging) {
		List<Employee> list = dao.getByDepartment(department);
		list.sort(Comparator.comparing(employee -> employee.getFullName().getLastName()));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByManagerSortByLastname(Employee manager, Paging paging) {
		List<Employee> list = dao.getAllByManager(manager);
		list.sort(Comparator.comparing(employee -> employee.getFullName().getLastName()));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByManagerSortByHireDate(Employee manager, Paging paging) {
		List<Employee> list = dao.getAllByManager(manager);
		list.sort(Comparator.comparing(Employee::getHired));
		return getPagedList(list, paging);
	}
	
	@Override
	public List<Employee> getByManagerSortBySalary(Employee manager, Paging paging) {
		List<Employee> list = dao.getAllByManager(manager);
		list.sort(Comparator.comparing(Employee::getSalary));
		return getPagedList(list, paging);
	}
	
	@Override
	public Employee getWithDepartmentAndFullManagerChain(Employee employee) {
		return dao.getEmployeeCTE(employee);
	}
	
	@Override
	public Employee getTopNthBySalaryByDepartment(int salaryRank, Department department) {
		List <Employee> list = dao.getByDepartment(department);
		list.sort((o1, o2) -> {
			if (o1.getSalary().intValue() > o2.getSalary().intValue()) {
				return -1;
			} else if (o1.getSalary().intValue() < o2.getSalary().intValue()) {
				return 1;
			} else return o2.getFullName().getLastName().compareTo(o1.getFullName().getLastName());
		});
		
		return list.get(Math.min(salaryRank - 1, list.size() - 1));
	}
	
	private List<Employee> getPagedList(List<Employee> list, Paging paging) {
		int fromIndex = getByIndex(paging);
		int toIndex = Math.min(list.size(), getToIndex(paging));
		return list.subList(fromIndex, toIndex);
	}
	
	private int getByIndex(Paging paging) {
		return (paging.page - 1) * paging.itemPerPage;
	}
	
	private int getToIndex(Paging paging) {
		return getByIndex(paging) + paging.itemPerPage;
	}
}
