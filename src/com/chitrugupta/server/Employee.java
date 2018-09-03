package com.chitrugupta.server;

import java.io.Serializable;

public class Employee implements Serializable,Comparable<Employee>{
	public Employee()
	{
	}
	private String name;
	private int id;
	private String email;
	private int salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Employee(String name, int id, String email, int salary) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", email=" + email + ", salary=" + salary + "]";
	}
	@Override
	public int compareTo(Employee o) {
		return this.getId()-o.getId();
	}
	
	
}
