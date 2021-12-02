package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class GetInstructorCoursesDemo {

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
			
			// get instructor from db
			int theId=1001;
			Instructor i=session.get(Instructor.class, theId);
			
			// retrive courses for instructor
			System.out.println(i);
			System.out.println(i.getCourses());
			
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
