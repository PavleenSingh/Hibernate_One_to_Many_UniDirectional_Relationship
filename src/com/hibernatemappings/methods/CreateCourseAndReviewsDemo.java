package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class CreateCourseAndReviewsDemo {

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
			
			// create some course
			Course c1=new Course("Finding Ghost as a Dost");
			
			// Create some reviews
			Review r1=new Review("Very Scary Course.Loved it!!");
			Review r2=new Review("Hard Diffculty.But also good.");
			
			// Add courses to instructor
			i.add(c1);
			
			// Add reviews to the course
			c1.add(r1);
			c1.add(r2);
			
			// save the courses in db
			session.save(c1); // it will save reviews also // Cascade feature
			
			System.out.println("Instructor"+i);
			System.out.println("Course"+c1);
			System.out.println(c1.getReviews());
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
