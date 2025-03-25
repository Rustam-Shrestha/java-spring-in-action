<!DOCTYPE html>
<html>
<head>
    <title>Add Task</title>
</head>
<body>
    <h2>Add a New Task</h2>
    <form method="post" action="TodoServlet?action=add">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br><br>
        <button type="submit">Add Task</button>
    </form>
    <a href="TodoServlet?action=list">Back to Task List</a>
</body>
</html>
