package com.example.one_one;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sessionFactory = cfg.buildSessionFactory();

		Session session = sessionFactory.openSession();
	
		Profile profile = new Profile("Zensar", 5);
	
		Employee emp = new Employee("Suraj", 20000, profile);
		
		Transaction t1 = session.beginTransaction();
	
		session.persist(emp);

		long e_id = emp.getEmployeeId(); //Managed
		System.out.println("Employee id = " + e_id);
		
		
		t1.commit();
		
		session.close();	
		

		/* Merge	*/
		
		//operations on detached entities
		emp.setSal(50000);
		emp.getProfile().setExperience(10L);
		
		Session sessionMerge = sessionFactory.openSession();
		sessionMerge.beginTransaction();
		
		Employee empNew = sessionMerge.get(Employee.class, e_id);

	
	
		sessionMerge.merge(emp);
		
		sessionMerge.getTransaction().commit();
		

		sessionMerge.close();
		

		
		//updating 
//		
//		Session session2 = sessionFactory.openSession();
//		Transaction t2 = session2.beginTransaction();
//		
//		Employee emp2 = session2.load(Employee.class, e_id); //proxy
//		
//		
//		emp2.setSal(35000);
//		emp2.getProfile().setExperience(6);
//		t2.commit();
//	
//		session2.close();
		
	
//		
		
		Session session3 = sessionFactory.openSession();
		Query query = session3.createQuery("from Employee");
		List<Employee> employees = query.list();
		for(Employee e: employees) {
			System.out.println("employee = " + e);
		}
		session3.close();
		
//		Session session4 = sessionFactory.openSession();
//		Transaction t4 = session4.beginTransaction();
//		Employee emp4 = session4.load(Employee.class, e_id);
//		session4.delete(emp4);
//		t4.commit();
//		session4.close();
		
		
		
		
		sessionFactory.close();
		
		
		
		
    	
		
	}

}
