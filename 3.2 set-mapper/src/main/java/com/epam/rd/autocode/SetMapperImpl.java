package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SetMapperImpl implements SetMapper<Set<Employee>> {
	
	private static final String ID = "id";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String MIDDLE_NAME = "middleName";
	private static final String POSITION = "position";
	private static final String SALARY = "salary";
	private static final String HIREDATE = "hiredate";
	private static final String MANAGER = "manager";
	
	@Override
	public Set<Employee> mapSet(ResultSet resultSet) {
		Set<Employee> set = new HashSet<>();
		Employee employee;
		try {
			while (resultSet.next()) {
				int currentRow = resultSet.getRow();
				employee = getEmployee(resultSet);
				resultSet.absolute(currentRow);
				set.add(employee);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	
	private Employee getEmployee(ResultSet resultSet) throws SQLException {
		return new Employee(
				getId(resultSet),
				getFullName(resultSet),
				getPosition(resultSet),
				getHireDate(resultSet),
				getSalary(resultSet),
				getManager(resultSet.getString(MANAGER), resultSet));
	}
	
	private Employee getManager(String managerID, ResultSet resultSet) {
		
		Employee manager = null;
		
		if (managerID == null) {
			return manager;
		} else {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					if (managerID.equals(resultSet.getString("ID"))) {
						manager = getEmployee(resultSet);
						return manager;
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return manager;
	}
	
	private BigInteger getId(ResultSet resultSet) throws SQLException {
		return new BigInteger(resultSet.getString(ID));
	}
	
	private FullName getFullName(ResultSet resultSet) throws SQLException {
		return new FullName((resultSet.getString(FIRST_NAME)), (resultSet.getString(LAST_NAME)),
				(resultSet.getString(MIDDLE_NAME)));
	}
	
	private Position getPosition(ResultSet resultSet) throws SQLException {
		return Position.valueOf(resultSet.getString(POSITION));
	}
	
	private LocalDate getHireDate(ResultSet resultSet) throws SQLException {
		return LocalDate.of(resultSet.getDate(HIREDATE).toLocalDate().getYear(),
				resultSet.getDate(HIREDATE).toLocalDate().getMonth(),
				resultSet.getDate(HIREDATE).toLocalDate().getDayOfMonth());
	}
	
	private BigDecimal getSalary(ResultSet resultSet) throws SQLException {
		return new BigDecimal(resultSet.getInt(SALARY));
	}
}

