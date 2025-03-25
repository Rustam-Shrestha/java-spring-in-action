<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Echo Inputs with Calculations</title>
</head>
<body>
    <h2>Input Form</h2>
    <form method="post">
        <!-- Input Box -->
        <label for="textbox">Text Input:</label>
        <input type="text" id="textbox" name="textbox"><br><br>

        <!-- Checkbox -->
        <label>Choose options:</label><br>
        <input type="checkbox" id="option1" name="checkboxOption" value="Bonsai">
        <label for="Bonsai">Bonsai</label><br>
        <input type="checkbox" id="option2" name="checkboxOption" value="Farming">
        <label for="Farming">Farming</label><br><br>

        <!-- Radio Buttons -->
        <label>Select one:</label><br>
        <input type="radio" id="radio1" name="radioOption" value="Gay>
        <label for="radio1">male</label><br>
        <input type="radio" id="radio2" name="radioOption" value="Lesbian">
        <label for="radio2">female</label><br><br>

        <!-- Dropdown -->
        <label for="dropdown">Select a value:</label>
        <select id="dropdown" name="dropdownOption">
            <option value="Biggest Swindle">BCA</option>
            <option value="lucky guy">BIT</option>
            <option value="self destruction">Bsc. CSIT</option>
        </select><br><br>

        <!-- Number Fields -->
        <label for="num1">Enter First Number:</label>
        <input type="number" id="num1" name="num1"><br><br>
        <label for="num2">Enter Second Number:</label>
        <input type="number" id="num2" name="num2"><br><br>

        <!-- Submit Button -->
        <input type="submit" value="Submit">
    </form>

    <%
        // Display input values if the form is submitted
        String textbox = request.getParameter("textbox");
        String[] checkboxOptions = request.getParameterValues("checkboxOption");
        String radioOption = request.getParameter("radioOption");
        String dropdownOption = request.getParameter("dropdownOption");
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        // Check if number fields are not null and perform calculations
        if (num1Str != null && num2Str != null && !num1Str.isEmpty() && !num2Str.isEmpty()) {
            try {
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);
                double addition = num1 + num2;
                double subtraction = num1 - num2;
                double multiplication = num1 * num2;
                double division = num2 != 0 ? num1 / num2 : Double.NaN; // Avoid division by zero

    %>
    <h2>Your Inputs:</h2>
    <p>Text Input: <%= textbox != null ? textbox : "" %></p>
    <p>Hobbies:
        <% if (checkboxOptions != null) {
            for (String option : checkboxOptions) {
                out.print(option + " ");
            }
        } %>
    </p>
    <p>Gender: <%= radioOption != null ? radioOption : "" %></p>
    <p>Course Comments: <%= dropdownOption != null ? dropdownOption : "" %></p>
    <h2>Calculations:</h2>
    <p>Addition: <%= addition %></p>
    <p>Subtraction: <%= subtraction %></p>
    <p>Multiplication: <%= multiplication %></p>
    <p>Division: <%= division %></p>
    <%
            } catch (NumberFormatException e) {
                out.println("<p>Invalid number input. Please enter valid numbers.</p>");
            }
        }
    %>
</body>
</html>
