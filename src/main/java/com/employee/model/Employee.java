package com.employee.model;

public class Employee {
	private int id;
	private String name;
	private double salary;
	private String department;
	private String city;
	private String mobile_number;
	private String email;
	private int age;
	
	
	public Employee(int id, String name, double salary, String department, String city, String mobile_number,
			String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
		this.city = city;
		this.mobile_number = mobile_number;
		this.email = email;
		this.age = age;
	}
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
