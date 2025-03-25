package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

// Import ContactsWindow class (adjust the package path if necessary)

public class LoginPage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BoxLayout for better organization
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Username panel
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        usernamePanel.add(usernameField);
        mainPanel.add(usernamePanel);

        // Password panel diisply
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);
        mainPanel.add(passwordPanel);

        // Login button panel disply
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        mainPanel.add(buttonPanel);

        // Button Action event listener raheo
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        // Add main panel to framepanen ading
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void authenticateUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            // Debugging connection
            System.out.println("Attempting to connect to the database...");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactMgmt", "root", "");

            // Debugging successful connection
            System.out.println("Database connection successful!");

            String query = "SELECT * FROM admin WHERE userName = ? AND adminPassword = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                JOptionPane.showMessageDialog(this, "Login Successful!");
                this.dispose();  // Close login window
                new ContactsWindow(); // Open Contacts window
            } else {
                System.out.println("Invalid username or password.");
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
        } catch (SQLException ex) {
            // Printing the SQL exception to understand the error
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}


