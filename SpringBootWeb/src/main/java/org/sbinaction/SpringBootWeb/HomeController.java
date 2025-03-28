package org.sbinaction.SpringBootWeb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "index.jsp"; // Spring Boot will look for index.html in the static directory
    }
    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpSession session){
        int n1 =Integer.parseInt(request.getParameter("number1"));
        int n2 =Integer.parseInt(request.getParameter("number2"));
        int resut = n1*n2;
        System.out.println(resut);
        session.setAttribute("result", resut);
        return "result.jsp";
    }
}
