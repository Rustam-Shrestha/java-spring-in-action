<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Input Form</title>
</head>
<body>
    <h2>Input Form</h2>
    <form method="post" action="DisplayPage.jsp">
        <!-- Input Box -->
        <label for="textbox">Text Input:</label>
        <input type="text" id="textbox" name="textbox"><br><br>

        <!-- Checkbox -->
        <label>Choose options:</label><br>
        <input type="checkbox" id="option1" name="checkboxOption" value="Bonsai">
        <label for="option1">Bonsai</label><br>
        <input type="checkbox" id="option2" name="checkboxOption" value="Horticulture">
        <label for="option2">Horticulture</label><br><br>

        <!-- Radio Buttons -->
        <label>Select one:</label><br>
        <input type="radio" id="radio1" name="radioOption" value="Gay">
        <label for="radio1">Male</label><br>
        <input type="radio" id="radio2" name="radioOption" value="Lesbian">
        <label for="radio2">Female</label><br><br>

        <!-- Dropdown -->
        <label for="dropdown">Select a value:</label>
        <select id="dropdown" name="dropdownOption">
            <option value="Swindled bad">BCA</option>
            <option value="lucky guy">BIT</option>
            <option value="Self desrtuction">Bsc. CSIT</option>
        </select><br><br>

        <!-- Number Fields -->
        <label for="num1">Enter First Number:</label>
        <input type="number" id="num1" name="num1"><br><br>
        <label for="num2">Enter Second Number:</label>
        <input type="number" id="num2" name="num2"><br><br>

        <!-- Submit Button -->
        <input type="submit" value="Submit">
    </form>
</body>
</html>
