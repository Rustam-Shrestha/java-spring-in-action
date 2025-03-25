<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Task Manager</title>
</head>
<body>
    <h1>Add Task</h1>
    <form action="index.jsp" method="post">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required>
        <br>
        <label for="description">Description:</label>
        <textarea id="description" name="description" required></textarea>
        <br>
        <button type="submit">Submit</button>
    </form>
    <%
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                // Load MySQL driver
                Class.forName("com.mysql.jdbc.Driver");

                // Connect to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "");

                // Insert task into the database
                String sql = "INSERT INTO tasks (title, description, timestamp) VALUES (?, ?, NOW())";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    out.println("<p>Task added successfully!</p>");
                } else {
                    out.println("<p>Failed to add the task.</p>");
                }
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                // Close resources
                if (preparedStatement != null) try { preparedStatement.close(); } catch (Exception ignore) {}
                if (connection != null) try { connection.close(); } catch (Exception ignore) {}
            }
        }
    %>
</body>
</html>
