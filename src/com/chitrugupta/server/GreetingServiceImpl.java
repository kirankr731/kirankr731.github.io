package com.chitrugupta.server;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
public class GreetingServiceImpl{
	static SessionFactory sf=null;
	static {
	Configuration cfg=new Configuration();
	cfg.configure("Hibernate.cfg.xml");
	sf=cfg.buildSessionFactory();
	}
	public static boolean addEmployee(Employee e1)
	{
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		try {
			s.save(e1);
			s.flush();
			tx.commit();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		/*finally {
			if(s!=null)
				s.close();
		}*/
	}
	public static Employee queryEmployee(int id)
	{
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		Employee emp=null;
		try
		{
		emp=(Employee)s.get(Employee.class, id);
		tx.commit();
		s.flush();
		return emp;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return emp;
		}
		/*finally {
			if(s!=null)
				s.close();
		}*/
	}
	public static boolean RemoveEmployee(int id)
	{
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		try {
			Employee emp=(Employee) s.get(Employee.class,id);
		s.delete(emp);
		tx.commit();
		s.flush();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	/*	finally {
			if(s!=null)
				s.close();
		}*/
	}
	public static List<Employee> allEmployees()
	{
		Set<Employee> ts=null;
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		List<Employee> l=null;
		try {
		l=s.createQuery("from com.chitrugupta.server.Employee").list();
		java.util.Collections.sort(l);
		tx.commit();
		s.flush();
		return l;
		}
		catch (Exception e) {
			e.printStackTrace();
			return l;
		}
		/*finally {
			if(s!=null)
				s.close();
		}*/
	}
	public static Employee checkemp(int id)
	{
			Session s=sf.openSession();
			Transaction tx=s.beginTransaction();
			Employee emp=null;
			try
			{
			emp=(Employee)s.get(Employee.class, id);
			tx.commit();
			s.flush();
			s.close();
			return emp;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return emp;
			}
		}
	public static boolean editemp(Employee e)
	{
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		try {
			s.update(e);
			tx.commit();
			return true;
		}
		catch (Exception e2) {
			return false;
		}
	}
}
