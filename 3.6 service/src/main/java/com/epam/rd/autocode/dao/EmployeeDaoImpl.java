package com.epam.rd.autocode.dao;

import com.epam.rd.autocode.ConnectionSource;
import com.epam.rd.autocode.domain.Department;
import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl {
	
	private static final String MANAGER_ID = "MID";
	private static final String EMPLOYEE_ID = "EID";
	private static final String EMPLOYEE_FIRST_NAME = "EFN";
	private static final String EMPLOYEE_LAST_NAME = "ELN";
	private static final String EMPLOYEE_MIDDLE_NAME = "EMN";
	private static final String MANAGER_FIRST_NAME = "MFN";
	private static final String MANAGER_LAST_NAME = "MLN";
	private static final String MANAGER_MIDDLE_NAME = "MMN";
	private static final String EMPLOYEE_POSITION = "EP";
	private static final String MANAGER_POSITION = "MP";
	private static final String EMPLOYEE_HIRE_DATE = "EH";
	private static final String MANAGER_HIRE_DATE = "MH";
	private static final String EMPLOYEE_SALARY = "ES";
	private static final String MANAGER_SALARY = "MS";
	private static final String DEPARTMENT_ID = "ED";
	private static final String DEPARTMENT_NAME = "EDN";
	private static final String DEPARTMENT_LOCATION = "EDL";
	private static final String MANAGER_DEPARTMENT_ID = "MD";
	private static final String MANAGER_DEPARTMENT_LOCATION = "MDL";
	private static final String MANAGER_DEPARTMENT_NAME = "MDN";
	
	private static final String GET_ALL = "SELECT e.id AS EID, e.firstname AS EFN, e.lastname AS ELN, " +
			"e.middlename AS EMN, e.position AS EP, e.manager AS EM, e.hiredate AS EH, e.salary AS ES, " +
			"e.department AS ED, ed.name AS EDN, ed.location AS EDL, " +
			"m.id AS MID, m.firstname AS MFN, m.lastname AS MLN, m.middlename AS MMN, m.position AS MP, m.manager " +
			"AS MM, m.hiredate AS MH, m.salary AS MS, m.department AS MD, md.name AS MDN, md.location AS MDL " +
			"FROM EMPLOYEE AS e " +
			"LEFT OUTER JOIN EMPLOYEE AS m ON e.MANAGER = m.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS ed ON e.department = ed.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS md ON m.department = md.ID";
	
	private static final String GET_ALL_BY_DEP = "SELECT e.id AS EID, e.firstname AS EFN, e.lastname AS ELN, " +
			"e.middlename AS EMN, e.position AS EP, e.manager AS EM, e.hiredate AS EH, e.salary AS ES, " +
			"e.department AS ED, ed.name AS EDN, ed.location AS EDL, " +
			"m.id AS MID, m.firstname AS MFN, m.lastname AS MLN, m.middlename AS MMN, " +
			"m.position AS MP, m.manager AS MM, m.hiredate AS MH, m.salary AS MS, m.department AS MD, " +
			"md.name AS MDN, md.location AS MDL " +
			"FROM EMPLOYEE AS e " +
			"LEFT OUTER JOIN EMPLOYEE AS m ON e.MANAGER = m.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS ed ON e.department = ed.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS md ON m.department = md.ID " +
			"WHERE e.department = ?";
	
	private static final String GET_ALL_BY_MANAGER = "SELECT e.id AS EID, e.firstname AS EFN, " +
			"e.lastname AS ELN, e.middlename AS EMN, e.position AS EP, e.manager AS EM, e.hiredate AS EH, " +
			"e.salary AS ES, e.department AS ED, ed.name AS EDN, ed.location AS EDL, " +
			"m.id AS MID, m.firstname AS MFN, m.lastname AS MLN, m.middlename AS MMN, " +
			"m.position AS MP, m.manager AS MM, m.hiredate AS MH, m.salary AS MS, m.department AS MD, " +
			"md.name AS MDN, md.location AS MDL " +
			"FROM EMPLOYEE AS e " +
			"LEFT OUTER JOIN EMPLOYEE AS m ON e.MANAGER = m.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS ed ON e.department = ed.ID " +
			"LEFT OUTER JOIN DEPARTMENT AS md ON m.department = md.ID " +
			"WHERE e.MANAGER = ?";
	
	private static final String GET_MANAGER_CTE =
			"WITH RECURSIVE managers AS (SELECT e.id, e.firstname, e.lastname, e.middlename, e.position, e.manager, " +
					"e.hiredate, e.salary, e.department AS ED, d.name AS EDN, d.location AS EDL " +
					"FROM employee e " +
					"JOIN DEPARTMENT d ON d.id = e.department " +
					"WHERE e.ID = ? " +
					"UNION " +
					"SELECT e.id AS EID, e.firstname AS EF, e.lastname AS EL, e.middlename AS EM, " +
					"e.position AS EP, e.manager AS EM, e.hiredate AS EH, e.salary AS ES, e.department AS ED, " +
					"d.name AS EDN, d.location AS EDL " +
					"FROM employee e " +
					"LEFT JOIN DEPARTMENT d ON d.id = e.department " +
					"JOIN managers m ON e.id = m.manager " +
					") " +
					"SELECT managers.id AS EID, firstname AS EFN, lastname AS ELN, middlename AS EMN, " +
					"position AS EP, manager, hiredate AS EH, salary AS ES, ED, EDN, EDL " +
					"FROM managers";
	
	
	private static ConnectionSource connectionSource;
	
	public EmployeeDaoImpl() {
		connectionSource = ConnectionSource.instance();
	}
	
	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<>();
		Employee employee;
		
		try (Connection connection = connectionSource.createConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				employee = getEmployee(resultSet);
				list.add(employee);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	private Employee getEmployee(ResultSet resultSet) throws SQLException {
		BigInteger id = getId(resultSet);
		FullName fullName = getFullName(resultSet);
		Position position = getPosition(resultSet);
		LocalDate hireDate = getHireDate(resultSet);
		BigDecimal salary = getSalary(resultSet);
		Employee manager = getOneManager(resultSet);
		Department department = getDepartment(resultSet);
		
		return new Employee(id, fullName, position, hireDate, salary, manager, department);
	}
	
	private Employee getOneManager(ResultSet resultSet) throws SQLException {
		if (resultSet.getInt(MANAGER_ID) == 0) {
			return null;
		}
		BigInteger id = getManId(resultSet);
		FullName fullName = getManFullName(resultSet);
		Position position = getManPosition(resultSet);
		LocalDate hireDate = getManHireDate(resultSet);
		BigDecimal salary = getManSalary(resultSet);
		Employee manager = null;
		Department department = getManDepartment(resultSet);
		return new Employee(id, fullName, position, hireDate, salary, manager, department);
	}
	
	private BigInteger getId(ResultSet resultSet) throws SQLException {
		return BigInteger.valueOf(resultSet.getInt(EMPLOYEE_ID));
	}
	
	private BigInteger getManId(ResultSet resultSet) throws SQLException {
		return BigInteger.valueOf(resultSet.getInt(MANAGER_ID));
	}
	
	private FullName getFullName(ResultSet resultSet) throws SQLException {
		return new FullName((resultSet.getString(EMPLOYEE_FIRST_NAME)), (resultSet.getString(EMPLOYEE_LAST_NAME)),
				(resultSet.getString(EMPLOYEE_MIDDLE_NAME)));
	}
	
	private FullName getManFullName(ResultSet resultSet) throws SQLException {
		return new FullName((resultSet.getString(MANAGER_FIRST_NAME)), (resultSet.getString(MANAGER_LAST_NAME)),
				(resultSet.getString(MANAGER_MIDDLE_NAME)));
	}
	
	private Position getPosition(ResultSet resultSet) throws SQLException {
		return Position.valueOf(resultSet.getString(EMPLOYEE_POSITION));
	}
	
	private Position getManPosition(ResultSet resultSet) throws SQLException {
		return Position.valueOf(resultSet.getString(MANAGER_POSITION));
	}
	
	private LocalDate getHireDate(ResultSet resultSet) throws SQLException {
		return LocalDate.parse(resultSet.getDate(EMPLOYEE_HIRE_DATE).toString());
	}
	
	private LocalDate getManHireDate(ResultSet resultSet) throws SQLException {
		return LocalDate.parse(resultSet.getDate(MANAGER_HIRE_DATE).toString());
	}
	
	private BigDecimal getSalary(ResultSet resultSet) throws SQLException {
		return new BigDecimal(resultSet.getInt(EMPLOYEE_SALARY));
	}
	
	private BigDecimal getManSalary(ResultSet resultSet) throws SQLException {
		return new BigDecimal(resultSet.getInt(MANAGER_SALARY));
	}
	
	public List<Employee> getByDepartment(Department department) {
		List<Employee> list = new ArrayList<>();
		Employee employee;
		
		try (Connection connection = connectionSource.createConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_DEP)) {
			
			statement.setInt(1, department.getId().intValue());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				employee = getEmployee(resultSet);
				list.add(employee);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	public List<Employee> getAllByManager(Employee manager) {
		List<Employee> list = new ArrayList<>();
		Employee employee;
		
		try (Connection connection = connectionSource.createConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_ALL_BY_MANAGER)) {
			
			statement.setInt(1, manager.getId().intValue());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				employee = getEmployee(resultSet);
				list.add(employee);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}
	
	private Department getDepartment(ResultSet resultSet) throws SQLException {
		if (resultSet.getLong(DEPARTMENT_ID) == 0) {
			return null;
		}
		BigInteger departmentId = BigInteger.valueOf(resultSet.getLong(DEPARTMENT_ID));
		String departmentName = resultSet.getString(DEPARTMENT_NAME);
		String departmentLocation = resultSet.getString(DEPARTMENT_LOCATION);
		return new Department(departmentId, departmentName, departmentLocation);
	}
	
	private Department getManDepartment(ResultSet resultSet) throws SQLException {
		if (resultSet.getLong(MANAGER_DEPARTMENT_ID) == 0) {
			return null;
		}
		BigInteger departmentId = BigInteger.valueOf(resultSet.getLong(MANAGER_DEPARTMENT_ID));
		String departmentName = resultSet.getString(MANAGER_DEPARTMENT_NAME);
		String departmentLocation = resultSet.getString(MANAGER_DEPARTMENT_LOCATION);
		return new Department(departmentId, departmentName, departmentLocation);
	}
	
	public Employee getEmployeeCTE(Employee inputtedEmployee) {
		Employee employee;
		
		try (Connection connection = connectionSource.createConnection();
		     PreparedStatement statement = connection.prepareStatement(GET_MANAGER_CTE)) {
			
			statement.setInt(1, inputtedEmployee.getId().intValue());
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				employee = getEmployeeRecursively(resultSet);
			} else return inputtedEmployee;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return employee;
	}
	
	private Employee getEmployeeRecursively(ResultSet resultSet) throws SQLException {
		Employee manager = null;
		Department department;
		BigInteger id = getId(resultSet);
		FullName fullName = getFullName(resultSet);
		Position position = getPosition(resultSet);
		LocalDate hireDate = getHireDate(resultSet);
		BigDecimal salary = getSalary(resultSet);
		department = getDepartmentForChain(resultSet);
		if (resultSet.next()) {
			manager = getEmployeeRecursively(resultSet);
		}
		return new Employee(id, fullName, position, hireDate, salary, manager, department);
	}
	
	private Department getDepartmentForChain(ResultSet resultSet) throws SQLException {
		if (resultSet.getLong(DEPARTMENT_ID) == 0) {
			return null;
		}
		BigInteger departmentId = BigInteger.valueOf(resultSet.getLong(DEPARTMENT_ID));
		String departmentName = resultSet.getString(DEPARTMENT_NAME);
		String departmentLocation = resultSet.getString(DEPARTMENT_LOCATION);
		return new Department(departmentId, departmentName, departmentLocation);
	}
}
