package org.rustamhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create a Student object
        Student rustam = new Student();
        rustam.setRollNo(4);
        rustam.setAge(28);
        rustam.setName("Benedict Cumberbach");

        Student updating = new Student();
        //changing two of field primar key being third key
        updating.setName("Updated Melissa Rauch");
        updating.setAge(29);
        updating.setRollNo(3);
        Configuration cfg = new Configuration();
        cfg.configure(); // Load the hibernate.cfg.xml
        cfg.addAnnotatedClass(Student.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();
        session.merge(updating);
            transaction.commit();

        try {

            // Save the Student object
            session.persist(rustam);

            // Fetch another Student by primary key
            Student fetchStudent = session.get(Student.class, 1);
            if (fetchStudent != null) {
                System.out.println(fetchStudent.getName());
            } else {
                System.out.println("Student with roll number 1 not found.");
            }
            Student deleting = null;
            deleting = session.get(Student.class,1);
        Transaction deleteTransaction = session.beginTransaction();
            session.remove(deleting);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sf.close();
        }
    }
}
