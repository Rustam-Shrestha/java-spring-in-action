package org.rustamhibernate; // This package groups your Main class logically.

// Import necessary Hibernate classes.
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Create an instance of the Student object with sample data.
        Student rustam = new Student();
        rustam.setRollNo(2); // Set student roll number.
        rustam.setAge(22);   // Set student age.
        rustam.setName("Santosh Shrestha"); // Set student name.

        // Create a Hibernate Configuration instance to manage settings.
        Configuration cfg = new Configuration();
        cfg.configure(); // Load the default 'hibernate.cfg.xml' configuration file.

        // Add the Student class (annotated with @Entity) to the configuration.
        cfg.addAnnotatedClass(org.rustamhibernate.Student.class);

        // Build the SessionFactory from the configuration (one per application).
        SessionFactory sf = cfg.buildSessionFactory();

        // Open a session to interact with the database.
        Session session = sf.openSession();

        // Begin a transaction to ensure ACID properties for database operations.
        Transaction transaction = session.beginTransaction();

        // Save the Student object to the database.
        session.save(rustam);

        // Commit the transaction to finalize changes in the database.
        transaction.commit();

        // Close the session to release resources.
        session.close();

        // Close the SessionFactory to release resources.
        sf.close();
    }
}
