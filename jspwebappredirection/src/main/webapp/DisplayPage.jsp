<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Display Inputs and Calculations</title>
</head>
<body>
    <h2>Submitted Inputs and Calculations</h2>

    <%
        // Retrieve values from the request
        String textbox = request.getParameter("textbox");
        String[] checkboxOptions = request.getParameterValues("checkboxOption");
        String radioOption = request.getParameter("radioOption");
        String dropdownOption = request.getParameter("dropdownOption");
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        // Display the submitted data
    %>
    <h3>Your Inputs:</h3>
    <p>Text Input: <%= textbox != null ? textbox : "Not provided" %></p>
    <p>Checkbox Options:
        <% if (checkboxOptions != null) {
            for (String option : checkboxOptions) {
                out.print(option + " ");
            }
        } else {
            out.print("Not provided");
        }
        %>
    </p>
    <p>Radio Option: <%= radioOption != null ? radioOption : "Not provided" %></p>
    <p>Dropdown Option: <%= dropdownOption != null ? dropdownOption : "Not provided" %></p>

    <%
        // Perform calculations if numbers are provided
        if (num1Str != null && num2Str != null && !num1Str.isEmpty() && !num2Str.isEmpty()) {
            try {
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);
                double addition = num1 + num2;
                double subtraction = num1 - num2;
                double multiplication = num1 * num2;
                double division = num2 != 0 ? num1 / num2 : Double.NaN; // Handle division by zero

    %>
    <h3>Calculations:</h3>
    <p>Addition: <%= addition %></p>
    <p>Subtraction: <%= subtraction %></p>
    <p>Multiplication: <%= multiplication %></p>
    <p>Division: <%= division %></p>
    <%
            } catch (NumberFormatException e) {
                out.println("<p>Invalid number input. Please enter valid numbers.</p>");
            }
        } else {
    %>
    <p>Numbers not provided. Please enter both numbers.</p>
    <%
        }
    %>
</body>
</html>
