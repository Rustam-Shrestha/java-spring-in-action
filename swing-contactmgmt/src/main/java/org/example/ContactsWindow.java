package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

// Creating a Java class for the contacts listing dashboard
public class ContactsWindow extends JFrame {
    // Creating a table to show contacts in proper form
    private JTable table;
    private DefaultTableModel model;

    public ContactsWindow() {
        setTitle("Contacts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table Model
        model = new DefaultTableModel(new String[]{"ID", "Name", "Gender", "Phone", "Email", "Address", "Actions"}, 0);
        table = new JTable(model);
        table.setRowHeight(30);

        // Load Data from Database
        loadContacts();

        // Add Buttons for Each Row
        table.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        table.getColumn("Actions").setCellEditor(new ButtonEditor(new JCheckBox()));

        // Add "Plus" button for adding new contacts
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(e -> showAddModal());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadContacts() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactMgmt", "root", "");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM contacts")) {

            model.setRowCount(0);  // Clear Table
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("personName"), rs.getString("gender"),
                        rs.getString("phoneNumber"), rs.getString("email"), rs.getString("homeAddress"), "Edit/Delete"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show add contact modal
    private void showAddModal() {
        JDialog addDialog = new JDialog(ContactsWindow.this, "Add Contact", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField();
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField addressField = new JTextField();

        JButton addButton = new JButton("Add");

        addDialog.add(new JLabel("Name:"));
        addDialog.add(nameField);
        addDialog.add(new JLabel("Gender:"));
        addDialog.add(genderBox);
        addDialog.add(new JLabel("Phone:"));
        addDialog.add(phoneField);
        addDialog.add(new JLabel("Email:"));
        addDialog.add(emailField);
        addDialog.add(new JLabel("Address:"));
        addDialog.add(addressField);
        addDialog.add(new JLabel(""));
        addDialog.add(addButton);

        addButton.addActionListener(ev -> {
            String name = nameField.getText().trim();
            String gender = genderBox.getSelectedItem().toString();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String address = addressField.getText().trim();

            // Validation
            if (name.length() < 3) {
                JOptionPane.showMessageDialog(addDialog, "Name must be at least 3 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(addDialog, "Phone number must be exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(addDialog, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            addContact(name, gender, phone, email, address);
            addDialog.dispose();
        });


        addDialog.setVisible(true);
    }

    // Add new contact to database
    private void addContact(String name, String gender, String phone, String email, String address) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactMgmt", "root", "");
             PreparedStatement pst = con.prepareStatement("INSERT INTO contacts (personName, gender, phoneNumber, email, homeAddress) VALUES (?, ?, ?, ?, ?)")) {
            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.executeUpdate();
            loadContacts();  // Refresh Table
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Button Renderer for Table
    class ButtonRenderer extends JPanel implements TableCellRenderer {
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        public ButtonRenderer() {
            setLayout(new FlowLayout());
            add(editButton);
            add(deleteButton);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Button Editor for Table Actions
    class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel = new JPanel();
        protected JButton editButton = new JButton("Edit");
        protected JButton deleteButton = new JButton("Delete");
        private int selectedRow;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel.setLayout(new FlowLayout());
            panel.add(editButton);
            panel.add(deleteButton);

            // Edit Button Click
            editButton.addActionListener(e -> {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    showEditModal();
                }
            });

            // Delete Button Click
            deleteButton.addActionListener(e -> {
                selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    deleteContact(id);
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            selectedRow = row;
            return panel;
        }

        private void showEditModal() {
            int id = (int) table.getValueAt(selectedRow, 0);
            String name = (String) table.getValueAt(selectedRow, 1);
            String gender = (String) table.getValueAt(selectedRow, 2);
            String phone = (String) table.getValueAt(selectedRow, 3);
            String email = (String) table.getValueAt(selectedRow, 4);
            String address = (String) table.getValueAt(selectedRow, 5);

            JDialog editDialog = new JDialog(ContactsWindow.this, "Edit Contact", true);
            editDialog.setSize(400, 300);
            editDialog.setLayout(new GridLayout(6, 2));

            JTextField nameField = new JTextField(name);
            JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            genderBox.setSelectedItem(gender);
            JTextField phoneField = new JTextField(phone);
            JTextField emailField = new JTextField(email);
            JTextField addressField = new JTextField(address);

            JButton updateButton = new JButton("Update");

            editDialog.add(new JLabel("Name:"));
            editDialog.add(nameField);
            editDialog.add(new JLabel("Gender:"));
            editDialog.add(genderBox);
            editDialog.add(new JLabel("Phone:"));
            editDialog.add(phoneField);
            editDialog.add(new JLabel("Email:"));
            editDialog.add(emailField);
            editDialog.add(new JLabel("Address:"));
            editDialog.add(addressField);
            editDialog.add(new JLabel(""));
            editDialog.add(updateButton);

            updateButton.addActionListener(ev -> {
                String updatedName = nameField.getText().trim();
                String updatedGender = genderBox.getSelectedItem().toString();
                String updatedPhone = phoneField.getText().trim();
                String updatedEmail = emailField.getText().trim();
                String updatedAddress = addressField.getText().trim();

                // Validation
                if (updatedName.length() < 3) {
                    JOptionPane.showMessageDialog(editDialog, "Name must be at least 3 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!updatedPhone.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(editDialog, "Phone number must be exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!updatedEmail.contains("@") || !updatedEmail.contains(".")) {
                    JOptionPane.showMessageDialog(editDialog, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateContact(id, updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress);
                editDialog.dispose();
            });


            editDialog.setVisible(true);
        }

        private void updateContact(int id, String name, String gender, String phone, String email, String address) {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactMgmt", "root", "");
                 PreparedStatement pst = con.prepareStatement("UPDATE contacts SET personName=?, gender=?, phoneNumber=?, email=?, homeAddress=? WHERE id=?")) {
                pst.setString(1, name);
                pst.setString(2, gender);
                pst.setString(3, phone);
                pst.setString(4, email);
                pst.setString(5, address);
                pst.setInt(6, id);
                pst.executeUpdate();
                loadContacts();  // Refresh Table
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void deleteContact(int id) {
            int confirm = JOptionPane.showConfirmDialog(ContactsWindow.this, "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactMgmt", "root", "");
                     PreparedStatement pst = con.prepareStatement("DELETE FROM contacts WHERE id=?")) {
                    pst.setInt(1, id);
                    pst.executeUpdate();
                    loadContacts();  // Refresh Table
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }



}