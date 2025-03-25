<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Form Page</title>
</head>
<body>
    <h2>User Preferences Form</h2>
    <form method="post" action="ResultPage.jsp">
        <!-- User Name -->
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>

        <!-- Favorite Color -->
        <label for="color">Favorite Color:</label>
        <input type="text" id="color" name="color"><br><br>

        <!-- Hobbies -->
        <label>Hobbies:</label><br>
        <input type="checkbox" id="hobby1" name="hobbies" value="Reading">
        <label for="hobby1">Reading</label><br>
        <input type="checkbox" id="hobby2" name="hobbies" value="Traveling">
        <label for="hobby2">Traveling</label><br>
        <input type="checkbox" id="hobby3" name="hobbies" value="Sports">
        <label for="hobby3">Sports</label><br><br>

        <!-- Submit Button -->
        <input type="submit" value="Submit">
    </form>
</body>
</html>
