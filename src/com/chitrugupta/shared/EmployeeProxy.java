package com.chitrugupta.shared;

import com.chitrugupta.server.Employee;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
@ProxyFor(Employee.class)
public interface EmployeeProxy extends ValueProxy{
	public String getName();
	public void setName(String name);
	public int getId();
	public void setId(int id);
	public String getEmail();
	public void setEmail(String email);
	public int getSalary();
	public void setSalary(int salary);
	
}
