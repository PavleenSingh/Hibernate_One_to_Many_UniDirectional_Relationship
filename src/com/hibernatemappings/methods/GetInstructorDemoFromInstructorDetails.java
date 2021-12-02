package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class GetInstructorDemoFromInstructorDetails {

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
			session.beginTransaction();
			
			// get instructor by primary key/id
			InstructorDetail d=session.get(InstructorDetail.class, 2); //here 2 is id 
			System.out.println(d);
			
			// getting instructor object using Instructor detail
			System.out.println(d.getInstructor());
			
			
			session.getTransaction().commit();
			
			// for deleting
			// first delete the chain
			//d.getInstructor().setInstructorDetail(null); // breaking the chain
			//session.delete(d);
			
		}catch(Exception e)
		{
			System.out.println("Error" +e);
		}finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}

	}

}
