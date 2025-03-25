<!DOCTYPE html>
<html>
<head>
    <title>Update Task</title>
</head>
<body>
    <h2>Update Task</h2>
    <form method="post" action="TodoServlet?action=update">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="<%= request.getParameter("title") %>" required><br><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description"><%= request.getParameter("description") %></textarea><br><br>
        <button type="submit">Update Task</button>
    </form>
    <a href="TodoServlet?action=list">Back to Task List</a>
</body>
</html>
