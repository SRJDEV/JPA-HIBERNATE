package com.example.many_many;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.one_one.Employee;

public class Main_N_N 
{
	public static SessionFactory sessionFactory;

	public static void insertCourses() {
	
		Student student_tom = new Student("Tom"); //Java, Hibernate
		Student student_jerry = new Student("Jerry"); //Java, Spring
		Student student_ivan = new Student("Ivan"); //Hibernate, Spring
		
		Set<Student> java_students = new HashSet<Student>();
		java_students.add(student_tom);java_students.add(student_jerry);
		
		Set<Student> hibernate_students = new HashSet<Student>();
		hibernate_students.add(student_tom);hibernate_students.add(student_ivan);
		
		Set<Student> spring_students = new HashSet<Student>();
		spring_students.add(student_jerry);spring_students.add(student_ivan);
		
		Course course_java = new Course("Java programming", java_students);		
		Course course_hibernate = new Course("Hibernate", hibernate_students);
		Course course_spring = new Course("Spring", spring_students);

		Session session = sessionFactory.openSession();
		Transaction t1 = session.beginTransaction();
		session.persist(course_java);
		session.persist(course_hibernate);
		session.persist(course_spring);
		t1.commit();
		session.close();
	}
	
	public static void insertStudents() {
		
		Course course_1 = new Course("Java programming");		
		Course course_2 = new Course("Hibernate");
		Course course_3 = new Course("Spring");
		
		Set<Course> tomCourses = new HashSet<Course>();
		
		Set<Course> jerryCourses = new HashSet<Course>();
		tomCourses.add(course_1);tomCourses.add(course_2);tomCourses.add(course_3);
		jerryCourses.add(course_1);jerryCourses.add(course_3);
	
		Student student_tom = new Student("Tom", tomCourses);
		Student student_jerry = new Student("Jerry", jerryCourses);

				Session session1 = sessionFactory.openSession();
				Transaction t1 = session1.beginTransaction();
				session1.persist(student_tom);
				session1.persist(student_jerry);
				t1.commit();
				session1.close();
	}
    public static void main( String[] args )
    {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		sessionFactory = cfg.buildSessionFactory();
		
		//insertStudents();
		insertCourses();
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Course where COURSE_ID=:courseId").setParameter("courseId", 4L);
		List<Course> courses = query.list();
		System.out.println("Course Name: " + courses.get(0).getCourseName());
		
		for(Student student : courses.get(0).getStudents()) {
			System.out.println("Students :"+student.getStudentName());
		}
	
		
		//Updatig the course name
		Session session2 = sessionFactory.openSession();
		Transaction t2 = session2.beginTransaction();
		Course course = session2.load(Course.class,courses.get(0).getCourseId()); // Managed
		course.setCourseName("Hibernate_new");
		t2.commit();
		session2.close();
		
		
// DELETE
		
//		Session session3 = sessionFactory.openSession();
//		Transaction t3 = session3.beginTransaction();
//		Student stud = session3.load(Student.class,2L); // Managed
//		
//		session3.delete(stud);
//		t3.commit();
//		session2.close();
		
		
		
		
		sessionFactory.close();
    }
}
