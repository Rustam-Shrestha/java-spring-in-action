import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create LoginBean and validate login
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        RequestDispatcher dispatcher;
        if (loginBean.checkLogin()) {
            // Forward to success page
            dispatcher = request.getRequestDispatcher("LoginSuccess.jsp");
        } else {
            // Forward to error page
            dispatcher = request.getRequestDispatcher("LoginError.jsp");
        }
        dispatcher.forward(request, response);
    }
}
