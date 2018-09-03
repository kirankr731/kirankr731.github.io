package com.chitrugupta.shared;
import java.util.List;

import com.chitrugupta.server.GreetingServiceImpl;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
@Service(value = GreetingServiceImpl.class)
public interface EmployeeRequestContext extends RequestContext {
	Request<Boolean> addEmployee(EmployeeProxy e1);
	Request<EmployeeProxy> queryEmployee(int id);
	Request<Boolean> RemoveEmployee(int id);
	Request<List<EmployeeProxy>> allEmployees();
	Request<EmployeeProxy> checkemp(int id);
	Request<Boolean> editemp(EmployeeProxy e);
}
