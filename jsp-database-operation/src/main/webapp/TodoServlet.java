import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TodoServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/todo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            if ("list".equals(action)) {
                listTasks(conn, request, response);
            } else if ("delete".equals(action)) {
                deleteTask(conn, request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            if ("add".equals(action)) {
                addTask(conn, request, response);
            } else if ("update".equals(action)) {
                updateTask(conn, request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listTasks(Connection conn, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tasks")) {
            ResultSet rs = stmt.executeQuery();
            request.setAttribute("tasks", rs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void addTask(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO tasks (title, description) VALUES (?, ?)")) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.executeUpdate();
        }
        response.sendRedirect("TodoServlet?action=list");
    }

    private void updateTask(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        try (PreparedStatement stmt = conn.prepareStatement("UPDATE tasks SET title = ?, description = ? WHERE id = ?")) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
        response.sendRedirect("TodoServlet?action=list");
    }

    private void deleteTask(Connection conn, HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        response.sendRedirect("TodoServlet?action=list");
    }
}
