package com.hibernatemappings.methods;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.hibernatemappings.entity.Course;
import com.hibernatemappings.entity.Instructor;
import com.hibernatemappings.entity.InstructorDetail;
import com.hibernatemappings.entity.Review;

public class DeleteDemo {

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
			Instructor i=session.get(Instructor.class, 1002);
			
			
			// checking if Instructor is not null
			if(i!=null) {
				System.out.println("Deleting");
				System.out.println(i);
				session.delete(i);
				System.out.println("DONE!!!!!");
			}
			// Cascade type ALL => its going to delete Instructor object as well as its associated
			// InstructorDetail Object 
			session.getTransaction().commit();
			
		}catch(Exception e)
		{
			System.out.println("Error" +e);
		}finally {
			factory.close();
		}

	}

}
