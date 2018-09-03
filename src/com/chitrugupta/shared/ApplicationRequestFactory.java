package com.chitrugupta.shared;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface ApplicationRequestFactory extends RequestFactory{
	EmployeeRequestContext employeeRequestContext();
}
