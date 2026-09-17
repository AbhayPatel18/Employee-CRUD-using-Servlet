package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.employee.model.Employee;

public class EmployeeDao {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection con = null;
		
	String url = "jdbc:mysql://localhost:3306/employee_db";
	String user = "root";
	String password = "1234";
	
	
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection(url,user,password);
	
	return con;
	
	 }
	public boolean addEmployee(Employee emp)  {
		String query = "INSERT INTO employee (name, salary, department, city, mobile_number, email, age) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try(Connection con = EmployeeDao.getConnection() ;
			PreparedStatement pstmt = con.prepareStatement(query);
			){
			
			pstmt.setString(1,emp.getName());
			pstmt.setDouble(2,emp.getSalary());
			pstmt.setString(3, emp.getDepartment());
			pstmt.setString(4,emp.getCity());
			pstmt.setString(5, emp.getMobile_number());
			pstmt.setString(6, emp.getEmail());
			pstmt.setInt(7, emp.getAge());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected >0) return true;
			else return false;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return false;
			}
		
	}
	public ArrayList<Employee> viewAllEmployee() {
		ArrayList<Employee> employeeList = new ArrayList<>();
		String query = "SELECT * from Employee";
		
		try(Connection con = EmployeeDao.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
						ResultSet rs = pstmt.executeQuery();){
				
				while(rs.next()) {
					Employee e = new Employee(rs.getInt("emp_id") , rs.getString("name") , rs.getDouble("salary"),	rs.getString("department"), 
							rs.getString("city") , rs.getString("mobile_number"),
							rs.getString("email"), rs.getInt("age") );
					
					employeeList.add(e);
	
				}
			
		} catch (SQLException e) {
						e.printStackTrace();
		} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
		}
		return employeeList;
	}
		public Employee getEmployeeById(int id) {
	    Employee emp = null;
	    String query = "SELECT * FROM employee WHERE emp_id = ?";
	
	    try (Connection con = EmployeeDao.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(query)) {
	
	        pstmt.setInt(1, id);
	
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                emp = new Employee(
	                    rs.getInt("emp_id"),
	                    rs.getString("name"),
	                    rs.getDouble("salary"),
	                    rs.getString("department"),
	                    rs.getString("city"),
	                    rs.getString("mobile_number"),
	                    rs.getString("email"),
	                    rs.getInt("age")
	                );
	            }
	        }
	
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return emp;
	}
		public boolean updateEmployee(Employee emp) {
			String query = "UPDATE employee SET name=?, salary=?, department=?, city=?, "
		             + "mobile_number=?, email=?, age=? WHERE emp_id=?";
			
			try(Connection con = EmployeeDao.getConnection();
					PreparedStatement pstmt = con.prepareStatement(query);){
				
				

			pstmt.setString(1, emp.getName());           // 1
			pstmt.setDouble(2, emp.getSalary());         // 2
			pstmt.setString(3, emp.getDepartment());     // 3
			pstmt.setString(4, emp.getCity());           // 4
			pstmt.setString(5, emp.getMobile_number());  // 5
			pstmt.setString(6, emp.getEmail());          // 6
			pstmt.setInt(7,    emp.getAge());            // 7
			pstmt.setInt(8,    emp.getId());          // 8 ← WHERE

			return pstmt.executeUpdate() > 0;
				
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		public boolean deleteEmployee(int id) {
			String query = "DELETE FROM Employee WHERE emp_id = ?";
			
			try(Connection con = EmployeeDao.getConnection();
					PreparedStatement pstmt = con.prepareStatement(query);){
				
				pstmt.setInt(1, id);
				
				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;
				
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	
		
	
}
