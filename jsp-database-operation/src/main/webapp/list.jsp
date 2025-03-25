<!DOCTYPE html>
<html>
<head>
    <title>Task List</title>
</head>
<body>
    <h2>Task List</h2>
    <a href="add.jsp">Add New Task</a><br><br>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Timestamp</th>
            <th>Actions</th>
        </tr>
        <%
            ResultSet tasks = (ResultSet) request.getAttribute("tasks");
            while (tasks != null && tasks.next()) {
        %>
        <tr>
            <td><%= tasks.getString("title") %></td>
            <td><%= tasks.getString("description") %></td>
            <td><%= tasks.getTimestamp("timestamp") %></td>
            <td>
                <a href="update.jsp?id=<%= tasks.getInt("id") %>">Edit</a>
                <a href="TodoServlet?action=delete&id=<%= tasks.getInt("id") %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
