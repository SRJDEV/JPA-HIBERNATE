package com.example;

import org.hibernate.Transaction;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory sessionFactory = cfg.buildSessionFactory();

		/*This is changes from dev_user branch
		method xyz(){
			
			//int x =100;
	/*}
		/* this is change in code
		 * for git conflit
		 * 
		 * method1(){
		 * int x=20; changes on dev branch
		 * x =40;
		 * int y-0 ;
		 * }
		 *  */
		
		Session session = sessionFactory.openSession();
		
		Transaction t = session.beginTransaction();
		
		Product product = new Product("Laptop", 25000);
		
		session.persist(product);//Managed entity
		
		Product pr = session.get(Product.class, product.getId());
		
	//	session.evict(product); // removes cached entity at runtime
		
		long productId = product.getId();
		pr = session.get(Product.class, productId);
		t.commit();
		session.close();

		
		
		
		
		
		sessionFactory.getCache().evictEntity(Product.class, product.getId());
		
		//Thread.sleep(10000);
	

//		
		Session session2 = sessionFactory.openSession();
		t = session2.beginTransaction();
		long product_Id = product.getId();

		Product pr_2 = session2.get(Product.class, product_Id);
		pr_2.setPrice(12000);
		t.commit();
		session2.close();

		sessionFactory.close();
	}

}
