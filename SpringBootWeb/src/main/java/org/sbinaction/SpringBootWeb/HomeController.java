package org.sbinaction.SpringBootWeb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "index"; // Spring Boot will look for index.html in the static directory
    }
    @RequestMapping("/add")
    public String add(@RequestParam(value = "number1") int number1, @RequestParam(value = "number2") int number2, HttpSession session, Model model) {
        int result = number1 * number2;
        System.out.println(number1);
        System.out.println(number2);
        System.out.println(result);
        model.addAttribute("result", result);
//    System.out.println(result);
//    session.setAttribute("result", result);
        return "result";
    }

}
