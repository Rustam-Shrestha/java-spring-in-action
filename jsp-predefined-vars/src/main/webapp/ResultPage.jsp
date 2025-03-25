<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Result Page</title>
</head>
<body>
    <h2>Results Page</h2>

    <%
        // 1. Using request: Retrieve user input
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        String[] hobbies = request.getParameterValues("hobbies");

        // 2. Using session: Store the user's name in the session
        session.setAttribute("userName", name);

        // 3. Using application: Track visit count for the application
        Integer visitCount = (Integer) application.getAttribute("visitCount");
        if (visitCount == null) {
            visitCount = 0;
        }
        visitCount++;
        application.setAttribute("visitCount", visitCount);

        // 4. Using response: Redirect if no name is provided
        if (name == null || name.trim().isEmpty()) {
            response.sendRedirect("FormPage.jsp");
        }

        // 5. Using out: Display output
        out.println("<h3>Hello, " + name + "!</h3>");
        out.println("<p>Your favorite color is: " + (color != null ? color : "Not provided") + "</p>");
        out.print("<p>Your hobbies are: ");
        if (hobbies != null) {
            for (String hobby : hobbies) {
                out.print(hobby + " ");
            }
        } else {
            out.print("No hobbies selected");
        }
        out.println("</p>");

        // 6. Using config: Display servlet configuration info
        out.println("<p>Servlet Name (from config): " + config.getServletName() + "</p>");

        // 7. Using application: Display visit count
        out.println("<p>Total visits to this application: " + visitCount + "</p>");

        // 8. Using page: Confirm we are on the current page
        out.println("<p>The 'page' object refers to this JSP itself: " + page + "</p>");
    %>
</body>
</html>
