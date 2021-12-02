package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class CreateInstructorCoursesDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		try {
			// create the objects
			
			Instructor i=new Instructor("Scooby","Doooo","scoobyD@gmail.com");
			InstructorDetail d=new InstructorDetail("Channel_ScoobyDoo_Show","Mystery Solving");
			/*
			Instructor i=new Instructor("Raman","Singhaniya","ramanS@gmail.com");
			InstructorDetail d=new InstructorDetail("Channel_CookingDesi","Cooking food and eating");
			*/
			// associate the objects
			// calling setInstructorDetail of Instructor Class
			i.setInstructorDetail(d);
			
			session.beginTransaction();
			
			// Cascade type ALL => its going to save Instructor object as well as its associated
			// InstructorDetail Object 
			session.save(i);
			System.out.println(i);
			System.out.println("SAVED!!!!");
			
			session.getTransaction().commit();
			
		}catch(Exception e)
		{
			System.out.println("Error" +e);
		}finally {
			session.close();
			factory.close();
		}

	}

}
