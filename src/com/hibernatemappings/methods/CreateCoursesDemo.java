package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class CreateCoursesDemo {

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
			
			// create some courses
			Course c1=new Course("Baking- The Cake");
			Course c2=new Course("Chinese- Snacks");
			Course c3=new Course("Desi - Tadka");
			
			// Add courses to instructor
			i.add(c1);
			i.add(c2);
			i.add(c3);
			
			// save the courses in db
			session.save(c1);
			session.save(c2);
			session.save(c3);
			
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
